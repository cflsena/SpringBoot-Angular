package com.example.dev.backend.api.security.token;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.example.dev.backend.api.utils.GenericUtils;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RefreshTokenCookiePreProcessorFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;

		if ("/backend/api/v1/oauth/token".equals(req.getRequestURI()) && "refresh_token".equals(req.getParameter("grant_type"))
				&& GenericUtils.isNotNull(req.getCookies())) {
			for (Cookie cookie : req.getCookies()) {
				if ("refresh_token".equals(cookie.getName())) {
					String refreshToken = cookie.getValue();
					req = new MyServletRequestWrapper(req, refreshToken);
				}
			}
		}
		chain.doFilter(req, response);
	}

}
