package br.com.welson.clinic.filter;

import br.com.welson.clinic.bean.user.LoginBean;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminLoginFilter implements Filter {

    @Inject
    private LoginBean loginBean;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if(request.getRequestURL().toString().contains("/restricted/admin") && (loginBean.getApplicationUser() == null || loginBean.getApplicationUser().getAdmin() == null)) {
            response.sendRedirect(request.getServletContext().getContextPath() + "/login.xhtml");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
