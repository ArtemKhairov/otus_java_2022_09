import annotations.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TestRunner {
    private List<Method> before = new ArrayList<>();
    private List<Method> test = new ArrayList<>();
    private List<Method> after = new ArrayList<>();
    private int success = 0;
    private int fail = 0;
    private Class<?> clazz;

    public TestRunner(Class<?> clazz){
        this.clazz = clazz;
    }

    public void runner(){
        System.out.println("simpleName:" + clazz.getSimpleName());
        System.out.println("canonicalName:" + clazz.getCanonicalName());
        getMethods();

        for (Method testMethod: test) {
            System.out.println("--- Executing methods of MyTest class ---");
            execute(testMethod);
        }

        System.out.println(String.format("Total: %d, success: %d, fail: %d", test.size(), success, fail));
    }

    private void getMethods(){
//        System.out.println(Arrays.stream(clazz.getDeclaredAnnotations()).toList());
        for(Method method: clazz.getDeclaredMethods()){
            if(method.isAnnotationPresent(Before.class)){
                before.add(method);
            }else if(method.isAnnotationPresent(Test.class)){
                test.add(method);
            }else if(method.isAnnotationPresent(After.class)){
                after.add(method);
            }
        }
    }

    private void execute( Method testMethod){
        Object instanceClass  = ReflectionHelper.instantiate(clazz);
        try{
            before.forEach( beforeMethod ->
                    ReflectionHelper.callMethod(instanceClass, beforeMethod.getName())
            );

            ReflectionHelper.callMethod(instanceClass, testMethod.getName());
            System.out.println("Test succeeded: " + testMethod.getName());
            success++;
        }
        catch (Exception ex){
            System.out.println("Test failed: " + testMethod.getName());
//            System.out.println("Message of Exception: " + ex.getCause());
            System.out.println("Message of Exception: " + ex.getMessage());
            fail++;
        }
        finally {
            after.forEach( afterMethod ->
                    ReflectionHelper.callMethod(instanceClass, afterMethod.getName())
            );
        }
    }
}