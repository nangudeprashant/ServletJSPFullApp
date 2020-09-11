package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StudentDAO;
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
		if (request.getParameter("getList") != null) {
			List studentList = new StudentDAO().getDatabaseStudentList();
			if (studentList != null) {
				System.out.println("Non Empty list");
				request.setAttribute("studentList", studentList);
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/jsp/StudentList.jsp");
				rd.forward(request, response);
			}else {
				System.out.println("Empty list");
			}
		} else if (request.getParameter("getInfo") != null) {
			Student s = new StudentDAO().getDatabaseStudent(Integer.parseInt(request.getParameter("studentID")));
			System.out.println(s.toString());
			if (s != null) {
				request.setAttribute("Student", s);
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/jsp/StudentInfo.jsp");
				rd.forward(request, response);
			}
		} else if (request.getParameter("insert") != null) {
			System.out.println("Insert button pressed.....");
			try {
				if (new StudentDAO().insertDatabaseStudent(Integer.parseInt(request.getParameter("studentID")),
						request.getParameter("studentName"), request.getParameter("studentAddress"))) {
					System.out.println("Data inserted successfully...........");
					request.setAttribute("successMessage", "Data Inserted Successfully.....");
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/jsp/Success.jsp");
					rd.forward(request, response);
				} else {
					request.setAttribute("errorMessage", "Opps!!!No record with privded details is present");
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/jsp/Error.jsp");
					rd.forward(request, response);
				}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				request.setAttribute("errorMessage",
						"Opps!!!Enter numerical id.\n" + "Here are the details:\n" + e.getMessage());
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/jsp/Error.jsp");
				rd.forward(request, response);
			} catch (SQLException e) {
				request.setAttribute("errorMessage",
						"Opps!!!Error in data insertion.\n" + "Here are the details:\n" + e.getMessage());
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/jsp/Error.jsp");
				rd.forward(request, response);
			}

		} else if (request.getParameter("update") != null) {
			System.out.println("Update button pressed.....");
			try {
				if (new StudentDAO().updateDatabaseStudent(Integer.parseInt(request.getParameter("studentID")),
						request.getParameter("studentName"), request.getParameter("studentAddress"))) {
					System.out.println("Data updated successfully...........");
					request.setAttribute("successMessage", "Data Updated Successfully.....");
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/jsp/Success.jsp");
					rd.forward(request, response);
				} else {
					request.setAttribute("errorMessage", "Opps!!!No record with privded details is present");
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/jsp/Error.jsp");
					rd.forward(request, response);
				}

			} catch (NumberFormatException e) {
				request.setAttribute("errorMessage",
						"Opps!!!Enter numerical id.\n" + "Here are the details:\n" + e.getMessage());
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/jsp/Error.jsp");
				rd.forward(request, response);
				e.printStackTrace();
			} catch (SQLException e) {
				request.setAttribute("errorMessage",
						"Opps!!!Error in data updation.\n" + "Here are the details:\n" + e.getMessage());
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/jsp/Error.jsp");
				rd.forward(request, response);
			}

		} else if (request.getParameter("delete") != null) {
			try {
				{
					System.out.println("Delete button pressed.....");
					if (new StudentDAO().deleteDatabaseStudent(Integer.parseInt(request.getParameter("studentID")))) {
						System.out.println("Data deleted successfully...........");
						request.setAttribute("successMessage", "Data Deleted Successfully.....");
						RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/jsp/Success.jsp");
						rd.forward(request, response);
					} else {
						request.setAttribute("errorMessage", "Opps!!!No record with privded details is present");
						RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/jsp/Error.jsp");
						rd.forward(request, response);
					}

				}
			} catch (NumberFormatException e) {
				request.setAttribute("errorMessage",
						"Opps!!!Enter numerical id.\n" + "Here are the details:\n" + e.getMessage());
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/jsp/Error.jsp");
				rd.forward(request, response);
				e.printStackTrace();
			} catch (SQLException e) {
				request.setAttribute("errorMessage",
						"Opps!!!Error in data Deletion.\n" + "Here are the details:\n" + e.getMessage());
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/jsp/Error.jsp");
				rd.forward(request, response);
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
