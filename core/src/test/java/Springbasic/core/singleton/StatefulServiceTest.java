package Springbasic.core.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // ThreadA: A 사용자가 10000원 주문
        int userAPrice = statefulService.order("A", 10000);

        // ThreadB: B 사용자가 20000원 주문
        int userBPrice = statefulService2.order("B", 20000);

        // ThreadA: A가 주문 금액 조회
//        int price = statefulService.getPrice();
//        System.out.println(price);

        System.out.println(userAPrice);

//        Assertions.assertThat(statefulService.getPrice()).isEqualTo(20000); // ??
    }

    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}