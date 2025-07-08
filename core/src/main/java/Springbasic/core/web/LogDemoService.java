package Springbasic.core.web;

import Springbasic.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogDemoService {

//    private final ObjectProvider<MyLogger> myLoggerProvider;

    private final MyLogger myLogger;


    public void logic(String testId) {
//        MyLogger myLogger = myLoggerProvider.getObject();
        myLogger.log("service testId : " + testId);
    }
}
