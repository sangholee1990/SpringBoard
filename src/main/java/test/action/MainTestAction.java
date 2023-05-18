package test.action;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.Method;

public class MainTestAction {
    public static class TestService {
        public void testMethod(String param) {
            System.out.println("Method invoked with param: " + param);
        }
    }

    public static void main(String[] args) {
        try {


            // ApplicationContext를 설정합니다.
            ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

//            Object oBean = WebApplicationContextUtils.getRequiredWebApplicationContext(scContext).getBean(sBeanName);

//               oService = WebUtils.getBean(request.getSession().getServletContext(), service);

            // getBean을 통해 서비스 객체를 가져옵니다.
            Object service = context.getBean(TestService.class);

            // 가져온 서비스 객체에서 특정 메서드를 가져옵니다.
            Method method = service.getClass().getMethod("testMethod", String.class);

            // 가져온 메서드를 invoke 메서드로 호출합니다.
            method.invoke(service, "Hello, World!");


            // 클래스 이름과 메서드 이름을 문자열로 지정합니다.
//            String className = "MainTest.TestService";
//            String methodName = "testMethod";
//
//            // 지정한 클래스 이름으로 클래스를 로드합니다.
//            Class<?> serviceClass = Class.forName(className);
//
//            // 로드한 클래스의 인스턴스를 생성합니다.
//            Object serviceInstance = serviceClass.getDeclaredConstructor().newInstance();
//
//            // 지정한 메서드 이름으로 메서드를 가져옵니다.
//            Method method = serviceClass.getMethod(methodName, String.class);
//
//            // 가져온 메서드를 invoke 메서드로 호출합니다.
//            method.invoke(serviceInstance, "Hello, World!");


            // getBean을 통해 서비스 객체를 가져온다고 가정합니다.
//            TestService service = new TestService();

            // 가져온 서비스 객체에서 특정 메서드를 가져옵니다.
//            Method method = service.getClass().getMethod("testMethod", String.class);

            // 가져온 메서드를 invoke 메서드로 호출합니다.
//            method.invoke(service, "Hello, World!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}