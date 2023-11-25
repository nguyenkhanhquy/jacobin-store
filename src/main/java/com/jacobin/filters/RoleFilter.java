package com.jacobin.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jacobin.models.User;
import com.jacobin.utils.SessionUtil;

@WebFilter(urlPatterns = { "/admin/*" })
public class RoleFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// Ép kiểu ServletRequest thành HttpServletRequest
		HttpServletRequest req = (HttpServletRequest) request;

		// Ép kiểu ServletResponse thành HttpServletResponse
		HttpServletResponse resp = (HttpServletResponse) response;

		// Lấy đối tượng HttpSession từ request
		HttpSession session = req.getSession();

		User userInSession = SessionUtil.getLoginedUser(session);

		if (userInSession != null) {
			if (userInSession.getRole().getRoleId() != 1) {
				resp.sendRedirect(req.getContextPath() + "/home");
			} else {
				chain.doFilter(request, response);
			}
		} else {
			resp.sendRedirect(req.getContextPath() + "/home");
		}
	}
}
