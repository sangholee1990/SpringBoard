package test;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Configuration
public class Application {
    public static void registerAndInvokeDynamicBean(String beanName, String methodName) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext()) {
//            BeanDefinitionRegistry registry = (BeanDefinitionRegistry) context;
//
//            GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
//            beanDefinition.setBeanClass(DynamicBean.class);
//            beanDefinition.setScope(GenericBeanDefinition.SCOPE_SINGLETON);
//
//            registry.registerBeanDefinition(beanName, beanDefinition);
//
//            context.refresh();

            Object dynamicBean = context.getBean(beanName);
//            Object dynamicBean = context.getBean(Application.class);
            Method method = dynamicBean.getClass().getMethod(methodName);
            method.invoke(dynamicBean);

        } catch (NoSuchMethodException e) {
            System.out.println("The method '" + methodName + "' does not exist.");
        } catch (IllegalAccessException e) {
            System.out.println("The method '" + methodName + "' cannot be accessed.");
        } catch (InvocationTargetException e) {
            System.out.println("An error occurred while calling the method '" + methodName + "'.");
        }
    }

    public static void main(String[] args) {
        registerAndInvokeDynamicBean("test.DynamicBean", "sayHello");
//        registerAndInvokeDynamicBean("test.dynamicBean", "sayHello");
    }
}
