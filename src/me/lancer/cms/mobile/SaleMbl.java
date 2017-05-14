package me.lancer.cms.mobile;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import me.lancer.cms.model.Studio;
import me.lancer.cms.service.StudioSrv;

public class SaleMbl extends HttpServlet {

	private static final long serialVersionUID = 819624997597986006L;

	public SaleMbl() {
		super();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String method = request.getParameter("method");
		String session = request.getParameter("session");
		if (session != null) {
			session = session.substring(0, session.length() - (session.charAt(session.length() - 1) - '0') - 1);
			if (request.getRequestedSessionId().equals(session)) {
				if ("add".equalsIgnoreCase(method)) {
					doAdd(request, response);
				} else if ("fetch".equalsIgnoreCase(method)) {
					doFetch(request, response);
				} else if ("modify".equalsIgnoreCase(method)) {
					doModify(request, response);
				} else if ("delete".equalsIgnoreCase(method)) {
					doDelete(request, response);
				}
			} else {
				String str = "\"session错误!\"";
				System.out.println(str);
				request.setAttribute("data", "{\"data\":" + str + "}");
				request.getRequestDispatcher("/main/mobile_data.jsp").forward(request, response);
			}
		} else {
			String str = "\"session错误!\"";
			System.out.println(str);
			request.setAttribute("data", "{\"data\":" + str + "}");
			request.getRequestDispatcher("/main/mobile_data.jsp").forward(request, response);
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		String studName = request.getParameter("name");
		String studRows = request.getParameter("rows");
		String studCols = request.getParameter("cols");
		String studIntroduction = request.getParameter("introduction");

		try {
			if (studName.length() > 0 && studRows.length() > 0 && studCols.length() > 0
					&& studIntroduction.length() > 0) {
				Studio stud = new Studio();
				stud.setName(studName);
				stud.setRowCount(Integer.parseInt(studRows));
				stud.setColCount(Integer.parseInt(studCols));
				stud.setIntroduction(studIntroduction);
				stud.setStudioFlag(0);
				if (new StudioSrv().add(stud) == 1) {
					// response.setHeader("refresh", "2;URL=main.jsp");
					// out.print("<font size=5 color=red>添加成功！</font>");
					HttpSession session = request.getSession(true);
					session.setAttribute("error", "添加成功！");
					response.sendRedirect("studio_list.jsp");
				} else {
					// response.setHeader("refresh", "2;URL=main.jsp");
					// out.print("<font size=5 color=red>添加失败！</font>");
					HttpSession session = request.getSession(true);
					session.setAttribute("error", "添加失败！");
					response.sendRedirect("studio_list.jsp");
				}
			} else {
				// response.setHeader("refresh", "2;URL=main.jsp");
				// out.print("<font size=5 color=red>数据错误！</font>");
				HttpSession session = request.getSession(true);
				session.setAttribute("error", "数据错误！");
				response.sendRedirect("studio_list.jsp");
			}
		} catch (Exception e) {
			// response.setHeader("refresh", "2;URL=main.jsp");
			// out.println("<script> alert('捕获异常" + e.toString() +
			// "！');</script>");
			HttpSession session = request.getSession(true);
			session.setAttribute("error", "捕获异常" + e.toString() + "！");
			response.sendRedirect("studio_list.jsp");
		}
		out.flush();
		out.close();
	}

	public void doFetch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		String studName = request.getParameter("name");
		String studRows = request.getParameter("rows");
		String studCols = request.getParameter("cols");
		String studIntroduction = request.getParameter("introduction");
		String studFlag = request.getParameter("flag");

		try {
			String sql = "";
			if (studName.length() > 0) {
				if (sql.equals("")) {
					sql += " studio_name='" + studName + "'";
				} else {
					sql += " and studio_name='" + studName + "'";
				}
			}
			if (studRows.length() > 0) {
				if (sql.equals("")) {
					sql += " studio_row_count=" + studRows;
				} else {
					sql += " and studio_row_count=" + studRows;
				}
			}
			if (studCols.length() > 0) {
				if (sql.equals("")) {
					sql += " studio_col_count=" + studCols;
				} else {
					sql += " and studio_col_count=" + studCols;
				}
			}
			if (studIntroduction.length() > 0) {
				if (sql.equals("")) {
					sql += " studio_introduction='" + studIntroduction + "'";
				} else {
					sql += " and studio_introduction='" + studIntroduction + "'";
				}
			}
			if (studFlag.length() > 0) {
				if (sql.equals("")) {
					sql += " studio_flag=" + studFlag;
				} else {
					sql += " and studio_flag=" + studFlag;
				}
			}
			List<Studio> studList = new StudioSrv().Fetch(sql);
			if (studList.size() > 0) {
				HttpSession session = request.getSession(true);
				session.setAttribute("studlist", studList);
				response.sendRedirect("main.jsp");
			} else {
				// response.setHeader("refresh", "2;URL=main.jsp");
				// out.print("<font size=5 color=red>未找到演出厅！</font>");
				HttpSession session = request.getSession(true);
				session.setAttribute("error", "未找到演出厅！");
				response.sendRedirect("studio_list.jsp");
			}
		} catch (Exception e) {
			// response.setHeader("refresh", "2;URL=main.jsp");
			// out.println("<script> alert('捕获异常" + e.toString() +
			// "！');</script>");
			HttpSession session = request.getSession(true);
			session.setAttribute("error", "捕获异常" + e.toString() + "！");
			response.sendRedirect("studio_list.jsp");
		}
		out.flush();
		out.close();
	}

