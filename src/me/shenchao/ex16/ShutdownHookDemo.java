package me.shenchao.ex16;

import java.io.IOException;

public class ShutdownHookDemo {

    private static void start() {
        System.out.println("Demo");
        // 实例化关闭钩子
        ShutdownHook hook = new ShutdownHook();
        // 注册关闭钩子
        Runtime.getRuntime().addShutdownHook(hook);
    }

    public static void main(String[] args) throws IOException {
        ShutdownHookDemo demo = new ShutdownHookDemo();
        demo.start();
        System.in.read();
    }
}

class ShutdownHook extends Thread{

    public void run() {
        System.out.println("Shutting down");
    }
}
