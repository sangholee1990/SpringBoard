package test.acInfo;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {
    public Map<String, Object> add(Map<String, Object> params) {
        int num1 = (Integer) params.get("num1");
        int num2 = (Integer) params.get("num2");

        Map<String, Object> result = new HashMap<>();
        result.put("result", num1 + num2);
        return result;
    }


    public static void main(String[] args) {

        String sUri = "/json/asdfsadfsadf/sadfsadf/asdfasdf/acInfo/DynamicBean/sayHello.do";

// Define a regex pattern to match the URI
        Pattern pattern = Pattern.compile("^.*/(\\w+)/([^/]+)/([^/]+)\\.do$");
        Matcher matcher = pattern.matcher(sUri);

        if (matcher.matches()) {
            String packageName = matcher.group(1);
            String className = matcher.group(2);
            String methodName = matcher.group(3);

            // Create the fully qualified class name and call the method
            String fullyQualifiedName = packageName + "." + className;
            System.out.println(fullyQualifiedName);
            System.out.println(methodName);
//            Map<String, Object> params = new HashMap<>(); // populate this map as necessary
//            Map<String, Object> result = new DynamicBean().createInstanceAndInvokeMethod(fullyQualifiedName, methodName, params);
        } else {
            System.out.println("URI does not match the expected pattern");
        }


    }

}
