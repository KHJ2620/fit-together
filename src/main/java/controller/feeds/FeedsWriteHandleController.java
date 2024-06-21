package controller.feeds;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.FeedDao;
import model.vo.Feed;
import model.vo.User;

@WebServlet("/write-handle")
public class FeedsWriteHandleController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		String title = request.getParameter("title");
		String body = request.getParameter("body");
		String category = request.getParameter("category");
		User authUser = (User) request.getSession().getAttribute("authUser");
		Feed one = new Feed(0, authUser.getName(), title, body, new Date(System.currentTimeMillis()),0, category);
		FeedDao feedDao = new FeedDao();
		
			boolean r = feedDao.save(one);
		if(r) {
			request.getRequestDispatcher("/WEB-INF/view/feeds/list.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/WEB-INF/view/feeds/write.jsp").forward(request, response);
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	}
}
