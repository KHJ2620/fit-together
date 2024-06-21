package controller.feeds;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.FeedDao;
import model.vo.Feed;

@WebServlet("/view")
public class FeedsViewController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			try {
				Feed found = new Feed();
				System.out.println(request.getParameter("no"));
	            if (request.getParameter("no") != null) {
	                int no = Integer.parseInt(request.getParameter("no"));
	                FeedDao feedDao = new FeedDao();
	                found = feedDao.findByNo(no);
	            }
	            request.setAttribute("found", found);
				
				
				request.getRequestDispatcher("/WEB-INF/view/feeds/view.jsp").forward(request, response);
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		
		
		
	}
}
