package controller;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import utils.WebUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

    private static final Logger log = LoggerFactory.getLogger(HomeController.class);

    protected static final String VIEW_JSON = GlobalVars.View.JSON;


    /**
     * Simply selects the home view to render by returning its name.
     */
//    @RequestMapping(value = "/", method = RequestMethod.GET)
    @RequestMapping(value = "/**/*.do")
    public String home(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, Object> params, ModelAndView mav) throws Exception {
//        log.info("Welcome home! The client locale is {}.", locale);

//        Date date = new Date();
//        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
//
//        String formattedDate = dateFormat.format(date);
//
//        model.addAttribute("serverTime", formattedDate);

        int iIndex;
        String sUri, sService, sMapper, sMethod, sSubMenuCd, sMenuCd;
        Object oService;
        Object oResult;
        Method meMethod;
        RequestDispatcher rdDispatcher;

        try {

            params.put("request", request);
            params.put("response", response);
            params.put("session", request.getSession());

            sUri = request.getRequestURI().substring(request.getContextPath().length());
            if (sUri == null || sUri.equals("") == true) return "";

            if (sUri.indexOf("/json/") == 0) {

                sUri = sUri.substring(0, sUri.length() - 3);

                iIndex = sUri.lastIndexOf('/');
                sMethod = sUri.substring(iIndex + 1);
                sService = sUri.substring(sUri.lastIndexOf('/', iIndex - 1) + 1, iIndex) + "Service";
                log.info("[CHECK] sService : %s", sService);
                log.info("[CHECK] sMethod : %s", sMethod);

//                leMeLogger.info("call == service : {}, method : {}", sService, sMethod);

                oService = WebUtils.getBean(request.getSession().getServletContext(), sService);
                if (oService == null) {
                    log.error("[unknownClass][url:" + request.getRequestURI() + ", class:" + sService + "]");
                    return "";
                }

//                meMethod = oService.getClass().getMethod(sMethod, params.class);
                meMethod = oService.getClass().getMethod(sMethod);
                if (meMethod == null) {
                    meMethod = WebUtils.getMethod(oService.getClass(), sMethod);
                    if (meMethod == null) {
                        log.error("[unknownMethod][url:" + request.getRequestURI() + ", class:" + sService + ", method:" + sMethod + "]");
                        throw new Exception("[not request servicdID] : " + sMethod);
                    }
                }

                try {
                    oResult = meMethod.invoke(oService, params);
                    if (((Map) oResult).containsKey("error") == true && ((Map) oResult).get("error").equals(true) == true)
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
//                    if (e.getCause().getClass().equals(ServiceException.class)) {
//                        log.error("[serviceException][url:" + request.getRequestURI() + ", class:" + sService + ", method:" + sMethod + "]");
////                        throw new ServiceException(null, e.getCause().getMessage(), e);
//                    } else {
//                        log.error("[invokeError][url:" + request.getRequestURI() + ", class:" + sService + ", method:" + sMethod + "]");
//                    }
//                    throw e;
                } catch (Exception e) {
                    throw e;
                }

//                addResult(moModel, oResult, sMethod);
                return VIEW_JSON;


            }
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
