package com.polarbookshop.dispatcherservice;
import org.springframework.cloud.function.context.FunctionCatalog;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class FunctionInspector {

    private final FunctionCatalog functionCatalog;

    public FunctionInspector(ApplicationContext context) {
        this.functionCatalog = context.getBean(FunctionCatalog.class);
    }

    @PostConstruct
    public void inspectFunctions() {
        System.out.println("Available functions: " + functionCatalog.getNames(null));
    }
}