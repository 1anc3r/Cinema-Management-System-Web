package me.lancer.cms.servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.lancer.cms.model.Schedule;
import me.lancer.cms.service.ScheduleSrv;

public class ScheduleSlt extends HttpServlet {

	private static final long serialVersionUID = 819624997597986006L;

	public ScheduleSlt() {
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
		String schedStudId = request.getParameter("studid");
		String schedPlayId = request.getParameter("playid");
		String schedTime = request.getParameter("time");
		String schedPrice = request.getParameter("price");
		if (schedStudId != null && schedPlayId != null && schedTime != null && schedPrice != null
				&& !schedStudId.equals("") && !schedPlayId.equals("") && !schedTime.equals("")
				&& !schedPrice.equals("")) {
			Schedule sched = new Schedule();
			sched.setStudioId(Integer.parseInt(schedStudId));
			sched.setPlayId(Integer.parseInt(schedPlayId));
			sched.setTime(Date.valueOf(schedTime));
			sched.setPrice(Double.parseDouble(schedPrice));
			if (new ScheduleSrv().add(sched) == 1) {
				request.setAttribute("error", "添加成功!");
				request.getRequestDispatcher("schedule_add.jsp").forward(request, response);
			} else {
				request.setAttribute("error", "添加失败!请检查数据库状态后再提交添加");
				request.getRequestDispatcher("schedule_add.jsp").forward(request, response);
			}
		} else {
			request.setAttribute("error", "添加失败!请将信息补充完整后再提交添加");
			request.getRequestDispatcher("schedule_add.jsp").forward(request, response);
		}
	}

	public void doFetch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String schedId = request.getParameter("id");
		String schedStudId = request.getParameter("studid");
		String schedPlayId = request.getParameter("playid");
		String schedTime = request.getParameter("time");
		String schedPrice = request.getParameter("price");
		Map<String, String> map = new HashMap<String, String>();
		if (schedId != null && schedId.equals("")) {
			map.put("id", schedId);
		}
		if (schedStudId != null && schedStudId.equals("")) {
			map.put("studid", schedStudId);
		}
		if (schedPlayId != null && schedPlayId.equals("")) {
			map.put("playid", schedPlayId);
		}
		if (schedTime != null && schedTime.equals("")) {
			map.put("time", schedTime);
		}
		if (schedPrice != null && schedPrice.equals("")) {
			map.put("price", schedPrice);
		}
		List<Schedule> schedList = new ScheduleSrv().Fetch_(map);
		if (schedList.size() > 0) {
			request.setAttribute("error", null);
			request.setAttribute("list", schedList);
			request.getRequestDispatcher("schedule_fetch.jsp").forward(request, response);
		} else {
			request.setAttribute("error", "未找到符合条件的演出计划!");
			request.getRequestDispatcher("schedule_fetch.jsp").forward(request, response);
		}
	}

	public void doModify(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String schedId = request.getParameter("id");
		String schedStudId = request.getParameter("studid");
		String schedPlayId = request.getParameter("playid");
		String schedTime = request.getParameter("time");
		String schedPrice = request.getParameter("price");
		Schedule sched = new Schedule();
		sched.setId(Integer.parseInt(schedId));
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", schedId);
		List<Schedule> rst = new ScheduleSrv().Fetch_(map);
		if (rst.size() > 0) {
			sched = rst.get(0);
			if (schedStudId != null && !schedStudId.equals("")) {
				sched.setStudioId(Integer.parseInt(schedStudId));
			}
			if (schedPlayId != null && !schedPlayId.equals("")) {
				sched.setPlayId(Integer.parseInt(schedPlayId));
			}
			if (schedTime != null && !schedTime.equals("")) {
				sched.setTime(Date.valueOf(schedTime));
			}
			if (schedPrice != null && !schedPrice.equals("")) {
				sched.setPrice(Double.parseDouble(schedPrice));
			}
			if (new ScheduleSrv().modify(sched) == 1) {
				request.setAttribute("error", "修改成功!");
				request.getRequestDispatcher("schedule_modify.jsp").forward(request, response);
			} else {
				request.setAttribute("error", "修改失败!请检查数据库状态后再提交修改");
				request.getRequestDispatcher("schedule_modify.jsp").forward(request, response);
			}
		} else {
			request.setAttribute("error", "修改失败!未找到符合条件的演出计划");
			request.getRequestDispatcher("schedule_modify.jsp").forward(request, response);
		}
	}

	public void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String schedId = request.getParameter("id");
		Schedule sched = new Schedule();
		sched.setId(Integer.parseInt(schedId));
		List<Schedule> rst = new ScheduleSrv().Fetch(" sched_id=" + schedId);
		if (rst.size() > 0) {
			sched = rst.get(0);
			if (new ScheduleSrv().delete(Integer.parseInt(schedId)) == 1) {
				request.setAttribute("error", "删除成功!");
				request.getRequestDispatcher("schedule_delete.jsp").forward(request, response);
			} else {
				request.setAttribute("error", "删除失败!请检查数据库状态后再提交删除");
				request.getRequestDispatcher("schedule_delete.jsp").forward(request, response);
			}
		} else {
			request.setAttribute("error", "删除失败!未找到符合条件的演出厅");
			request.getRequestDispatcher("schedule_delete.jsp").forward(request, response);
		}
	}

	public void destroy() {
		super.destroy();
	}
}
