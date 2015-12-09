package de.griesser.rest.logging;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

@Aspect
public class LoggingAspect {

    @Around("execution(* de.griesser.rest.services.*.*(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Logger log = Logger.getLogger(joinPoint.getTarget().getClass());
        String methodAndArguments = getMethodAndArgumentsAsString(joinPoint);
        log.info(methodAndArguments);
        long start = System.currentTimeMillis();
        Object result;
        try {
            result = joinPoint.proceed();
        } catch (Throwable ex) {
            log.error(methodAndArguments + getExceptionAsString(ex, getDuration(start)), ex);
            throw ex;
        }
        log.info(methodAndArguments + getResultAsString(result, getDuration(start)));
        return result;
    }

    protected long getDuration(long start) {
        return System.currentTimeMillis() - start;
    }

    protected String getMethodAndArgumentsAsString(ProceedingJoinPoint joinPoint) {
        return Arrays.stream(joinPoint.getArgs()).map(arg -> arg.toString())
                .collect(Collectors.joining(", ", getMethodName(joinPoint) + "(", ")"));
    }

    protected String getMethodName(ProceedingJoinPoint joinPoint) {
        return MethodSignature.class.cast(joinPoint.getSignature()).getMethod().getName();
    }

    protected String getResultAsString(Object result, long duration) {
        return new StringBuilder(" returned ").append(result).append(" in ").append(duration).append(" msecs")
                .toString();
    }

    protected String getExceptionAsString(Throwable ex, long duration) {
        return new StringBuilder(" threw ").append(ex.getClass().getSimpleName()).append(" after ").append(duration)
                .append(" msecs with message ").append(ex.getMessage()).toString();
    }

}
