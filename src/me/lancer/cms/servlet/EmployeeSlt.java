package me.lancer.cms.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.lancer.cms.model.Employee;
import me.lancer.cms.service.EmployeeSrv;

public class EmployeeSlt extends HttpServlet {

	private static final long serialVersionUID = 819624997597986006L;

	public EmployeeSlt() {
		super();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String method = request.getParameter("method");

		if ("add".equalsIgnoreCase(method)) {
			doAdd(request, response);
		} else if ("fetch".equalsIgnoreCase(method)) {
			doFetch(request, response);
		} else if ("modify".equalsIgnoreCase(method)) {
			doModify(request, response);
		} else if ("delete".equalsIgnoreCase(method)) {
			doDelete(request, response);
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String empAccess = request.getParameter("access");
		String empNo = request.getParameter("number");
		String empName = request.getParameter("name");
		String empAddr = request.getParameter("address");
		String empTel = request.getParameter("tel");
		String empEmail = request.getParameter("email");
		if (empAccess.length() > 0 && empNo.length() > 0 && empName.length() > 0 && empAddr.length() > 0
				&& empTel.length() > 0 && empEmail.length() > 0) {
			Employee emp = new Employee();
			emp.setNo(Integer.parseInt(empNo));
			emp.setName(empName);
			emp.setAddr(empAddr);
			emp.setTel(empTel);
			emp.setEmail(empEmail);
			if (new EmployeeSrv().add(emp) == 1) {
				request.setAttribute("error", "添加成功!");
				request.getRequestDispatcher("employee_add.jsp").forward(request, response);
			} else {
				request.setAttribute("error", "添加失败!请检查数据库状态后再提交添加");
				request.getRequestDispatcher("employee_add.jsp").forward(request, response);
			}
		} else {
			request.setAttribute("error", "添加失败!请将信息补充完整后再提交添加");
			request.getRequestDispatcher("employee_add.jsp").forward(request, response);
		}
	}

	public void doFetch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String empAccess = request.getParameter("access");
		String empId = request.getParameter("id");
		String empNo = request.getParameter("number");
		String empName = request.getParameter("name");
		String empAddr = request.getParameter("address");
		String empTel = request.getParameter("tel");
		String empEmail = request.getParameter("email");
		Map<String, String> map = new HashMap<String, String>();
		if (empAccess != null && !empAccess.equals("")) {
			map.put("access", empAccess);
		}
		if (empId != null && !empId.equals("")) {
			map.put("id", empId);
		}
		if (empNo != null && !empNo.equals("")) {
			map.put("number", empNo);
		}
		if (empName != null && !empName.equals("")) {
			map.put("name", empName);
		}
		if (empAddr != null && !empAddr.equals("")) {
			map.put("address", empAddr);
		}
		if (empTel != null && !empTel.equals("")) {
			map.put("tel", empTel);
		}
		if (empEmail != null && !empEmail.equals("")) {
			map.put("email", empEmail);
		}
		List<Employee> empList = new EmployeeSrv().Fetch_(map);
		if (empList.size() > 0) {
			request.setAttribute("error", null);
			request.setAttribute("list", empList);
			request.getRequestDispatcher("employee_fetch.jsp").forward(request, response);
		} else {
			request.setAttribute("error", "未找到符合条件的员工!");
			request.getRequestDispatcher("employee_fetch.jsp").forward(request, response);
		}
	}

	public void doModify(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// String empAccess = request.getParameter("access");
		String empId = request.getParameter("id");
		String empNo = request.getParameter("number");
		String empName = request.getParameter("name");
		String empAddr = request.getParameter("address");
		String empTel = request.getParameter("tel");
		String empEmail = request.getParameter("email");
		Employee emp = new Employee();
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", empId);
		List<Employee> rst = new EmployeeSrv().Fetch_(map);
		if (rst.size() > 0) {
			emp = rst.get(0);
			if (empNo != null && !empNo.equals("")) {
				emp.setNo(Integer.parseInt(empNo));
			}
			if (empName != null && !empName.equals("")) {
				emp.setName(empName);
			}
			if (empAddr != null && !empAddr.equals("")) {
				emp.setAddr(empAddr);
			}
			if (empTel != null && !empTel.equals("")) {
				emp.setTel(empTel);
			}
			if (empEmail != null && !empEmail.equals("")) {
				emp.setEmail(empEmail);
			}
			if (new EmployeeSrv().modify(emp) == 1) {
				request.setAttribute("error", "修改成功!");
				request.getRequestDispatcher("employee_modify.jsp").forward(request, response);
			} else {
				request.setAttribute("error", "修改失败!请检查数据库状态后再提交修改");
				request.getRequestDispatcher("employee_modify.jsp").forward(request, response);
			}
		} else {
			request.setAttribute("error", "修改失败!未找到符合条件的演出厅");
			request.getRequestDispatcher("employee_modify.jsp").forward(request, response);
		}
	}

	public void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String empId = request.getParameter("id");
		Employee emp = new Employee();
		emp.setId(Integer.parseInt(empId));
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", empId);
		List<Employee> rst = new EmployeeSrv().Fetch_(map);
		if (rst.size() > 0) {
			emp = rst.get(0);
			if (new EmployeeSrv().delete(Integer.parseInt(empId)) == 1) {
				request.setAttribute("error", "删除成功!");
				request.getRequestDispatcher("employee_delete.jsp").forward(request, response);
			} else {
				request.setAttribute("error", "删除失败!请检查数据库状态后再提交删除");
				request.getRequestDispatcher("employee_delete.jsp").forward(request, response);
			}
		} else {
			request.setAttribute("error", "删除失败!未找到符合条件的演出厅");
			request.getRequestDispatcher("employee_delete.jsp").forward(request, response);
		}
	}

	public void destroy() {
		super.destroy();
	}
}
