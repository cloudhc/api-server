package com.bobslab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.util.Map;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * Created by bast on 2016-04-19.
 */
public class ServiceDispatcher {
    private static ApplicationContext springContext;

    @Autowired
    public void init(ApplicationContext springContext) {
        ServiceDispatcher.springContext = springContext;
    }

    protected Logger logger = LogManager.getLogger(this.getClass());

    public static ApiRequest dispatch(Map<String, String> requestMap) {
        String serviceUri = requestMap.get("REQUEST_URI");
        String beanName = null;

        // Continue
    }
}