	public void doModify(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		String studId = request.getParameter("id");
		String studName = request.getParameter("name");
		String studRows = request.getParameter("rows");
		String studCols = request.getParameter("cols");
		String studIntroduction = request.getParameter("introduction");
		String studFlag = request.getParameter("flag");

		try {
			Studio stud = new Studio();
			stud.setID(Integer.parseInt(studId));
			List<Studio> rst = new StudioSrv().Fetch(" studio_id=" + studId);
			if (rst.size() > 0) {
				stud = rst.get(0);
				if (!studName.equals("")) {
					stud.setName(studName);
				}
				if (!studRows.equals("")) {
					stud.setRowCount(Integer.parseInt(studRows));
				}
				if (!studCols.equals("")) {
					stud.setColCount(Integer.parseInt(studCols));
				}
				if (!studIntroduction.equals("")) {
					stud.setIntroduction(studIntroduction);
				}
				if (!studFlag.equals("")) {
					stud.setStudioFlag(Integer.parseInt(studFlag));
				}
				if (new StudioSrv().modify(stud) == 1) {
					// response.setHeader("refresh", "2;URL=main.jsp");
					// out.print("<font size=5 color=red>修改成功！</font>");
					HttpSession session = request.getSession(true);
					session.setAttribute("error", "修改成功！");
					response.sendRedirect("studio_list.jsp");
				} else {
					// response.setHeader("refresh", "2;URL=main.jsp");
					// out.print("<font size=5 color=red>修改失败！</font>");
					HttpSession session = request.getSession(true);
					session.setAttribute("error", "修改失败！");
					response.sendRedirect("studio_list.jsp");
				}
			} else {
				// response.setHeader("refresh", "2;URL=main.jsp");
				// out.print("<font size=5 color=red>未找到待修改演出厅！</font>");
				HttpSession session = request.getSession(true);
				session.setAttribute("error", "未找到待修改演出厅！");
				response.sendRedirect("studio_list.jsp");
			}
		} catch (Exception e) {
			// response.setHeader("refresh", "2;URL=main.jsp");
			// out.println("<script> alert('捕获异常" + e.toString() +
			// "！');</script>");
			HttpSession session = request.getSession(true);
			session.setAttribute("error", "捕获异常" + e.toString() + "！");
			response.sendRedirect("studio_list.jsp");
		}
		out.flush();
		out.close();
	}

	public void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		String studId = request.getParameter("id");

		try {
			Studio stud = new Studio();
			stud.setID(Integer.parseInt(studId));
			List<Studio> rst = new StudioSrv().Fetch(" studio_id=" + studId);
			if (rst.size() > 0) {
				stud = rst.get(0);
				if (new StudioSrv().delete(Integer.parseInt(studId)) == 1) {
					// response.setHeader("refresh", "2;URL=main.jsp");
					// out.print("<font size=5 color=red>删除成功！</font>");
					HttpSession session = request.getSession(true);
					session.setAttribute("error", "删除成功！");
					response.sendRedirect("studio_list.jsp");
				} else {
					// response.setHeader("refresh", "2;URL=main.jsp");
					// out.print("<font size=5 color=red>删除失败！</font>");
					HttpSession session = request.getSession(true);
					session.setAttribute("error", "删除失败！");
					response.sendRedirect("studio_list.jsp");
				}
			} else {
				// response.setHeader("refresh", "2;URL=main.jsp");
				// out.print("<font size=5 color=red>未找到待删除演出厅！</font>");
				HttpSession session = request.getSession(true);
				session.setAttribute("error", "未找到待删除演出厅！！");
				response.sendRedirect("studio_list.jsp");
			}
		} catch (Exception e) {
			// response.setHeader("refresh", "2;URL=main.jsp");
			// out.println("<script> alert('捕获异常" + e.toString() +
			// "！');</script>");
			HttpSession session = request.getSession(true);
			session.setAttribute("error", "捕获异常" + e.toString() + "！");
			response.sendRedirect("studio_list.jsp");
		}
		out.flush();
		out.close();
	}

	public void destroy() {
		super.destroy();
	}
}
