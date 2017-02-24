package me.shenchao.ex02;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 一个Servlet对每个HTTP请求都要做如下几件事情：<br>
 *     1. 第一次请求某Servlet时，载入该servlet，并调用init方法
 *     2. 针对每个请求，创建一个ServletRequest and ServletResponse
 *     3. 调用该servlet的service方法，并将request 和 response传入
 *     4. 关闭servlet，调用destroy方法，并卸载该servlet方法
 */
public class PrimitiveServlet implements Servlet {

    public void init(ServletConfig config) throws ServletException {
        System.out.println("init");
    }

    public void service(ServletRequest request, ServletResponse response)
            throws ServletException, IOException {
        System.out.println("from service");
        PrintWriter out = response.getWriter();
        // println 会自动刷新缓存，所以会输出，而print不会输出
        out.println("Hello. Roses are red.");
        out.print("Violets are blue.");
    }

    public void destroy() {
        System.out.println("destroy");
    }

    public String getServletInfo() {
        return null;
    }

    public ServletConfig getServletConfig() {
        return null;
    }

}
