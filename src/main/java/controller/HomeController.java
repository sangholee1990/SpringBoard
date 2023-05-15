package controller;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import acInfo.DynamicBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;
import utils.WebUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//		list : "/html/fim/acInfo/listAcInfo.do"
//		, insertData : "/json/fim/acInfo/insertAcInfo.do"
//  http://localhost:9000/json/fim/acInfo/insertAcInfo.do

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

    private static final Logger log = LoggerFactory.getLogger(HomeController.class);

    protected static final String VIEW_JSON = GlobalVars.View.JSON;


    @RequestMapping(value = "/hello.do")
    public void sayHello(HttpServletRequest request) {
        System.out.println("hello");
        WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getSession().getServletContext());
        System.out.println(context.toString());
        UserService userService = (UserService) context.getBean("userService");

        userService.sayHello();
    }

    @RequestMapping(value = "/hello2.do")
    public void sayHello2(HttpServletRequest request, @RequestParam Map<String, Object> params) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        System.out.println("hello2");
        String method = "sayHello";
        String service = "userService";

        WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getSession().getServletContext());
        Object oService = context.getBean(service);

        Method meMethod = oService.getClass().getMethod(method, params.getClass());
        Object oResult = meMethod.invoke(oService, params);
    }

    /**
     * Simply selects the home view to render by returning its name.
     */
//    @RequestMapping(value = "/", method = RequestMethod.GET)
//    http://localhost:9000/json/test/DynamicBean/sayHello.do
//    http://localhost:9000/json/acInfo/DynamicBean/sayHello.do
    @RequestMapping(value = "/**/*.do")
    public String home(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, Object> params, ModelAndView mav) throws Exception {
//        log.info("Welcome home! The client locale is {}.", locale);

//        Date date = new Date();
//        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
//
//        String formattedDate = dateFormat.format(date);
//
//        model.addAttribute("serverTime", formattedDate);

        String service = null;
        String method = null;
        int idx;
        String url, sMapper, sSubMenuCd, sMenuCd;
        Object oService;
        Object oResult;
        Method meMethod;
        RequestDispatcher rdDispatcher;
        WebApplicationContext webApplicationContext;
        // json/smm/atclInfo/MainTest/list.do

        // http://localhost:9000/json/smm/atclInfo/MainTest/list.do

        try {

            params.put("request", request);
            params.put("response", response);
            params.put("session", request.getSession());
            System.out.println(String.format("[CHECK] params : %s", params));


//            Map<String, Object> params = new HashMap<>();
            params.put("num1", 5);
            params.put("num2", 3);


            System.out.println(String.format("[CHECK] request.getContextPath() : %s", request.getContextPath()));

            url = request.getRequestURI().substring(request.getContextPath().length());
            System.out.println(String.format("[CHECK] sUri : %s", url));

            if (url == null || url.length() < 1) return null;

            // 정적 화면
            if (url.indexOf("/html/") == 0) {


                if (url.indexOf("/html/login/") < 0) {

                    sSubMenuCd = (String) params.get("subMenuCd");
                    if ((sSubMenuCd == null || "".equals(sSubMenuCd) == true) && ((sMenuCd = (String) params.get("menuCd")) == null || "".equals(sMenuCd) == true)) {
                        params.put("menuUrl", url);
                    }
//                    moModel.addAttribute("menuInfo", menuInfoService.selMenuInfo(paParams));

                }

                return url.substring(6, url.length() - 3);
            }


            // 동적 화면
            if (url.indexOf("/json/") == 0) {
                Pattern pattern = Pattern.compile("^.*/(\\w+)/([^/]+)/([^/]+)\\.do$");
                Matcher matcher = pattern.matcher(url);
                if (matcher.matches()) {
                    String packageName = matcher.group(1);
                    String className = matcher.group(2);

                    service = packageName + "." + className;
                    method = matcher.group(3);
                }

                System.out.println(String.format("[CHECK] service : %s", service));
                System.out.println(String.format("[CHECK] method : %s", method));
                System.out.println(String.format("[CHECK] params : %s", params));


                Map result = new DynamicBean().createInstanceAndInvokeMethod(service, method, params);
//                Map result = DynamicBean.createInstanceAndInvokeMethod(service, method, params);
                System.out.println(String.format("[CHECK] result : %s", result));

                System.out.println(String.format("[CHECK] VIEW_JSON : %s", VIEW_JSON));

//                    addResult(moModel, result, sMethod);
                return VIEW_JSON;
            }
////                addResult(moModel, oResult, sMethod);
//                return VIEW_JSON;
        } catch (RuntimeException e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error("[url:" + request.getRequestURI() + "] ");
            throw e;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error("[url:" + request.getRequestURI() + "] ");
            throw e;
        }

        return "";
    }
}
