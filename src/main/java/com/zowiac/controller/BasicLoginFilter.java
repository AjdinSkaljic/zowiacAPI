package com.zowiac.controller;

import com.zowiac.model.UserRolesEntity;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.StringTokenizer;

public class BasicLoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String authHeader = request.getHeader("Authorization");
        if (authHeader != null) {
            StringTokenizer st = new StringTokenizer(authHeader);
            if (st.hasMoreTokens()) {
                String basic = st.nextToken();

                if (basic.equalsIgnoreCase("Basic")) {
                    try {
                        String credentials = new String(java.util.Base64.getDecoder().decode(st.nextToken()));
                        int p = credentials.indexOf(":");
                        if (p != -1) {
                            String username = credentials.substring(0, p).trim();
                            String password = credentials.substring(p + 1).trim();
                            request.login(username, password);
                            if (request.isUserInRole(UserRolesEntity.ROLE_LOCKED))
                                unauthorized(response, "User is Locked.");
                            else
                                filterChain.doFilter(servletRequest, servletResponse);
                        } else {
                            unauthorized(response, "Invalid Username or Password");
                        }
                    } catch (UnsupportedEncodingException e) {
                        throw new Error("Couldn't retrieve authentication", e);
                    }
                }
            }
        } else {
            unauthorized(response);
        }
    }

    private void unauthorized(HttpServletResponse response, String message) throws IOException {
        response.setHeader("WWW-Authenticate", "Basic realm=Benutzer");
        response.sendError(401, message);
    }

    private void unauthorized(HttpServletResponse response) throws IOException {
        unauthorized(response, "Unauthorized");
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
