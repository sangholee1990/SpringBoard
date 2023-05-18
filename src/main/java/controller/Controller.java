package controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import utils.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@org.springframework.stereotype.Controller
public class Controller {

    private static final Logger log = LoggerFactory.getLogger(Controller.class);

//    http://localhost:9000/json/acInfo/DynamicBean/sayHello.do
//    http://localhost:9000/html/acInfo/DynamicBean/sayHello.do
//    http://localhost:9000/html/acInfo/DynamicBean/sayHello2.do
//    http://localhost:9000/html/static/home/home.do

    @RequestMapping(value = "/**/*.do")
    public ModelAndView contoller(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, Object> params, ModelAndView mav) {

        String url = null;
        String service = null;
        String method = null;
        String view = null;
        String staticView = null;

        try {

            params.put("request", request);
            params.put("response", response);
            params.put("session", request.getSession());
            log.info("[CHECK] params : {}", params);

            url = request.getRequestURI().substring(request.getContextPath().length());
            log.info("[CHECK] url : {}", url);

            Pattern pattern = Pattern.compile("^.*/(\\w+)/([^/]+)/([^/]+)\\.do$");
            Matcher matcher = pattern.matcher(url);

            if (matcher.matches()) {
                String packageName = matcher.group(1);
                String className = matcher.group(2);
                method = matcher.group(3);

                service = packageName + "." + className;
                view = packageName + "/" + className;
                staticView = packageName + "/" + className + "/" + method;
            }

            if (url == null || url.length() < 1) return new ModelAndView();

            // 정적
            if (url.indexOf("/html/static") == 0) {
                log.info("[CHECK] staticView : {}", staticView);

                mav.setViewName(staticView);

                return mav;
            }

            // 동적 (화면 + 데이터)
            if (url.indexOf("/html/") == 0) {
                log.info("[CHECK] view : {}", view);
                log.info("[CHECK] service : {}", service);
                log.info("[CHECK] method : {}", method);

                Map<String, Object> result = WebUtils.getBean(service, method, params);
                if (result.containsKey("error") && result.get("error").equals(true)) return new ModelAndView();

                log.info("[CHECK] result : {}", result);

                mav.addObject(GlobalVars.Result.DATA, result);
                mav.setViewName(view);

                return mav;
            }

            // 동적 (데이터)
            if (url.indexOf("/json/") == 0) {
                log.info("[CHECK] service : {}", service);
                log.info("[CHECK] method : {}", method);

                 Map<String, Object> result = WebUtils.getBean(service, method, params);
                 log.info("[CHECK] result : {}", result);

                mav.addObject(GlobalVars.Result.DATA, result);
                mav.setViewName(GlobalVars.View.JSON);

                return mav;
            }

        } catch (RuntimeException e) {
            log.error("RuntimeException : {}", e.getMessage());
        } catch (Exception e) {
            log.error("Exception : {}", e.getMessage());
        }

//        return new ModelAndView();
        return null;
    }
}
