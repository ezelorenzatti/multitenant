package br.lorenzatti.springcloundmultitenant.multitenancy.interceptor;

import br.lorenzatti.springcloundmultitenant.multitenancy.resolver.TenantIdentifierResolver;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class TenantInterceptor implements HandlerInterceptor {

    @Autowired
    TenantIdentifierResolver tenantIdentifierResolver;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String tenantId = request.getHeader("X-TENANT-ID");
        if (!StringUtils.hasText(tenantId)) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return false;
        }
        tenantIdentifierResolver.setCurrentTenant(tenantId);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
