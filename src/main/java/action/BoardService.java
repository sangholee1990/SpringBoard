package action;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

//@Service
@Component
@Service(value = "boardService")
public class BoardService {

//    private final BoardMapper boardMapper;

//    @Resource(name = "boardMapper")

    @Autowired
    private BoardMapper boardMapper;

    //    public Map list(Map mapData) throws Exception {
    public Map list(Map mapData) {

        Map result = new HashMap();

        try {
            System.out.println("11111");
            System.out.println(boardMapper.list(mapData));
            result.put("boardList", boardMapper.list(mapData));
        } catch (Exception e) {
            e.printStackTrace();
        }


        return result;
    }

}
