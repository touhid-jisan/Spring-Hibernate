package com.touhid.didemo;

import com.touhid.didemo.controllers.ConstructorController;
import com.touhid.didemo.controllers.MyController;
import com.touhid.didemo.controllers.PropertyController;
import com.touhid.didemo.controllers.SetterController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DiDemoApplication {

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(DiDemoApplication.class, args);

        System.out.println(context.getBean(ConstructorController.class).sayHello());
        System.out.println(context.getBean(SetterController.class).sayHello());
        System.out.println(context.getBean(PropertyController.class).sayHello());
        System.out.println(context.getBean(MyController.class).sayHello());

    }


}
