package com.cos.jwt.config.filter;

import javax.servlet.*;
import java.io.IOException;

public class MyFilterB implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("Filter B");
        chain.doFilter(request, response);
    }
}
