package utils;

import com.google.gson.Gson;
import controller.Action;
import controller.ActionForward;
import controller.GlobalVars;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Component
public class WebUtils {

    private static final Logger log = LoggerFactory.getLogger(WebUtils.class);

//    public static void main(String[] args) {
//    }

    private static final Gson gson = new Gson();

    public static void writeResponse(HttpServletRequest request, HttpServletResponse response) {

        response.setContentType("application/json; charset=UTF-8");

        Map<String, Object> mapData = new HashMap<>();

        try {
            mapData = WebUtils.extractAttributes(request);
            WebUtils.writeJsonResponse(response, mapData);
        } catch (IOException ioe) {
            System.out.println(String.format("[ERROR] IOException : %s", ioe.getMessage()));
            WebUtils.handleError(request, response);
        } catch (Exception e) {
            System.out.println(String.format("[ERROR] Exception : %s", e.getMessage()));
            WebUtils.handleError(request, response);
        }
    }

    public static String getRequestCommand(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();
        return requestURI.substring(contextPath.length());
    }

    public static Action createActionInstance(ActionForward actionInfo) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class<?> className = Class.forName(actionInfo.getClassName());
        return (Action) className.newInstance();
    }


    public static void handleError(HttpServletRequest request, HttpServletResponse response) {
        try {
//            Map<String, Object> mapData = new HashMap<>();
//            mapData.put("error", "오류 발생");
//            WebUtils.writeJsonResponse(response, mapData);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "오류 발생");
        } catch (IOException ioe) {
            System.out.println(String.format("[ERROR] IOException : %s", ioe.getMessage()));
        } catch (Exception e) {
            System.out.println(String.format("[ERROR] Exception : %s", e.getMessage()));
        }
    }

    public static void handleResponse(HttpServletRequest request, HttpServletResponse response, ActionForward actionInfo) throws ServletException, IOException {
        // 비동기 통신에서 화면 전환없이 데이터 접근/전달
        if (actionInfo.isJson()) {
            WebUtils.writeResponse(request, response);
        } else if (actionInfo.isRedirect()) {
            // 동기 통신에서 데이터 접근/전달없이 화면 전환
            response.sendRedirect(actionInfo.getPath());
        } else {
            // 동기 통신에서 데이터 접근/전달 및 화면 전환
            RequestDispatcher dispatcher = request.getRequestDispatcher(actionInfo.getPath());
            dispatcher.forward(request, response);
        }
    }

    public static Map successResult(String messages, Map result) {
        Map mapData = new HashMap();
        mapData.put(GlobalVars.Result.SUCCESS, true);
        mapData.put(GlobalVars.Result.MESSAGES, messages);
        mapData.put(GlobalVars.Result.DATA, result);

        return mapData;
    }

    public static Map errorResult(String messages, String result) {
        Map mapData = new HashMap();
        mapData.put(GlobalVars.Result.ERROR, true);
        mapData.put(GlobalVars.Result.MESSAGES, messages);
        mapData.put(GlobalVars.Result.DATA, result);

        return mapData;
    }

    public static Map<String, Object> extractAttributes(HttpServletRequest request) {
        Map<String, Object> mapData = new HashMap<>();
        Enumeration<String> attributeNames = request.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String key = attributeNames.nextElement();
            Object val = request.getAttribute(key);
            mapData.put(key, val);
        }
        return mapData;
    }

    public static void writeJsonResponse(HttpServletResponse response, Map<String, Object> mapData) throws IOException {
        response.setContentType("application/json");

        try (PrintWriter out = response.getWriter()) {
            out.print(gson.toJson(mapData));
            out.flush();
        }
    }

    public static Map getBean(String className, String methodName, Map<String, Object> mapData) throws InvocationTargetException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InstantiationException {

        Map<String, Object> result = new HashMap<>();

        try {
            Class<?> getClass = Class.forName(className);
            Object getIns = getClass.getDeclaredConstructor().newInstance();
            Method method = getClass.getMethod(methodName, Map.class);
            method.setAccessible(true);
            result = (Map) method.invoke(getIns, mapData);

            return WebUtils.successResult("처리 완료했습니다.", result);
        } catch (RuntimeException e) {
            log.error("RuntimeException : {}", e.getMessage());
            return WebUtils.errorResult("오류 발생하였습니다.", e.getMessage());
        } catch (Exception e) {
            log.error("Exception : {}", e.getMessage());
            return WebUtils.errorResult("오류 발생하였습니다.", e.getMessage());
        }
    }

//    public static Object getBean(ServletContext scContext, String sBeanName) throws Exception {
//
//        Object oBean;
//
//        oBean = WebApplicationContextUtils.getRequiredWebApplicationContext(scContext).getBean(sBeanName);
//
//        return oBean;
//
//    }


//    public static Method getMethod(Class oClass, String sMethod) throws Exception {
//
//        int iCount, iNumber;
//        Method meMethod, meMethods[];
//
//        meMethods = oClass.getMethods();
//        iNumber = meMethods.length;
//
//        for (iCount = 0; iCount < iNumber; iCount++) {
//            if (meMethods[iCount].getName().equals(sMethod) == true) {
//                meMethod = meMethods[iCount];
//                meMethod.setAccessible(true);
//                return meMethod;
//            }
//        }
//
//        return null;
//
//    }

}
