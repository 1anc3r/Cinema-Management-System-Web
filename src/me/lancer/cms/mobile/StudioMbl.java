package me.lancer.cms.mobile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import me.lancer.cms.model.Studio;
import me.lancer.cms.service.StudioSrv;

public class StudioMbl extends HttpServlet {

	private static final long serialVersionUID = 5042128321408569090L;

	public StudioMbl() {
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
		String studName = request.getParameter("name");
		String studRows = request.getParameter("rows");
		String studCols = request.getParameter("cols");
		String studIntroduction = request.getParameter("introduction");
		if (studName != null && studRows != null && studCols != null && studIntroduction != null && !studName.equals("")
				&& !studRows.equals("") && !studCols.equals("") && !studIntroduction.equals("")) {
			Studio stud = new Studio();
			stud.setName(studName);
			stud.setRowCount(Integer.parseInt(studRows));
			stud.setColCount(Integer.parseInt(studCols));
			stud.setIntroduction(studIntroduction);
			stud.setStudioFlag(0);
			if (new StudioSrv().add(stud) == 1) {
				// request.setAttribute("error", "添加成功!");
				// request.getRequestDispatcher("/main/studio/studio_add.jsp").forward(request,
				// response);
				Gson gson = new Gson();
				String str = "\"添加成功!\"";
				System.out.println(str);
				request.setAttribute("data", "{\"data\":" + str + "}");
				request.getRequestDispatcher("/main/mobile_data.jsp").forward(request, response);
			} else {
				// request.setAttribute("error", "添加失败!请检查数据库状态后再提交添加");
				// request.getRequestDispatcher("/main/studio/studio_add.jsp").forward(request,
				// response);
				Gson gson = new Gson();
				String str = "\"添加失败!请稍后再提交添加\"";
				System.out.println(str);
				request.setAttribute("data", "{\"data\":" + str + "}");
				request.getRequestDispatcher("/main/mobile_data.jsp").forward(request, response);
			}
		} else {
			// request.setAttribute("error", "添加失败!请将信息补充完整后再提交添加");
			// request.getRequestDispatcher("/main/studio/studio_add.jsp").forward(request,
			// response);
			Gson gson = new Gson();
			String str = "\"添加失败!请将信息补充完整后再提交添加\"";
			System.out.println(str);
			request.setAttribute("data", "{\"data\":" + str + "}");
			request.getRequestDispatcher("/main/mobile_data.jsp").forward(request, response);
		}
	}

