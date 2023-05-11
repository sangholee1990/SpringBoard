package test;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Component
public class DynamicBeanRegistrationExample2 {
    public static void registerAndInvokeDynamicBean(String className, String methodName) {

        // AnnotationConfigApplicationContext를 초기화합니다.
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext()) {

            // BeanDefinitionRegistry를 얻어옵니다.
            BeanDefinitionRegistry registry = context;

            // BeanDefinition을 생성합니다.
            GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
//            beanDefinition.setBeanClass(Class.forName(className));
            beanDefinition.setBeanClass(DynamicBean.class);
            beanDefinition.setScope(GenericBeanDefinition.SCOPE_SINGLETON);

            // BeanDefinition을 등록합니다.
            registry.registerBeanDefinition("dynamicBean", beanDefinition);

            // ApplicationContext를 refresh합니다.
            context.refresh();

            // 동적으로 등록한 Bean을 사용합니다.
//            Object dynamicBean = context.getBean(DynamicBean.class);
            Object dynamicBean = context.getBean("dynamicBean");
            Method method = dynamicBean.getClass().getMethod(methodName);
            method.invoke(dynamicBean);

//        } catch (ClassNotFoundException e) {
//            System.out.println("The class '" + className + "' does not exist.");
        } catch (NoSuchMethodException e) {
            System.out.println("The '" + methodName + "' method does not exist in the class '" + className + "'.");
        } catch (IllegalAccessException e) {
            System.out.println("The '" + methodName + "' method cannot be accessed in the class '" + className + "'.");
        } catch (InvocationTargetException e) {
            System.out.println("An error occurred while calling the '" + methodName + "' method in the class '" + className + "'.");
        }
    }

    public static void main(String[] args) {
        String className = "DynamicBean";
        String methodName = "sayHello";
        registerAndInvokeDynamicBean(className, methodName);
    }
}
