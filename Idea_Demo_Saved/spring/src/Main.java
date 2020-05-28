import javafx.application.Application;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public  static void main(String []args){
//        HelloWorld helloWorld=new HelloWorld();
//        helloWorld.setName("Spring");
//        helloWorld.SayHello();
        //创建一个Spring的IOC容器对象
        ApplicationContext context=new ClassPathXmlApplicationContext("spring-config.xml");
        //从容器中提取Bean实例
        HelloWorld helloWorld=(HelloWorld)context.getBean("helloWorld");
        helloWorld.SayHello();
    }
}
