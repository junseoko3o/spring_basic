package Springbasic.core.singleton;

public class SingletonService {

    private static final SingletonService instance = new SingletonService();

    // JVM에서 자바가 뜰 때 객체를 생성해서 instance에 참조로 넣어둠.
    public static SingletonService getInstance() {
        return instance;
    }

    // 다른곳에서 생성 못하게 private
    private SingletonService() {
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
