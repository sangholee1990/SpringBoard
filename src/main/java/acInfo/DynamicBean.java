package acInfo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Component
public class DynamicBean {

    //    @Bean
    public void sayHello() {
        System.out.println("Hello from DynamicBean!");
    }

    public static void createBeanAndInvokeMethod(String className, String methodName) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Class.forName(className))) {
            Object dynamicBeanInstance = context.getBean(Class.forName(className));

            Method method = dynamicBeanInstance.getClass().getMethod(methodName);
            method.invoke(dynamicBeanInstance);

        } catch (ClassNotFoundException e) {
            System.out.println("The class '" + className + "' does not exist.");
        } catch (NoSuchMethodException e) {
            System.out.println("The method '" + methodName + "' does not exist in the class '" + className + "'.");
        } catch (IllegalAccessException e) {
            System.out.println("The method '" + methodName + "' cannot be accessed in the class '" + className + "'.");
        } catch (InvocationTargetException e) {
            System.out.println("An error occurred while calling the method '" + methodName + "' in the class '" + className + "'.");
        }
    }


    public static void createInstanceAndInvokeMethod(String className, String methodName) throws InvocationTargetException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InstantiationException {
        Class<?> dynamicClass = Class.forName(className);
        Object dynamicBeanInstance = dynamicClass.getDeclaredConstructor().newInstance();

        Method method = dynamicClass.getMethod(methodName);
        method.invoke(dynamicBeanInstance);
    }

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
        String className = "test.DynamicBean";
//        String className = "test.dynamicBean";
        String methodName = "sayHello";
//            String fullClassName = context.getAutowireCapableBeanFactory().getBeanClassLoader().loadClass(className).getName();
//            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//            String fullClassName = classLoader.loadClass(className).getName();

        createInstanceAndInvokeMethod(className, methodName);
        createBeanAndInvokeMethod(className, methodName);

//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
