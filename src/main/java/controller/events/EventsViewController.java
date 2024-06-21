package controller.events;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.EventDao;
import model.dao.GymDao;
import model.dao.ParticipantDao;
import model.dao.UserDao;
import model.vo.Event;
import model.vo.Participant;
import model.vo.User;
import model.vo.complex.ParticipantWithUserDetail;

// @WebServlet("/events/view")
@WebServlet("/events/*")
public class EventsViewController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// System.out.println("/events/*");
//			 int id = Integer.parseInt(request.getParameter("id"));
			String uri = request.getRequestURI();
			// System.out.println(uri);
			int id = Integer.parseInt(uri.substring(uri.lastIndexOf("/") + 1));
			// System.out.println(id);
			EventDao eventDao = new EventDao();
			UserDao userDao = new UserDao();
			Event event = eventDao.findById(id);
			request.setAttribute("e", event);
			GymDao gymDao = new GymDao();
			request.setAttribute("g", gymDao.findById(event.getId()));
			
			ParticipantDao participantDao = new ParticipantDao();
			
			
			Double avgBirth = null;
			double[] genderRatio = null;
			
			avgBirth = userDao.findAvgBirth();
			genderRatio = userDao.findGenderRatio();
			request.setAttribute("avgBirth", avgBirth);
			request.setAttribute("genderRatio", genderRatio);
			
			
			// 현재 로그인하고 있는 사용자가 이 이벤트 참가중인지 확인하려면
			// 이벤트에 참가중인 정보 가지고 와서
			List<ParticipantWithUserDetail> participants = participantDao.findByEventIdWithUserDetail(id);
			
			List<String> userIds = new ArrayList<>();
			for (ParticipantWithUserDetail one : participants) {	// 반복문돌면서
				userIds.add(one.getParticipant().getUserId());	// 참가자 아이디만 추출해서 List에 모으고
			}
			// 로그인하고 있는 사용자 정보, 세션에서 얻어서
			User authUser = (User)request.getSession().getAttribute("authUser");
						
			// 추출된 참가자아이디목록에 이 로그인 유저의 아이디가 있다면..?
			// 만약 로그인이 안되있으면 authUser.getId() 할때 터질꺼임.. 왜 authUser == null
			if(authUser != null && userIds.contains(authUser.getId())) {
					request.setAttribute("flag", true);
				}else {
					request.setAttribute("flag", false);
				}
						
			
			String tab= request.getParameter("tab");
			if(tab == null) {
				request.setAttribute("p", participants);
				request.setAttribute("psize", participants.size());
				request.getRequestDispatcher("/WEB-INF/view/events/view-default.jsp").forward(request, response);
			}else if(tab.equals("comments")) {
				
				
				request.getRequestDispatcher("/WEB-INF/view/events/view-comments.jsp").forward(request, response);
			}
			
			
			
			
			


		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher("/WEB-INF/view/events/error.jsp").forward(request, response);
		}

	}

}
