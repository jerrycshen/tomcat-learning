package me.shenchao.ex20.standardbean;

/**
 * 要想通过标准MBean来管理一个Java对象，要执行如下步骤:<br>
 *     1. 创建一个接口，该接口的命名规范为：Java类名+MBean后缀
 *     2. 修改Java类，实现MBean接口
 *     3. 创建一个代理，该代理类必须包含一个MBeanServer实例
 *     4. 为MBean创建ObjectName实例
 *     5. 实例化MBeanServer实例
 *     6. 将MBean注册到MBeanServer中
 *
 * 标准MBean的缺点：
 *      必须要修改JAVA类，如果不能修改JAVA类，那么可以使用模型MBean
 */
public interface CarMBean {

    String getColor();

    void setColor(String color);

    void drive();
}
