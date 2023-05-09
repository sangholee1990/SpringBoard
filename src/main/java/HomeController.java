//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Controller;
//import org.springframework.transaction.interceptor.TransactionAspectSupport;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.ModelAndView;
//import utils.WebUtils;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.lang.reflect.Method;
//import java.util.Map;
//
///**
// * Handles requests for the application home page.
// */
//@Controller
//public class HomeController {
//
//    private static final Logger log = LoggerFactory.getLogger(HomeController.class);
//
//    /**
//     * Simply selects the home view to render by returning its name.
//     */
////    @RequestMapping(value = "/", method = RequestMethod.GET)
//    @RequestMapping(value = "/**/*.do")
//    public String home(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, Object> params, ModelAndView mav) throws Exception {
////        log.info("Welcome home! The client locale is {}.", locale);
//
////        Date date = new Date();
////        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
////
////        String formattedDate = dateFormat.format(date);
////
////        model.addAttribute("serverTime", formattedDate);
//
//        int iIndex;
//        String sUri, sService, sMapper, sMethod, sSubMenuCd, sMenuCd;
//        Object oService;
//        Object oResult;
//        Method meMethod;
//        RequestDispatcher rdDispatcher;
//
//        try {
//
//            params.put("request", request);
//            params.put("response", response);
//            params.put("session", request.getSession());
//
//            sUri = request.getRequestURI().substring(request.getContextPath().length());
//            if (sUri == null || sUri.equals("") == true) return "";
//
//            if (sUri.indexOf("/json/") == 0) {
//
//                sUri = sUri.substring(0, sUri.length() - 3);
//
//                iIndex = sUri.lastIndexOf('/');
//                sMethod = sUri.substring(iIndex + 1);
//                sService = sUri.substring(sUri.lastIndexOf('/', iIndex - 1) + 1, iIndex) + "Service";
//
////                leMeLogger.info("call == service : {}, method : {}", sService, sMethod);
//
//                oService = WebUtils.getBean(request.getSession().getServletContext(), sService);
//                if (oService == null) {
////                    leMeLogger.error("[unknownClass][url:" + request.getRequestURI() + ", class:" + sService + "]");
//                    return "";
//                }
//
//                // 2019-02-18
//                // Service의 method를 검사 후 BaseService의 method를 검사해야 하는데
//                // BaseService의 method 부터 검사를 함.
//                // Service에 메소드가 없을 경우 BaseService의 메소드 검사하게 수정
////                meMethod = oService.getClass().getMethod(sMethod, Params.class);
////                if (meMethod == null) {
////                    meMethod = CsFuncs.getMethod(oService.getClass(), sMethod);
////                    if (meMethod == null) {
////                        leMeLogger.error("[unknownMethod][url:" + request.getRequestURI() + ", class:" + sService + ", method:" + sMethod + "]");
////                        throw new Exception("[not request servicdID] : " + sMethod);
////                    }
////                }
////
////                try {
////                    oResult = meMethod.invoke(oService, paParams);
////                    if (((Map) oResult).containsKey("error") == true && ((Map) oResult).get("error").equals(true) == true)
////                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
////                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
////                    if (e.getCause().getClass().equals(ServiceException.class)) {
////                        leMeLogger.error("[serviceException][url:" + request.getRequestURI() + ", class:" + sService + ", method:" + sMethod + "]");
////                        throw new ServiceException(null, e.getCause().getMessage(), e);
////                    } else {
////                        leMeLogger.error("[invokeError][url:" + request.getRequestURI() + ", class:" + sService + ", method:" + sMethod + "]");
////                    }
////                    throw e;
////                } catch (Exception e) {
////                    throw e;
////                }
////
////                addResult(moModel, oResult, sMethod);
////                return VIEW_JSON;
//
//            } else if (sUri.indexOf("/file/") == 0) {
//
//                oResult = null;
//
//                if (sUri.indexOf("/file/upload.do") == 0) fileInfoService.upload(paParams);
//                else if (sUri.indexOf("/file/download.do") == 0) oResult = fileInfoService.download(paParams);
//                else if (sUri.indexOf("/file/preview.do") == 0) oResult = fileInfoService.preview(paParams);
//
//                addResult(moModel, oResult, null);
//
//                return VIEW_JSON;
//
//        } catch (RuntimeException e) {
//            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
//            leMeLogger.error("[url:" + request.getRequestURI() + "] ");
//            throw e;
//        } catch (Exception e) {
//            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
//            leMeLogger.error("[url:" + request.getRequestURI() + "] ");
//            throw e;
//        }
//
//        return "";
//
//    }
//
//}
