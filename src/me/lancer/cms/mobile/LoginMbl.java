package me.lancer.cms.mobile;

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

public class LoginMbl extends HttpServlet {

	private static final long serialVersionUID = 4267112301971052433L;

	public LoginMbl() {
		super();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();

		Employee employee = null;

		String usrName = request.getParameter("username");
		String passWord = request.getParameter("password");

		try {
			if (usrName.length() > 0) {
				List<Employee> empList = new EmployeeSrv().Fetch("(emp_name='" + usrName + "')");
				empList.addAll(new EmployeeSrv().Fetch("(emp_no='" + usrName + "')"));
				empList.addAll(new EmployeeSrv().Fetch("(emp_email='" + usrName + "')"));
				if (empList.size() > 0) {
					employee = empList.get(0);
					if (employee.getPassword().equals(passWord)) {
						// session.setAttribute("error", null);
						// session.setAttribute("access", employee.getAccess());
						// session.setAttribute("id", employee.getId());
						// session.setAttribute("no", employee.getNo());
						// session.setAttribute("name", employee.getName());
						// session.setAttribute("password",
						// employee.getPassword());
						// session.setAttribute("address", employee.getAddr());
						// session.setAttribute("telephone", employee.getTel());
						// session.setAttribute("email", employee.getEmail());
						// response.sendRedirect(request.getContextPath()+"/main/main.jsp");
						// System.out.println(usrName+" "+passWord);
						String str = (session.getId() + usrName + usrName.length()).toUpperCase();
						System.out.println(str);
						mApp.setSessionid(str);
						request.setAttribute("session", str);
						request.setAttribute("data", "{\"code\":0,\"data\":\"" + str + "\"}");
						request.getRequestDispatcher("/main/mobile_data.jsp").forward(request, response);
					} else {
						// session.setAttribute("error", "用户" + usrName +
						// "密码错误！");
						// response.sendRedirect("index.jsp");
						String str = "\"登录失败!用户" + usrName + "密码错误!\"";
						System.out.println(str);
						request.setAttribute("data", "{\"code\":1,\"data\":" + str + "}");
						request.getRequestDispatcher("/main/mobile_data.jsp").forward(request, response);
					}
				} else {
					// session.setAttribute("error", "用户" + usrName + "不存在！");
					// response.sendRedirect("index.jsp");
					String str = "\"登录失败!用户" + usrName + "不存在!\"";
					System.out.println(str);
					request.setAttribute("data", "{\"code\":2,\"data\":" + str + "}");
					request.getRequestDispatcher("/main/mobile_data.jsp").forward(request, response);
				}
			} else {
				// session.setAttribute("error", "数据错误！");
				// response.sendRedirect("index.jsp");
				String str = "\"登录失败!请求数据格式错误!\"";
				System.out.println(str);
				request.setAttribute("data", "{\"code\":3,\"data\":" + str + "}");
				request.getRequestDispatcher("/main/mobile_data.jsp").forward(request, response);
			}
		} catch (Exception e) {
			// session.setAttribute("error", "捕获异常" + e.toString() + "！");
			// response.sendRedirect("index.jsp");
			String str = "\"登录失败!捕获异常" + e.toString() + "!\"";
			System.out.println(str);
			request.setAttribute("data", "{\"code\":4,\"data\":" + str + "}");
			request.getRequestDispatcher("/main/mobile_data.jsp").forward(request, response);
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
