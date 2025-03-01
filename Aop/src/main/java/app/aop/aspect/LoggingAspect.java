package app.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class LoggingAspect {

    // Advice для логирования перед выполнением метода
    @Before("@within(app.aop.aspect.annotation.CustomLogging) || @annotation(app.aop.aspect.annotation.CustomLogging)")
    public void logBefore(JoinPoint joinPoint) {
        log.info("Вызов метода: {}", joinPoint.getSignature().getName());
    }

    // Advice для логирования после успешного выполнения метода
    @AfterReturning(pointcut = "@within(app.aop.aspect.annotation.CustomLogging) || @annotation(app.aop.aspect.annotation.CustomLogging)", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        log.info("Метод {} завершился успешно с результатом: {}", joinPoint.getSignature().getName(), result);
    }

    // Advice для логирования в случае исключения
    @AfterThrowing(pointcut = "@within(app.aop.aspect.annotation.CustomLogging) || @annotation(app.aop.aspect.annotation.CustomLogging)", throwing = "error")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
        log.error("Метод {} завершился с ошибкой: {}", joinPoint.getSignature().getName(), error.getMessage());
    }

    // Advice для замера времени выполнения метода
    @Around("@within(app.aop.aspect.annotation.CustomLogging) || @annotation(app.aop.aspect.annotation.CustomLogging)")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed(); // Вызов метода
        long totalTime = System.currentTimeMillis() - startTime;

        log.info("Метод {} выполнен за {} миллисекунд", joinPoint.getSignature().getName(), totalTime);
        return result;
    }
}


//    // Advice для логирования перед выполнением метода
//    @Before("execution(* app.aop.service.TaskService.*(..))")
//    public void logBefore(JoinPoint joinPoint) {
//        log.info("Вызов метода: {}", joinPoint.getSignature().getName());
//    }
//
//    // Advice для логирования после успешного выполнения метода
//    @AfterReturning(pointcut = "execution(* app.aop.service.TaskService.*(..))", returning = "result")
//    public void logAfterReturning(JoinPoint joinPoint, Object result) {
//        log.info("Метод {} завершился успешно с результатом: {}", joinPoint.getSignature().getName(), result);
//    }
//
//    // Advice для логирования в случае исключения
//    @AfterThrowing(pointcut = "execution(* app.aop.service.TaskService.*(..))", throwing = "error")
//    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
//        log.error("Метод {} завершился с ошибкой: {}", joinPoint.getSignature().getName(), error.getMessage());
//    }
//
//    // Advice для замера времени выполнения метода
//    @Around("execution(* app.aop.service.TaskService.*(..))")
//    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
//        long startTime = System.currentTimeMillis();
//        Object result = joinPoint.proceed(); // Вызов метода
//        long totalTime = System.currentTimeMillis() - startTime;
//
//        log.info("Метод {} выполнен за {} миллисекунд", joinPoint.getSignature().getName(), totalTime);
//        return result;
//    }

