package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StudentUtil;
import model.Student;

/**
 * Servlet implementation class StudentController
 */
@WebServlet(name = "StudentServlet", urlPatterns = { "/studentInfo" }, loadOnStartup = 1)
public class StudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StudentController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getParameter("getInfo") != null) {
			Student s = new StudentUtil().getDatabaseStudent(Integer.parseInt(request.getParameter("studentID")));
			System.out.println(s.toString());
			if (s != null) {
				request.setAttribute("Student", s);
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/jsp/StudentInfo.jsp");
				rd.forward(request, response);
			}
		} else if (request.getParameter("insert") != null) {
			System.out.println("Insert button pressed.....");
			if (new StudentUtil().insertDatabaseStudent(Integer.parseInt(request.getParameter("studentID")),
					request.getParameter("studentName"), request.getParameter("studentAddress"))) {
				System.out.println("Data inserted successfully...........");
			}

		} else if (request.getParameter("update") != null) {
			System.out.println("Update button pressed.....");
			if (new StudentUtil().updateDatabaseStudent(Integer.parseInt(request.getParameter("studentID")),
					request.getParameter("studentName"), request.getParameter("studentAddress"))) {
				System.out.println("Data updated successfully...........");
			}

		} else if (request.getParameter("delete") != null) {
			System.out.println("Delete button pressed.....");
			if (new StudentUtil().deleteDatabaseStudent(Integer.parseInt(request.getParameter("studentID")))) {
				System.out.println("Data deleted successfully...........");
			}

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
	}

}
