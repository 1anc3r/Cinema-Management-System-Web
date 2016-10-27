package me.lancer.cms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import me.lancer.cms.model.Employee;
import me.lancer.cms.service.EmployeeSrv;

public class LoginSlt extends HttpServlet {

	private static final long serialVersionUID = 4267112301971052433L;

	public LoginSlt() {
		super();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession(true);
		PrintWriter out = response.getWriter();

		Employee employee = null;

		String usrName = request.getParameter("user_name");
		String passWord = request.getParameter("password");

		try {
			if (usrName.length() > 0) {
				List<Employee> empList = new EmployeeSrv().Fetch("(emp_name='" + usrName + "')");
				if (empList.size() > 0) {
					employee = empList.get(0);
					if (employee.getPassword().equals(passWord)) {
						session.setAttribute("error", null);
						session.setAttribute("access", employee.getAccess());
						session.setAttribute("id", employee.getId());
						session.setAttribute("no", employee.getNo());
						session.setAttribute("name", employee.getName());
						session.setAttribute("password", employee.getPassword());
						session.setAttribute("address", employee.getAddr());
						session.setAttribute("telephone", employee.getTel());
						session.setAttribute("email", employee.getEmail());
						response.sendRedirect("main.jsp");
					} else {
						// response.setHeader("refresh", "2;URL=index.jsp");
						// out.println("<script> alert('用户" + usrName +
						// "密码错误！');</script>");
						session.setAttribute("error", "用户" + usrName + "密码错误！");
						response.sendRedirect("index.jsp");
					}
				} else {
					// response.setHeader("refresh", "2;URL=index.jsp");
					// out.println("<script> alert('用户" + usrName +
					// "不存在！');</script>");
					session.setAttribute("error", "用户" + usrName + "不存在！");
					response.sendRedirect("index.jsp");
				}
			} else {
				// response.setHeader("refresh", "2;URL=index.jsp");
				// out.println("<script> alert('数据错误！');</script>");
				session.setAttribute("error", "数据错误！");
				response.sendRedirect("index.jsp");
			}
		} catch (Exception e) {
			// response.setHeader("refresh", "2;URL=index.jsp");
			// out.println("<script> alert('捕获异常" + e.toString() +
			// "！');</script>");
			session.setAttribute("error", "捕获异常" + e.toString() + "！");
			response.sendRedirect("index.jsp");
		}
		out.flush();
		out.close();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void destroy() {
		super.destroy();
	}
}
