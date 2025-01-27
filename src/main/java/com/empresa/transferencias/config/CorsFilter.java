package com.empresa.transferencias.config;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CorsFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(CorsFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        try {
            logger.info("Inicializando CorsFilter...");
        } catch (Exception e) {
            throw new ServletException("Erro ao inicializar o CorsFilter", e);
        }
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String origin = req.getHeader("Origin");
        logger.info("Requisição recebida com origem: {}", origin);

        if (origin != null && res.getHeader("Access-Control-Allow-Origin") == null) {
            res.setHeader("Access-Control-Allow-Origin", origin);
            res.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
            res.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, Accept");
            res.setHeader("Access-Control-Allow-Credentials", "true");
        }

        if ("OPTIONS".equalsIgnoreCase(req.getMethod())) {
            logger.info("CORS preflight request detectada, enviando resposta...");
            res.setStatus(HttpServletResponse.SC_OK);
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        logger.info("Destruindo CorsFilter...");
    }
}
