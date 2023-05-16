package test.controller;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DynamicBeanRegister {
    public static void main(String[] args) {
        // ApplicationContext 생성
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext();

        // BeanDefinitionRegistry를 가져옵니다.
        BeanDefinitionRegistry beanDefinitionRegistry = (BeanDefinitionRegistry) context.getBeanFactory();

        // BeanDefinition을 생성합니다.
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(MyService.class);
        BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();

        // BeanDefinition을 등록합니다.
        beanDefinitionRegistry.registerBeanDefinition("myService", beanDefinition);

        // 등록된 빈을 가져와 사용합니다.
        MyService myService = context.getBean(MyService.class);
        myService.myMethod();
    }

    public static class MyService {
        public void myMethod() {
            System.out.println("Dynamic bean method is called");
        }
    }
}
