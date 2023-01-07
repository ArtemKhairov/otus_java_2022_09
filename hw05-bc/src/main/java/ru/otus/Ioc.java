package ru.otus;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

class Ioc{

    private Ioc(){}

    static TestLoggingInterface createProxyLog(TestLoggingInterface clazz) {
        InvocationHandler handler = new ProxyInvocationHandler(clazz);
        return (TestLoggingInterface) Proxy.newProxyInstance(Ioc.class.getClassLoader(), new Class<?>[]{TestLoggingInterface.class}, handler);
    }

}