package controller.feeds;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.vo.User;

@WebServlet("/write")
public class FeedsWriteController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User authUser = (User)request.getSession().getAttribute("authUser");
		
		if(authUser == null) {
			request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/WEB-INF/view/feeds/write.jsp").forward(request, response);
		}
		
		
	}

}
