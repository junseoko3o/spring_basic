package Springbasic.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan( // 스프링 빈으로 자동 등록 해줌.
        // 설정 정보는 컴포넌트스캔 대상에서 제외하기 위함. 현재 코드들에서 충돌. 보통은 할 일 없음.
        basePackages = {"Springbasic.core"},
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

}
