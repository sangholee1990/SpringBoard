package test.controller;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.reflect.Method;

public class DynamicClassCreator {

    public static Object createDynamicClass(String className, String methodName, String returnValue) throws Exception {
        Class<?> dynamicClass = new ByteBuddy()
                .subclass(Object.class)
                .name(className)
                .method(ElementMatchers.named(methodName))
                .intercept(FixedValue.value(returnValue))
                .make()
                .load(DynamicClassCreator.class.getClassLoader())
                .getLoaded();

        // 동적으로 생성한 클래스를 로드합니다.
        Class.forName(className, true, dynamicClass.getClassLoader());

        Object instance = dynamicClass.getDeclaredConstructor().newInstance();
        Method method = dynamicClass.getMethod(methodName);
        System.out.println(method.invoke(instance));
        return instance;
    }

    public static void main(String[] args) throws Exception {
        String className = "test.DynamicBean";
        String methodName = "sayHello";
        String returnValue = "Hello, World!";
        Object instance = createDynamicClass(className, methodName, returnValue);
        System.out.println(instance.toString());
    }
}