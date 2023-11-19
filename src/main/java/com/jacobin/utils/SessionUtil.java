package com.jacobin.utils;

import javax.servlet.http.HttpSession;

import com.jacobin.models.User;

public class SessionUtil {

	// Lưu trữ thông tin người dùng đã login vào Session.
	public static void storeLoginedUser(HttpSession session, User loginedUser) {
		// Trên JSP có thể truy cập thông qua ${loginedUser}
		session.setAttribute("loginedUser", loginedUser);
	}

	// Lấy thông tin người dùng lưu trữ trong Session.
	public static User getLoginedUser(HttpSession session) {
		User loginedUser = (User) session.getAttribute("loginedUser");
		return loginedUser;
	}
}
