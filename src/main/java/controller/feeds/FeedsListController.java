package controller.feeds;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.FeedDao;
import model.vo.Feed;

@WebServlet("/list")
public class FeedsListController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			FeedDao feedDao = new FeedDao();
			List<Feed> feeds = feedDao.findAll();
			request.setAttribute("feeds", feeds);
			
			request.getRequestDispatcher("/WEB-INF/view/feeds/list.jsp").forward(request, response);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
}
