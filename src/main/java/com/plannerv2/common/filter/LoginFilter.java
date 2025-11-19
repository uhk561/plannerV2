package com.plannerv2.common.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LoginFilter implements Filter {

    @Override               // 요청정보              응답 정보                다음 필터 or 서블릿 실행
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        // 변환
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String path = req.getRequestURI();  //요청 경로
        HttpSession session = req.getSession(false); // 세션이 없으면 null // true로 넣으면 새 세션 생성

        // 로그인 제외
        if (path.equals("/user") && req.getMethod().equals("POST")) {
            chain.doFilter(request, response);
            return;
        }
        // 회원가입 제외
        if (path.equals("/user/signIn") && req.getMethod().equals("POST")) {
            chain.doFilter(request, response);
            return;
        }

        // 세션 체크
        if (session == null || session.getAttribute("signInUser") == null) {
            res.setStatus(HttpServletResponse.SC_FORBIDDEN);
            res.setContentType("application/json");
            res.getWriter().write("{\"code\":403, \"error_message\":\"로그인이 필요하니 로그인해줘.\"}");
            return;
        }

        // 인증이 성공하면 컨트롤러 전달
        chain.doFilter(request, response);
    }
}

