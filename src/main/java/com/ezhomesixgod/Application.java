package com.ezhomesixgod;

import com.ezhomesixgod.core.BeanFactory;
import com.ezhomesixgod.core.JsonApplicationContext;
import com.ezhomesixgod.entity.Robot;

/**
 * @author renyang
 * @description
 * @createTime 2018-09-27 9:30
 */
public class Application {
    public static void main(String[] args) {
        BeanFactory beanFactory =new JsonApplicationContext("application.json");

        try {
            Robot robot = (Robot) beanFactory.getBean("robot");
            robot.say();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
