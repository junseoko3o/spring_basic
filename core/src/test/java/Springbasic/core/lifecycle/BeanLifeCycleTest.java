package Springbasic.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest() {
        ConfigurableApplicationContext applicationContext = new AnnotationConfigApplicationContext(LiceCycleConfig.class);
        NetworkClient client = applicationContext.getBean(NetworkClient.class);
        applicationContext.close();

    }

    @Test
    public void lifeCycleTest2() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LiceCycleConfig2.class);
        NetworkClientInterface client = ac.getBean(NetworkClientInterface.class);
        ac.close();

    }

    @Test
    public void lifeCycleTest3() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LiceCycleConfig3.class);
        NetworkClientMethod client = ac.getBean(NetworkClientMethod.class);
        ac.close();
    }

    @Test
    public void lifeCycleTest4() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LiceCycleConfig4.class);
        NetworkClientAnno client = ac.getBean(NetworkClientAnno.class);
        ac.close();
    }


    @Configuration
    static class LiceCycleConfig {
         @Bean
         public NetworkClient networkClient() {
             NetworkClient networkClient = new NetworkClient();
             networkClient.setUrl("http://localhost:8080");
             return networkClient;
         }
    }

    @Configuration
    static class LiceCycleConfig2 {
        @Bean
        public NetworkClientInterface networkClient() {
            NetworkClientInterface networkClient = new NetworkClientInterface();
            networkClient.setUrl("http://localhost:8080");
            return networkClient;
        }
    }

    @Configuration
    static class  LiceCycleConfig3 {
        @Bean(initMethod = "init", destroyMethod = "close")
        public NetworkClientMethod networkClient() {
            NetworkClientMethod networkClient = new NetworkClientMethod();
            networkClient.setUrl("http://localhost:8080");
            return networkClient;
        }
    }

    @Configuration
    static class  LiceCycleConfig4 {
        @Bean
        public NetworkClientAnno networkClient() {
            NetworkClientAnno networkClient = new NetworkClientAnno();
            networkClient.setUrl("http://localhost:8080");
            return networkClient;
        }
    }
}
