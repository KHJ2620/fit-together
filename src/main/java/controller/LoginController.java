package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String signupSuccess = request.getParameter("signupSuccess");
        String id = request.getParameter("id");

        if (signupSuccess != null && signupSuccess.equals("true")) {
            request.setAttribute("signupSuccess", true);
            request.setAttribute("id", id);
        }
		
		
		
		request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
	}
}
