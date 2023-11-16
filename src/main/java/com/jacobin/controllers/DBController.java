package com.jacobin.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jacobin.data.DBUtil;

@WebServlet(urlPatterns = { "/createdb" })
public class DBController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		
		resp.setContentType("text/html");

		PrintWriter writer = resp.getWriter();

		writer.println("<h1>Created Database</h1>");

		writer.close();
	}
}
