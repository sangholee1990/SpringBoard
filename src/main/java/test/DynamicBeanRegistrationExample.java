package test;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Component
public class DynamicBeanRegistrationExample {
    public static void registerAndInvokeDynamicBean(String className, String methodName) {

        // AnnotationConfigApplicationContext를 초기화합니다.
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext()) {

            // 컨텍스트를 초기화하기 위해 refresh를 호출합니다.
//            context.refresh();

            // 동적으로 등록한 Bean을 사용합니다.
            Object dynamicBean = context.getBean(className);
            Method method = dynamicBean.getClass().getMethod(methodName);
            method.invoke(dynamicBean);

        } catch (NoSuchMethodException e) {
            System.out.println("The '" + methodName + "' method does not exist in the class '" + className + "'.");
        } catch (IllegalAccessException e) {
            System.out.println("The '" + methodName + "' method cannot be accessed in the class '" + className + "'.");
        } catch (InvocationTargetException e) {
            System.out.println("An error occurred while calling the '" + methodName + "' method in the class '" + className + "'.");
        } catch (NoSuchBeanDefinitionException e) {
            System.out.println("No bean named '" + className + "' available");
        }
    }

    public static void main(String[] args) {
        String className = "test.DynamicBean";
        String methodName = "sayHello";
        registerAndInvokeDynamicBean(className, methodName);
    }
}
