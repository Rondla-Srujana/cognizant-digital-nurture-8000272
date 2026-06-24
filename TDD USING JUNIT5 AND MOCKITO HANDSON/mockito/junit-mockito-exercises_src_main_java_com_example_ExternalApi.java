package com.example;

public interface ExternalApi {
    String getData();
    void saveData(String data);
    String getDataWithArg(String key);
    void doSomething() throws Exception;
}