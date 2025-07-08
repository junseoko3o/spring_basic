package Springbasic.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class SingletonWithPrototypeTest2 {

    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonWithPrototypeTest2.PrototypeBean.class);
        SingletonWithPrototypeTest2.PrototypeBean prototypeBean = ac.getBean(SingletonWithPrototypeTest2.PrototypeBean.class);
        prototypeBean.addCount();
        Assertions.assertThat(prototypeBean.getCount()).isEqualTo(1);

        SingletonWithPrototypeTest2.PrototypeBean prototypeBean2 = ac.getBean(SingletonWithPrototypeTest2.PrototypeBean.class);
        prototypeBean2.addCount();
        Assertions.assertThat(prototypeBean2.getCount()).isEqualTo(1);

        ac.close();
    }

    @Test
    void singletonClientUsesPrototype() {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

        ClientBean client1 = context.getBean(ClientBean.class);
        int count1 = client1.logic();
        Assertions.assertThat(count1).isEqualTo(1);

        ClientBean client2 = context.getBean(ClientBean.class);
        int count2 = client2.logic();
        Assertions.assertThat(count2).isEqualTo(1);

        context.close();
    }

    @Scope("singleton")
    static class ClientBean {

        // javax.inject.Provider 사용시
        // private Provider<PrototypeBean> provider;
        private final ObjectProvider<PrototypeBean> prototypeBeanObjectProvider;

        public ClientBean(ObjectProvider<PrototypeBean> prototypeBeanObjectProvider) {
            this.prototypeBeanObjectProvider = prototypeBeanObjectProvider;
        }

        public int logic() {
            PrototypeBean prototypeBean = prototypeBeanObjectProvider.getObject();
            // javax.inject.Provider 사용시
            // PrototypeBean prototypeBean = provider.get();
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }

    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init" + this);
        }

        @PreDestroy
        public void close() {
            System.out.println("PrototypeBean.close" + this);
        }
    }
}
