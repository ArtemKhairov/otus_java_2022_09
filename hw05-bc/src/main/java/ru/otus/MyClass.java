package ru.otus;

import ru.otus.Log;
import ru.otus.TestLoggingInterface;

public class MyClass implements TestLoggingInterface {

    public void calculation(){
        System.out.println("MyClass executed method calculation without param");
    }

    @Log
    public void calculation(int param1){
        System.out.printf("MyClass executed method calculation, Param: %d%n", param1);
    }
    @Log
    public void calculation(int param1, int param2){
        System.out.printf("MyClass executed method calculation, Params: %d %d%n", param1 , param2);
    }
//    @Log
    public void calculation(int param1, int param2, int param3){
        System.out.printf("MyClass executed method calculation, Params: %d %d %d%n", param1 , param2, param3);
    }
}