package com.example.dev.backend.api.security.token;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
public class RefreshTokenPostProcessor implements ResponseBodyAdvice<OAuth2AccessToken>{

	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		return returnType.getMethod().getName().equals("postAccessToken");
	}

	@Override
	public OAuth2AccessToken beforeBodyWrite(OAuth2AccessToken body, MethodParameter returnType,
			MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType,
			ServerHttpRequest request, ServerHttpResponse response) {
		
		String refreshToken = body.getRefreshToken().getValue();

		HttpServletRequest req = ((ServletServerHttpRequest) request).getServletRequest();
		HttpServletResponse resp = ((ServletServerHttpResponse) response).getServletResponse();
		
		DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) body;
		
		this.addRefreshTokenCookie(refreshToken, req, resp);
		this.removeRefreshTokenFromBody(token);
		
		return body;
	}

	private void removeRefreshTokenFromBody(DefaultOAuth2AccessToken token) {
		token.setRefreshToken(null);
	}

	private void addRefreshTokenCookie(String refreshToken, HttpServletRequest request, HttpServletResponse response) {
		Cookie cookie = new Cookie("refresh_token", refreshToken);
		cookie.setHttpOnly(true);
		cookie.setSecure(false);
		cookie.setPath(request.getContextPath() + "/oauth/token");
		cookie.setMaxAge(2592000);
		response.addCookie(cookie);
	}

}
