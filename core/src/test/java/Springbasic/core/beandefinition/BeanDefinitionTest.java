package Springbasic.core.beandefinition;

import Springbasic.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanDefinitionTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    // GenericXml로도 정보 확인 가능하다.
//    ApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");

    // 빈을 등록하는 방법
    // 1. 팩토리 메서드를 통해 등록 (자바코드) AppConfig에서 팩토리 메서드를 사용해서 등록 -> 외부에서 메서드를 호출해 등록하는 방식
    // 2. 직접 등록 (xml)

    @Test
    @DisplayName("빈 설정 메타 정보 확인")
    void findApplicationBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                System.out.println("beanDefinitionName =" + beanDefinitionName +
                        "\nbeanDefinition =" + beanDefinition
                );
            }
        }
    }
}
