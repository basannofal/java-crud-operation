package com.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.dbOperation;
import com.user.User;

@WebServlet("/")
public class webservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private dbOperation dbo;

	public webservlet() {
		super();
		this.dbo = new dbOperation();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getServletPath();
		System.out.print(path);

		switch (path) {
		case "/new":
			showNewForm(request, response);
			break;

		case "/insert":
			try {
				insertuser(request, response);
			} catch (ServletException | IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "/delete":
			try {
				deleteuser(request, response);
			} catch (ServletException | IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "/edit":
			showEditform(request, response);
			break;
//       	 
		case "/update":
			try {
				updateuser(request, response);
			} catch (ServletException | IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
//       	 
		default:

			try {
				listUser(request, response);
			} catch (ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}

	}

	private void listUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<User> users = dbo.AllUsers();
		request.setAttribute("users", users);
		RequestDispatcher dispatcher = request.getRequestDispatcher("list.jsp");
		dispatcher.forward(request, response);
	}

	private void updateuser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		// TODO Auto-generated method stub
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String number = request.getParameter("number");
		String city = request.getParameter("city");
		int id = Integer.parseInt(request.getParameter("id"));
		User userdata = new User(id,name, email, number, city);
		dbo.updateUser(userdata);
		response.sendRedirect("list");
	}

	private void showEditform(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		User user = dbo.selectUser(id);
		request.setAttribute("user", user);
		RequestDispatcher dispatcher = request.getRequestDispatcher("form.jsp");
		dispatcher.forward(request, response);
	}

	private void deleteuser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		dbo.deleteUser(id);
		response.sendRedirect("list");

	}

	private void insertuser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		// TODO Auto-generated method stub

		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String number = request.getParameter("number");
		String city = request.getParameter("city");
		User userdata = new User(name, email, number, city);
		dbo.insertUser(userdata);
		response.sendRedirect("list");

	}

	protected void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("form.jsp");
		dispatcher.forward(request, response);
	}

}
