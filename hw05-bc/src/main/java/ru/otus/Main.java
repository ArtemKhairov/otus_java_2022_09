package ru.otus;

public class Main {
    public static void main(String[] args){
        TestLoggingInterface testClass = Ioc.createProxyLog(new MyClass());
        testClass.calculation();
        testClass.calculation(1);
        testClass.calculation(18, 25);
        testClass.calculation(16, 23, 26);
    }
}