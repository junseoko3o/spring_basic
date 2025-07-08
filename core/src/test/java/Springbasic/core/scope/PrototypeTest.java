package Springbasic.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.assertThat;

public class PrototypeTest {

    @Test
    void prototypeBeanFind() {

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeTest.PrototypeBean.class);
        System.out.println("find prototypeBean");
        PrototypeTest.PrototypeBean prototypeBean = ac.getBean(PrototypeTest.PrototypeBean.class);
        System.out.println("find prototypeBean2");
        PrototypeTest.PrototypeBean prototypeBean2= ac.getBean(PrototypeTest.PrototypeBean.class);
        System.out.println("prototypeBean = " + prototypeBean);
        System.out.println("prototypeBean = " + prototypeBean2);

        assertThat(prototypeBean).isNotSameAs(prototypeBean2);

        // 필요하면 직접
        prototypeBean.close();
        prototypeBean2.close();
        ac.close(); // 안됨.
    }

    @Scope("prototype")
    static class PrototypeBean {
        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init");
        }

        @PreDestroy
        public void close() {
            System.out.println("PrototypeBean.close");
        }
    }
}
