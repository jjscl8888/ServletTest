package com.jjs.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author jjs
 * @Version 1.0 2020/3/21
 */
public class LogFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 获取初始化参数
        String site = filterConfig.getInitParameter("site");
        // 输出初始化参数
        System.out.println("网站名称: " + site);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 输出站点名称
        System.out.println("站点网址：http://www.runoob.com");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
