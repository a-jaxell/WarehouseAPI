package com.warehouseapi.interceptor;

import jakarta.annotation.Priority;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;

import jakarta.interceptor.InvocationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
@LogMethodCalls
public class LogMethodCallsInterceptor {

    Logger logger = LoggerFactory.getLogger(LogMethodCallsInterceptor.class);

    @AroundInvoke
    public Object logMethodEntry(InvocationContext context) throws Exception {
        logger.info("Entering method " + context.getMethod().getName() + " in " +
                context.getMethod().getDeclaringClass());
        return context.proceed();
    }
}
