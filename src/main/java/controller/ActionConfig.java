//package controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.core.io.Resource;
//import org.springframework.web.context.support.XmlWebApplicationContext;
//import utils.XmlUtils;
//
//import org.xml.sax.SAXException;
//
//import javax.xml.parsers.ParserConfigurationException;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.HashMap;
//import java.util.Map;
//
////@Configuration
//public class ActionConfig {
//
//    @Autowired
//    ApplicationContext resourceLoader;
//
//    @Bean
//    public Map<String, ActionForward> actionForwardMapping() {
//
//        System.out.println(String.format("[START] 스프링 컨테이너 : %s", " init"));
//
//        Map<String, ActionForward> mapData = new HashMap<>();
//
//        try {
//
//            ApplicationContext context = new ClassPathXmlApplicationContext("config/action.xml");
////            HelloWorld obj = (HelloWorld) context.getBean("helloWorld");
////            obj.getMessage();
////            ApplicationContext context = new XmlWebApplicationContext("config/action.xml");
//
//            System.out.println(context.toString());
//
//            System.out.println(resourceLoader.getClass()); //ApplicationContext의 리소스 타입을 찍어본다.
//
//            Resource resource = resourceLoader.getResource("classpath:config/action.xml");
//            System.out.println(resource.getClass());
//
//            System.out.println(resource.exists());
//            System.out.println(resource.getDescription());
//
//
////            ClassPathResource resource = new ClassPathResource("classpath:config/action.xml");
//            InputStream inputStream = resource.getInputStream();
//
////            ApplicationContext ctx = new ClassPathXmlApplicationContext();
////            Resource resource = ctx.getResource("classpath:config/action.xml");
////            System.out.println(String.format("[CHECK] resource : %s", resource.toString()));
//
//            // 웹 어플리케이션 최상단 배포 위치
//            String configInfo = inputStream.toString();
//            System.out.println(String.format("[CHECK] configInfo : %s", configInfo.toString()));
//
//            mapData = XmlUtils.parseActionXml(configInfo);
//            System.out.println(String.format("[CHECK] mapData : %s", mapData));
//
//        } catch (ParserConfigurationException | SAXException | IOException psi) {
//            System.out.println(String.format("[ERROR] XML 파일 읽기 오류 : %s", psi.getMessage()));
//        } catch (Exception e) {
//            System.out.println(String.format("[ERROR] 스프링 컨테이너 : %s : %s", "Exception", e.getMessage()));
//        } finally {
//            System.out.println(String.format("[END] 스프링 컨테이너 : %s", "init"));
//        }
//
//        return mapData;
//    }
//}
