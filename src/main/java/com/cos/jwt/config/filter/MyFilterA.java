package com.cos.jwt.config.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MyFilterA implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        if (req.getMethod().equals("POST")) {
            String headerAuth = req.getHeader("Authorization");

            // 토큰이 만들어졌다고 가정, 토큰이 없거나 이상하다면 컨트롤러 진입 불가토록 로직 작성
            if (headerAuth.equals("token")) {
                System.out.println("Filter A");
                chain.doFilter(req, res);
            }
        } else {
            PrintWriter out = res.getWriter();
            out.println("토큰인증 불가");
        }
    }
}
