/*package com.abc.termproject.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.abc.termproject.utils.*;

// work in progress, servlet used to handle form data for uploading invoices
@WebServlet("/upload")
public class UploadServlet extends HttpServlet {

	DatabaseUtility dbUtil = new DatabaseUtility();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.getWriter().append("Served at: ").append(req.getContextPath());
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/views/admin.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// stuff will be here or something
		String invoiceId = req.getParameter("invoiceId");
		String userId = req.getParameter("userId");
		String date = req.getParameter("date");
		String prodId = req.getParameter("prodId");
		String quantity = req.getParameter("quantity");
		
		// i had this method adding invoiceId as well but had to change it due to merge conflicts
		dbUtil.insertInvoice(Integer.parseInt(invoiceId), Integer.parseInt(userId), date, Integer.parseInt(prodId), Integer.parseInt(quantity));
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/views/uploadsuccess.jsp");
		dispatcher.forward(req, resp);
	}
}*/
