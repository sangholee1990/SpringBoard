package test.acInfo;

import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Component
public class DynamicBean {

    private static final long serialVersionUID = 1L;

    //    @Bean
//    public Map sayHello(Map mapData) {
//
//        Map result = new HashMap();
//
////        try {
//
//        mapData.put("key", "asdfsadfasdf");
//        System.out.println(mapData);
//
////        } catch (Exception e) {
////            return WebUtils.errorResult("에러 발생하였습니다.", e.getMessage());
////        }
//
//        return mapData;
//    }


    public Map sayHello(Map mapData) throws Exception {

        Map result = new HashMap();

//        try {
        Map aa = new HashMap();
        aa.put("key", "asdfsadfasdf");
        System.out.println(aa);

        result.put("aa", aa);
        System.out.println(String.format("[CHECK] result : %s", result));

        return result;
    }

    public Map sayHello2(Map mapData) throws RuntimeException, Exception {

        Map result = new HashMap();

//        try {
        Map aa = new HashMap();
        aa.put("key", "asdfsadfasdf");
        System.out.println(aa);

        result.put("aa", aa);
        System.out.println(String.format("[CHECK] result : %s", result));

        throw new RuntimeException("예외 발생");

//        return result;

    }

//    public void sayHello(Map Params) {
//        System.out.println("Hello from DynamicBean!");
//        System.out.println(Params);
//    }

//    public static void createBeanAndInvokeMethod(String className, String methodName) {
//        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Class.forName(className))) {
//            Object dynamicBeanInstance = context.getBean(Class.forName(className));
//
//            Method method = dynamicBeanInstance.getClass().getMethod(methodName);
//            method.invoke(dynamicBeanInstance);
//
//        } catch (ClassNotFoundException e) {
//            System.out.println("The class '" + className + "' does not exist.");
//        } catch (NoSuchMethodException e) {
//            System.out.println("The method '" + methodName + "' does not exist in the class '" + className + "'.");
//        } catch (IllegalAccessException e) {
//            System.out.println("The method '" + methodName + "' cannot be accessed in the class '" + className + "'.");
//        } catch (InvocationTargetException e) {
//            System.out.println("An error occurred while calling the method '" + methodName + "' in the class '" + className + "'.");
//        }
//    }


    public static Map createInstanceAndInvokeMethod(String className, String methodName, Map<String, Object> mapData) throws InvocationTargetException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InstantiationException {
        Class<?> dynamicClass = Class.forName(className);
        Object dynamicBeanInstance = dynamicClass.getDeclaredConstructor().newInstance();
        Method method = dynamicClass.getMethod(methodName, Map.class);
        method.setAccessible(true);
        Map result = (Map) method.invoke(dynamicBeanInstance, mapData);

//        System.out.println(result);
        System.out.println(String.format("[CHECK] result : %s", result.toString()));

        return result;
    }

//    public Result savList(Params paParams) {
//
//        try {
//
//            super.savList(oMeDao, paParams, "grid");
//
//        } catch (RuntimeException e) {
//            super.error(e.getMessage(), e);
//            return super.errorResult(super.getMessage("E0001", null, paParams.getLang()));
//        } catch (Exception e) {
//            super.error(e.getMessage(), e);
//            return super.errorResult(super.getMessage("E0001", null, paParams.getLang()));
//        }
//
//        return super.successResult(super.getMessage("M0001", null, paParams.getLang()));
//
//    }
//

//    public static void createInstanceAndInvokeMethod(String className, String methodName, Map<String, Object> Params) throws InvocationTargetException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InstantiationException {
//        Class<?> dynamicClass = Class.forName(className);
//        Object dynamicBeanInstance = dynamicClass.getDeclaredConstructor().newInstance();
//        Method method = dynamicClass.getMethod(methodName, Map.class);
//        method.setAccessible(true);
//        method.invoke(dynamicBeanInstance, Params);
//    }

//    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InstantiationException {
//        String className = "test.DynamicBean";
//        String methodName = "sayHello";
//        createInstanceAndInvokeMethod(className, methodName);
//    }

//    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InstantiationException {
//        String className = "DynamicBean";
//        String methodName = "sayHello";
//        String packageName = className.getClass().getPackage().getName();
//        System.out.println(packageName);
//        String fullClassName = packageName + "." + className;
//        createInstanceAndInvokeMethod(fullClassName, methodName);
//    }

    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
//        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class)) {
        String className = "acInfo.DynamicBean";
//        String className = "test.dynamicBean";
        String methodName = "sayHello";
//            String fullClassName = context.getAutowireCapableBeanFactory().getBeanClassLoader().loadClass(className).getName();
//            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//            String fullClassName = classLoader.loadClass(className).getName();

        Map<String, Object> mapData = new HashMap<>();
        Map result = (Map) createInstanceAndInvokeMethod(className, methodName, mapData);
        System.out.println(result);


        Map<String, Object> params = new HashMap<>();
        params.put("num1", 5);
        params.put("num2", 3);

        className = "acInfo.Calculator";
        methodName = "add";

        result = new DynamicBean().createInstanceAndInvokeMethod(className, methodName, params);
        System.out.println(result.get("result"));  // Output: 8

//        createInstanceAndInvokeMethod(className, methodName, params);
//        createBeanAndInvokeMethod(className, methodName);

//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
