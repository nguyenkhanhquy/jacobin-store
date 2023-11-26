package com.jacobin.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.jacobin.dao.UserDB;
import com.jacobin.models.User;
import com.jacobin.utils.CookieUtil;
import com.jacobin.utils.SessionUtil;

@WebFilter(urlPatterns = { "/*" })
public class CookieFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// Ép kiểu ServletRequest thành HttpServletRequest
		HttpServletRequest req = (HttpServletRequest) request;
		
		// Lấy đối tượng HttpSession từ request
		HttpSession session = req.getSession();
		User userInSession = SessionUtil.getLoginedUser(session);
		
		if (userInSession != null) {
			session.setAttribute("COOKIE_CHECKED", "CHECKED");
			chain.doFilter(request, response);
			return;
		}

		// Cờ (flag) để kiểm tra Cookie.
		String checked = (String) session.getAttribute("COOKIE_CHECKED");
		if (checked == null) {
			String userName = CookieUtil.getUserNameInCookie(req);
			
			if (userName != null) {
	            User user = UserDB.selectUserByUserName(userName);
	            SessionUtil.storeLoginedUser(session, user);
	        }
			
			// Đánh dấu đã kiểm tra Cookie.
			session.setAttribute("COOKIE_CHECKED", "CHECKED");
		}

		chain.doFilter(request, response);
	}
}
