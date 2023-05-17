package action;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Component;
import utils.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Component
public class FileService {

    private static final long serialVersionUID = 1L;
    private static final String UPLOAD_PATH = "upload";

    // 1,000 MB
    private static final int MEMORY_THRESHOLD = 1024 * 1024 * 1000;
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 1000;
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 1000;

    public Map FileComp(Map mapData) throws Exception {

        Map result = new HashMap();

        HttpServletRequest request = (HttpServletRequest) mapData.get("request");

        String uploadPath = request.getSession().getServletContext().getRealPath(UPLOAD_PATH);
        String dtYmdHms = LocalDateTime.now(ZoneId.of("Asia/Seoul")).format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));

        FileService fileService = new FileService();

        List<File> fileList = fileService.getFileList(uploadPath);
        String comFileInfo = String.format("%s/%s_%s_%s.zip", uploadPath, dtYmdHms, "compression", fileList.size());

        compressFiles(fileList, comFileInfo);

        result.put("size", fileList.size());
        result.put("fileName", new File(comFileInfo).getName());

        return result;
    }

    public Map FileUpload(Map mapData) throws Exception {

        Map result = new HashMap();

        HttpServletRequest request = (HttpServletRequest) mapData.get("request");
        String uploadPath = request.getSession().getServletContext().getRealPath(UPLOAD_PATH);
        File uploadFileInfo = new File(uploadPath);

        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setFileSizeMax(MAX_FILE_SIZE);
        upload.setSizeMax(MAX_REQUEST_SIZE);

        if (!uploadFileInfo.exists() && !uploadFileInfo.mkdirs()) throw new Exception("디렉터리 생성 실패 : " + uploadPath);

        List<FileItem> items = upload.parseRequest(request);
        for (FileItem item : items) {
            if (item.isFormField())
                throw new Exception(String.format("파일 형식 오류 : %s | %s", item.getName(), item.getFieldName()));
            if (!(item.getSize() > 0))
                throw new Exception(String.format("파일 크기 오류 : %s | %s", item.getName(), item.getSize()));

            String dtYmdHms = LocalDateTime.now(ZoneId.of("Asia/Seoul")).format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
            String fileName = String.format("%s_%s", dtYmdHms, item.getName());

            File saveFile = new File(uploadPath, fileName);
            if (saveFile.exists())
                throw new Exception(String.format("파일 중복 : %s | %s", saveFile.getPath(), saveFile.getName()));

            item.write(saveFile);
            result.put("saveFile", saveFile);
        }

        return result;
    }

    public Map FileList(Map mapData) throws Exception {

        Map result = new HashMap();

        HttpServletRequest request = (HttpServletRequest) mapData.get("request");
        String uploadPath = request.getSession().getServletContext().getRealPath(UPLOAD_PATH);

        List<File> fileList = getFileList(uploadPath);

        List<Map<String, Object>> resMapList = fileList.stream()
                .map(this::createFileDataMap)
                .collect(Collectors.toList());

        result = createResponseData(fileList.size(), resMapList);

        return result;
    }

    public List<File> getFileList(String uploadPath) throws IOException {
        return Files.walk(Paths.get(uploadPath))
                .parallel()
                .filter(Files::isRegularFile)
                .filter(path -> !isZipFile(path))
                .map(Path::toFile)
                .collect(Collectors.toList());
    }

     private static void compressFiles(List<File> files, String outputZipFile) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        try (FileOutputStream fos = new FileOutputStream(outputZipFile);
             BufferedOutputStream bos = new BufferedOutputStream(fos);
             ZipOutputStream zos = new ZipOutputStream(bos)) {

            // 최대 압축 효율
            zos.setLevel(9);

            List<Future<Void>> tasks = new ArrayList<>();
            for (File file : files) {
                tasks.add(executor.submit(() -> {
                    addToZipFile(file, zos);
                    return null;
                }));
            }

            for (Future<Void> task : tasks) {
                task.get();
            }

        } catch (IOException | InterruptedException | ExecutionException iie) {
            System.out.println(String.format("[ERROR] IOException | InterruptedException | ExecutionException : %s", iie.getMessage()));
            throw new Exception(String.format("[ERROR] IOException | InterruptedException | ExecutionException : %s", iie.getMessage()));
        } finally {
            executor.shutdown();
        }
    }

    private static synchronized void addToZipFile(File file, ZipOutputStream zos) throws IOException {
        try (FileInputStream fis = new FileInputStream(file);
             BufferedInputStream bis = new BufferedInputStream(fis)) {

            ZipEntry zipEntry = new ZipEntry(file.getName());
            zos.putNextEntry(zipEntry);

            byte[] buffer = new byte[1024];
            int len;
            while ((len = bis.read(buffer)) != -1) {
                zos.write(buffer, 0, len);
            }

            zos.closeEntry();
        }
    }

    private static boolean isZipFile(Path path) {
        String fileName = path.getFileName().toString().toLowerCase();
        Pattern pattern = Pattern.compile("\\.(zip|rar)$");
        Matcher matcher = pattern.matcher(fileName);
        return matcher.find();
    }

    public Map<String, Object> createFileDataMap(File file) {
        Map<String, Object> mapData = new HashMap<>();
        mapData.put("name", file.getName());
        mapData.put("size", formatFileSize(file.length()));
        return mapData;
    }

    private String formatFileSize(long size) {
        return new DecimalFormat("#.##").format(size / 1024.0 / 1024.0) + " MB";
    }

    private Map<String, Object> createResponseData(int total, List<Map<String, Object>> resMapList) {
        Map<String, Object> resData = new HashMap<>();
        resData.put("total", total);
        resData.put("rows", resMapList);
        return resData;
    }
}
