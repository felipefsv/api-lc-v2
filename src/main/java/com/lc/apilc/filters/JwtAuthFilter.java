package com.lc.apilc.filters;

import com.lc.apilc.enums.ErrorCodes;
import com.lc.apilc.exception.LcException;
import com.lc.apilc.services.JwtService;
import com.lc.apilc.services.UserDetailService;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthFilter extends OncePerRequestFilter {
    private JwtService jwtService;
    private UserDetailService userDetailService;

    public JwtAuthFilter(JwtService jwtService, UserDetailService userDetailService) {
        this.jwtService = jwtService;
        this.userDetailService = userDetailService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (publicUrl(request)) {
            filterChain.doFilter(request, response);
        } else {
            String authorization = request.getHeader("Authorization");
            if (authorization != null && authorization.startsWith("Bearer")) {
                String token = authorization.split(" ")[1];
                boolean isValid = jwtService.isTokenValid(token);
                if (isValid) {
                    String login = jwtService.getUserLogin(token);
                    UserDetails user = userDetailService.loadUserByUsername(login);
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    filterChain.doFilter(request, response);
                } else {
                    throw new LcException("Token Inválido", ErrorCodes.TOKEN_INVALIDO);
                }
            } else {
                throw new LcException("Authorization não encontrado", ErrorCodes.OPERACAO_ILEGAL);
            }
        }
    }

    private boolean publicUrl(HttpServletRequest request) {
        String path = request.getRequestURI();
        String method = request.getMethod();

        boolean isLogin = path.equals("/api/v2/login") && method.equals("POST");
        boolean isPostUser = path.equals("/api/v2/user") && method.equals("POST");
        boolean isGetDepartment = path.equals("/api/v2/department") && method.equals("GET");
        boolean isPostDepartment = path.equals("/api/v2/department") && method.equals("POST");

        return isLogin || isPostUser || isGetDepartment || isPostDepartment;
    }
}
