package com.eLocals.SecurityConfiguration;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler{
	
	public void onAuthenticationSuccess(HttpServletRequest request,HttpServletResponse response,Authentication authentication) throws IOException
	{
		System.out.println("inside onauthenticationsuccses");
		request.getSession(false).setMaxInactiveInterval(30);
		response.sendRedirect("/welcome");
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			org.springframework.security.core.Authentication authentication) throws IOException, ServletException {
		
		
	}

}
