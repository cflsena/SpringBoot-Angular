package com.example.dev.backend.api.interceptor;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.example.dev.backend.api.exception.customs.NotFoundRequestException;
import com.example.dev.backend.api.utils.GenericUtils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class GenericControllerInterceptor extends HandlerInterceptorAdapter {

	private boolean ignoreEndPoint;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		this.setIgnoreEndPoint(false);

		if (isIgnoreEndPoints(request)) {
			throw new NotFoundRequestException();
		}
		return true;
	}

	private boolean isIgnoreEndPoints(HttpServletRequest request) {

		String servletPath = request.getServletPath();

		Properties prop = new Properties();
		String propFilename = "rest/IgnorePaths.properties";
		InputStream propInputStream = getClass().getClassLoader().getResourceAsStream(propFilename);

		if (GenericUtils.isEmpytOrNull(servletPath)) {
			throw new NotFoundRequestException();
		}

		try {
			prop.load(propInputStream);
		} catch (IOException e) {
			e.printStackTrace();
			throw new NotFoundRequestException();
		}

		List<Entry<Object, Object>> propList = prop.entrySet().stream().collect(Collectors.toList());

		propList.stream().filter(item -> !this.isIgnoreEndPoint()).forEach(item -> {
			if (GenericUtils.isNotEmpytOrNotNull(item.getValue())) {
				String[] value = item.getValue().toString().split(";");
				Pattern p = Pattern.compile(value[1]);
				Matcher m = p.matcher(servletPath);
				if (m.find() && request.getMethod().equals(value[0])) {
					this.setIgnoreEndPoint(true);
				}
			}
		});

		return this.isIgnoreEndPoint();
	}
}