	public void doFetch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String studId = request.getParameter("id");
		String studName = request.getParameter("name");
		String studRows = request.getParameter("rows");
		String studCols = request.getParameter("cols");
		String studIntroduction = request.getParameter("introduction");
		String studFlag = request.getParameter("flag");
		Map<String, String> map = new HashMap<String, String>();
		if (studId != null && !studId.equals("")) {
			map.put("id", studId);
		}
		if (studName != null && !studName.equals("")) {
			map.put("name", studName);
		}
		if (studRows != null && !studRows.equals("")) {
			map.put("row", studRows);
		}
		if (studCols != null && !studCols.equals("")) {
			map.put("col", studCols);
		}
		if (studIntroduction != null && !studIntroduction.equals("")) {
			map.put("introduction", studIntroduction);
		}
		if (studFlag != null && !studFlag.equals("")) {
			map.put("flag", studFlag);
		}
		List<Studio> studList = new StudioSrv().Fetch_(map);
		if (studList.size() > 0) {
			// request.setAttribute("error", null);
			// request.setAttribute("list", studList);
			// request.getRequestDispatcher("/main/studio/studio_fetch.jsp").forward(request,
			// response);
			Gson gson = new Gson();
			String str = gson.toJson(studList);
			System.out.println(request.getRequestedSessionId());
			request.setAttribute("data", "{\"data\":" + str + "}");
			request.getRequestDispatcher("/main/mobile_data.jsp").forward(request, response);
		} else {
			// request.setAttribute("error", "未找到符合条件的演出厅!");
			// request.getRequestDispatcher("/main/studio/studio_fetch.jsp").forward(request,
			// response);
			Gson gson = new Gson();
			String str = "\"未找到符合条件的演出厅!\"";
			System.out.println(str);
			request.setAttribute("data", "{\"data\":" + str + "}");
			request.getRequestDispatcher("/main/mobile_data.jsp").forward(request, response);
		}
	}

	public void doModify(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String studId = request.getParameter("id");
		String studName = request.getParameter("name");
		String studRows = request.getParameter("rows");
		String studCols = request.getParameter("cols");
		String studIntroduction = request.getParameter("introduction");
		String studFlag = request.getParameter("flag");
		Studio stud = new Studio();
		stud.setID(Integer.parseInt(studId));
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", studId);
		List<Studio> rst = new StudioSrv().Fetch_(map);
		if (rst.size() > 0) {
			stud = rst.get(0);
			if (studName != null && !studName.equals("")) {
				stud.setName(studName);
			}
			if (studRows != null && !studRows.equals("")) {
				stud.setRowCount(Integer.parseInt(studRows));
			}
			if (studCols != null && !studCols.equals("")) {
				stud.setColCount(Integer.parseInt(studCols));
			}
			if (studIntroduction != null && !studIntroduction.equals("")) {
				stud.setIntroduction(studIntroduction);
			}
			if (studFlag != null && !studFlag.equals("")) {
				stud.setStudioFlag(Integer.parseInt(studFlag));
			}
			if (new StudioSrv().modify(stud) == 1) {
				// request.setAttribute("error", "修改成功!");
				// request.getRequestDispatcher("/main/studio/studio_modify.jsp").forward(request,
				// response);
				Gson gson = new Gson();
				String str = "\"修改成功!\"";
				System.out.println(str);
				request.setAttribute("data", "{\"data\":" + str + "}");
				request.getRequestDispatcher("/main/mobile_data.jsp").forward(request, response);
			} else {
				// request.setAttribute("error", "修改失败!请检查数据库状态后再提交修改");
				// request.getRequestDispatcher("/main/studio/studio_modify.jsp").forward(request,
				// response);
				Gson gson = new Gson();
				String str = "\"修改失败!请稍后再提交修改\"";
				System.out.println(str);
				request.setAttribute("data", "{\"data\":" + str + "}");
				request.getRequestDispatcher("/main/mobile_data.jsp").forward(request, response);
			}
		} else {
			// request.setAttribute("error", "修改失败!未找到符合条件的演出厅");
			// request.getRequestDispatcher("/main/studio/studio_modify.jsp").forward(request,
			// response);
			Gson gson = new Gson();
			String str = "\"修改失败!未找到符合条件的演出厅\"";
			System.out.println(str);
			request.setAttribute("data", "{\"data\":" + str + "}");
			request.getRequestDispatcher("/main/mobile_data.jsp").forward(request, response);
		}
	}

	public void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String studId = request.getParameter("id");
		Studio stud = new Studio();
		stud.setID(Integer.parseInt(studId));
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", studId);
		List<Studio> rst = new StudioSrv().Fetch_(map);
		if (rst.size() > 0) {
			stud = rst.get(0);
			if (new StudioSrv().delete(Integer.parseInt(studId)) == 1) {
				// request.setAttribute("error", "删除成功!");
				// request.getRequestDispatcher("/main/studio/studio_delete.jsp").forward(request,
				// response);
				Gson gson = new Gson();
				String str = "\"删除成功!\"";
				System.out.println(str);
				request.setAttribute("data", "{\"data\":" + str + "}");
				request.getRequestDispatcher("/main/mobile_data.jsp").forward(request, response);
			} else {
				// request.setAttribute("error", "删除失败!请检查数据库状态后再提交删除");
				// request.getRequestDispatcher("/main/studio/studio_delete.jsp").forward(request,
				// response);
				Gson gson = new Gson();
				String str = "\"删除失败!请稍后再提交删除\"";
				System.out.println(str);
				request.setAttribute("data", "{\"data\":" + str + "}");
				request.getRequestDispatcher("/main/mobile_data.jsp").forward(request, response);
			}
		} else {
			// request.setAttribute("error", "删除失败!未找到符合条件的演出厅");
			// request.getRequestDispatcher("/main/studio/studio_delete.jsp").forward(request,
			// response);
			Gson gson = new Gson();
			String str = "\"删除失败!未找到符合条件的演出厅\"";
			System.out.println(str);
			request.setAttribute("data", "{\"data\":" + str + "}");
			request.getRequestDispatcher("/main/mobile_data.jsp").forward(request, response);
		}
	}

	public void destroy() {
		super.destroy();
	}
}
