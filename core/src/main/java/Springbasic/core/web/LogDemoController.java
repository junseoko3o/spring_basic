package Springbasic.core.web;

import Springbasic.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
    // 프록시 쓰면 사용 x
//    private final ObjectProvider<MyLogger> myLoggerProvider;
    private final MyLogger myLogger;


    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) {
//        MyLogger myLogger = myLoggerProvider.getObject();
        String url = request.getRequestURI();

        System.out.println("myLogger = " + myLogger.getClass());
        myLogger.setRequestURL(url);

        myLogger.log("controller test");

        logDemoService.logic("testId");
        return "ok";
    }
}
