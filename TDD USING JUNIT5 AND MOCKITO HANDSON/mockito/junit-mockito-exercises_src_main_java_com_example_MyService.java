package com.example;

public class MyService {
    private final ExternalApi externalApi;

    public MyService(ExternalApi externalApi) {
        this.externalApi = externalApi;
    }

    public String fetchData() {
        return externalApi.getData();
    }

    public void processAndSave(String data) {
        externalApi.saveData(data);
    }

    public String fetchDataWithArg(String key) {
        return externalApi.getDataWithArg(key);
    }

    public void executeAction() throws Exception {
        externalApi.doSomething();
    }
}