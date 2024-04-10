package com.example.Homework_Eight.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

@Aspect
@Component
public class LogAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


//
//    /*
//     Используем Pointcut — запрос точек присоединения (указываем в срезе все методы класса
//     NoteService расположенного в com.example.Homework_Eight.service)
//     */
//
//    @Pointcut("execution(public * com.example.Homework_Eight.service.NoteService.*(..))")
//    public void LogMethod() {
//    }
//
//    @Around("LogMethod()")
//    public Object logMethod(ProceedingJoinPoint joinPoint) throws Throwable {
//        System.out.println("Пользователь вызвал метод "
//                + joinPoint.getSignature().getName() + ".");
//        Object proceed = joinPoint.proceed();
//        System.out.println("Метод завершил работу.");
//        return proceed;
//    }
//
//    @Before("LogMethod()")
//    public void beforeCallAtMethod1(JoinPoint jp) {
//        String args = Arrays.stream(jp.getArgs())
//                .map(Object::toString)
//                .collect(Collectors.joining(","));
//        logger.info("before " + jp.toString() + ", args=[" + args + "]");
//    }
//
//    @After("LogMethod()")
//    public void afterCallAt(JoinPoint jp) {
//        logger.info("after " + jp.toString());
//    }
//



   /*
    Используем для логирования аннотацию TrackUserAction
   */

    @Around(value = "@annotation(TrackUserAction)")
    public Object logMethodAnnotation(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Пользователь вызвал метод "
                + joinPoint.getSignature().getName() + ".");
        Object proceed = joinPoint.proceed();
        System.out.println("Метод завершил работу.");
        return proceed;
    }

}
