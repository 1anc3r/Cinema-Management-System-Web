package me.lancer.cms.mobile;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import me.lancer.cms.model.Employee;
import me.lancer.cms.model.Studio;
import me.lancer.cms.service.EmployeeSrv;
import me.lancer.cms.service.StudioSrv;

public class RegisterMbl extends HttpServlet {

	private static final long serialVersionUID = 4267112301971052433L;

	public RegisterMbl() {
		super();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		String empAccess = request.getParameter("access");
		String empNumber = request.getParameter("number");
		String empName = request.getParameter("name");
		String empAddress = request.getParameter("address");
		String empTel = request.getParameter("tel");
		String empEmail = request.getParameter("email");
		String empPassword = request.getParameter("password");
		if (empAccess != null && empNumber != null && empName != null && empAddress != null && empTel != null && empEmail != null && empPassword != null
				&& !empAccess.equals("") && !empNumber.equals("") && !empName.equals("") && !empAddress.equals("") && !empTel.equals("") && !empEmail.equals("") && !empPassword.equals("")) {
			Employee emp = new Employee();
			emp.setAccess(Integer.parseInt(empAccess));
			emp.setNo(Integer.parseInt(empNumber));
			emp.setName(empName);
			emp.setAddr(empAddress);
			emp.setTel(empTel);
			emp.setEmail(empEmail);
			emp.setPassword(empPassword);
			if (new EmployeeSrv().add(emp) == 1) {
				// request.setAttribute("error", "添加成功!");
				// request.getRequestDispatcher("/main/studio/studio_add.jsp").forward(request,
				// response);
				String str = "\"添加成功!\"";
				System.out.println(str);
				request.setAttribute("data", "{\"code\":0,\"data\":" + str + "}");
				request.getRequestDispatcher("/main/mobile_data.jsp").forward(request, response);
			} else {
				// request.setAttribute("error", "添加失败!请检查数据库状态后再提交添加");
				// request.getRequestDispatcher("/main/studio/studio_add.jsp").forward(request,
				// response);
				String str = "\"添加失败!请稍后再提交添加\"";
				System.out.println(str);
				request.setAttribute("data", "{\"code\":1,\"data\":" + str + "}");
				request.getRequestDispatcher("/main/mobile_data.jsp").forward(request, response);
			}
		} else {
			// request.setAttribute("error", "添加失败!请将信息补充完整后再提交添加");
			// request.getRequestDispatcher("/main/studio/studio_add.jsp").forward(request,
			// response);
			String str = "\"添加失败!请将信息补充完整后再提交添加\"";
			System.out.println(str);
			request.setAttribute("data", "{\"code\":2,\"data\":" + str + "}");
			request.getRequestDispatcher("/main/mobile_data.jsp").forward(request, response);
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void destroy() {
		super.destroy();
	}
}
