package me.shenchao.ex20.modelbean;


/**
 * 对于模型Bean，不需要像标准MBean那样，编写一个接口，然后实现该接口，只需要实例化RequiredMBean
 */
public class Car {

    private String color = "red";

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void drive() {
        System.out.println("Baby you can drive my car.");
    }
}
