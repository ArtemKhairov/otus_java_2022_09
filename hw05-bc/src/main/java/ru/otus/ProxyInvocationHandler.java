package ru.otus;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;

class ProxyInvocationHandler implements InvocationHandler {
    private final TestLoggingInterface clazz;
    private final HashSet<Method> listLogMethod = new HashSet<>();

    ProxyInvocationHandler(TestLoggingInterface clazz) {
        this.clazz = clazz;

        for (Method method : clazz.getClass().getMethods()) {
            if (method.isAnnotationPresent(Log.class))
                listLogMethod.add(getMethodFromInterface(method));
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//        System.out.println(method + "   ____METHOD from interface invoked ");
        if (listLogMethod.contains(method)){
            System.out.printf("executed method: %s, param: %s [class: %s]%n"
                    , method.getName(), Arrays.toString(args), clazz.getClass().getSimpleName());}

        return method.invoke(clazz, args);
    }


    private Method getMethodFromInterface(Method method){
        try{
//            System.out.println(method + "____METHOD from class");
//            System.out.println(method.getName()+ "   ____  " + method.getParameterTypes());
//            System.out.println(TestLoggingInterface.class.getDeclaredMethod());
//            System.out.println(TestLoggingInterface.class.getDeclaredMethod(method.getName(),method.getParameterTypes()) + "   _____DECLARED");
            return TestLoggingInterface.class.getDeclaredMethod(method.getName(),method.getParameterTypes());
        }catch (NoSuchMethodException e){
            throw new RuntimeException(e);
        }
    }

}