package ua.pp.darknsoft;

import org.springframework.beans.factory.BeanFactory;

public class Main {
    public static void main(String[] args) {
        System.out.println("hello");
        BeanFactory beanFactory = new BeanFactory();
        beanFactory.instantiate("ua.pp.darknsoft");
    }
}
