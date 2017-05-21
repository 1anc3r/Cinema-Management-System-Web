package me.lancer.cms.mobile;

import java.io.IOException;
import java.util.HashMap;
import java.sql.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import me.lancer.cms.model.Ticket;
import me.lancer.cms.service.TicketSrv;

public class TicketMbl extends HttpServlet {

	private static final long serialVersionUID = 819624997597986006L;

	public TicketMbl() {
		super();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String method = request.getParameter("method");
		String session = request.getParameter("session");
		if (session != null) {
			if (mApp.getSessionid(session)) {
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
				request.setAttribute("data", "{\"code\":-1,\"data\":" + str + "}");
				request.getRequestDispatcher("/main/mobile_data.jsp").forward(request, response);
			}
		} else {
			String str = "\"session错误!\"";
			System.out.println(str);
			request.setAttribute("data", "{\"code\":-1,\"data\":" + str + "}");
			request.getRequestDispatcher("/main/mobile_data.jsp").forward(request, response);
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String seatId = request.getParameter("seatid");
		String schedId = request.getParameter("schedid");
		String price = request.getParameter("price");
		String status = request.getParameter("status");
		String lockTime = request.getParameter("locktime");

		if (seatId.length() > 0 && schedId.length() > 0 && price.length() > 0 && status.length() > 0
				&& lockTime.length() > 0) {
			Ticket tick = new Ticket();
			tick.setSeatId(Integer.parseInt(seatId));
			tick.setScheduleId(Integer.parseInt(schedId));
			tick.setPrice(Float.parseFloat(price));
			tick.setStatus(Integer.parseInt(status));
			tick.setLocked_time(Date.valueOf(lockTime));
			if (new TicketSrv().add(tick) == 1) {
				// request.setAttribute("error", "添加成功!");
				// request.getRequestDispatcher("/main/studio/studio_add.jsp").forward(request,
				// response);
				String str = "\"添加成功!\"";
				System.out.println(str);
				request.setAttribute("data", "{\"code\":0,\"data\":" + tick.getLocked_time() + "}");
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

	public void doFetch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tickId = request.getParameter("id");
		String seatId = request.getParameter("seatid");
		String schedId = request.getParameter("schedid");
		String price = request.getParameter("price");
		String status = request.getParameter("status");
		String lockTime = request.getParameter("locktime");
		Map<String, String> map = new HashMap<String, String>();
		if (tickId != null && !tickId.equals("")) {
			map.put("id", tickId);
		}
		if (seatId != null && !seatId.equals("")) {
			map.put("seatid", seatId);
		}
		if (schedId != null && !schedId.equals("")) {
			map.put("schedid", schedId);
		}
		if (price != null && !price.equals("")) {
			map.put("price", price);
		}
		if (status != null && !status.equals("")) {
			map.put("status", status);
		}
		if (lockTime != null && !lockTime.equals("")) {
			map.put("locktime", lockTime);
		}
		List<Ticket> tickList = new TicketSrv().Fetch_(map);
		if (tickList.size() > 0) {
			// request.setAttribute("error", null);
			// request.setAttribute("list", studList);
			// request.getRequestDispatcher("/main/studio/studio_fetch.jsp").forward(request,
			// response);
			Gson gson = new Gson();
			String str = gson.toJson(tickList);
			System.out.println(request.getRequestedSessionId());
			request.setAttribute("data", "{\"code\":0,\"data\":" + str + "}");
			request.getRequestDispatcher("/main/mobile_data.jsp").forward(request, response);
		} else {
			// request.setAttribute("error", "未找到符合条件的演出厅!");
			// request.getRequestDispatcher("/main/studio/studio_fetch.jsp").forward(request,
			// response);
			String str = "\"未找到符合条件的电影票!\"";
			System.out.println(str);
			request.setAttribute("data", "{\"code\":1,\"data\":" + str + "}");
			request.getRequestDispatcher("/main/mobile_data.jsp").forward(request, response);
		}
	}

	public void doModify(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String tickId = request.getParameter("id");
		String seatId = request.getParameter("seatid");
		String schedId = request.getParameter("schedid");
		String price = request.getParameter("price");
		String status = request.getParameter("status");
		String lockTime = request.getParameter("locktime");

		String sql = " ticket_id=" + tickId;
		List<Ticket> tickList = new TicketSrv().Fetch(sql);
		if (tickList.size() > 0) {
			// request.setAttribute("error", null);
			// request.setAttribute("list", studList);
			// request.getRequestDispatcher("/main/studio/studio_fetch.jsp").forward(request,
			// response);
			Ticket tick = tickList.get(0);
			tick.setSeatId(Integer.parseInt(seatId));
			tick.setScheduleId(Integer.parseInt(schedId));
			tick.setPrice(Float.parseFloat(price));
			tick.setStatus(Integer.parseInt(status));
			tick.setLocked_time(Date.valueOf(lockTime));
			if (new TicketSrv().modify(tick) == 1) {
				// request.setAttribute("error", "添加成功!");
				// request.getRequestDispatcher("/main/studio/studio_add.jsp").forward(request,
				// response);
				String str = "\"修改成功!\"";
				System.out.println(str);
				request.setAttribute("data", "{\"code\":0,\"data\":" + str + "}");
				request.getRequestDispatcher("/main/mobile_data.jsp").forward(request, response);
			} else {
				// request.setAttribute("error", "添加失败!请检查数据库状态后再提交添加");
				// request.getRequestDispatcher("/main/studio/studio_add.jsp").forward(request,
				// response);
				String str = "\"修改失败!请稍后再提交添加\"";
				System.out.println(str);
				request.setAttribute("data", "{\"code\":1,\"data\":" + str + "}");
				request.getRequestDispatcher("/main/mobile_data.jsp").forward(request, response);
			}
		} else {
			// request.setAttribute("error", "未找到符合条件的演出厅!");
			// request.getRequestDispatcher("/main/studio/studio_fetch.jsp").forward(request,
			// response);
			String str = "\"未找到符合条件的电影票!\"";
			System.out.println(str);
			request.setAttribute("data", "{\"code\":1,\"data\":" + str + "}");
			request.getRequestDispatcher("/main/mobile_data.jsp").forward(request, response);
		}
	}

	public void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String tickId = request.getParameter("id");

		Ticket tick = new Ticket();
		tick.setId(Integer.parseInt(tickId));
		List<Ticket> rst = new TicketSrv().Fetch(" ticket_id=" + tickId);
		if (rst.size() > 0) {
			tick = rst.get(0);
			if (new TicketSrv().delete(Integer.parseInt(tickId)) == 1) {
				// request.setAttribute("error", "删除成功!");
				// request.getRequestDispatcher("/main/studio/studio_delete.jsp").forward(request,
				// response);
				String str = "\"删除成功!\"";
				System.out.println(str);
				request.setAttribute("data", "{\"code\":0,\"data\":" + str + "}");
				request.getRequestDispatcher("/main/mobile_data.jsp").forward(request, response);
			} else {
				// request.setAttribute("error", "删除失败!请检查数据库状态后再提交删除");
				// request.getRequestDispatcher("/main/studio/studio_delete.jsp").forward(request,
				// response);
				String str = "\"删除失败!请稍后再提交删除\"";
				System.out.println(str);
				request.setAttribute("data", "{\"code\":1,\"data\":" + str + "}");
				request.getRequestDispatcher("/main/mobile_data.jsp").forward(request, response);
			}
		} else {
			// request.setAttribute("error", "删除失败!未找到符合条件的演出厅");
			// request.getRequestDispatcher("/main/studio/studio_delete.jsp").forward(request,
			// response);
			String str = "\"删除失败!未找到符合条件的电影票\"";
			System.out.println(str);
			request.setAttribute("data", "{\"code\":2,\"data\":" + str + "}");
			request.getRequestDispatcher("/main/mobile_data.jsp").forward(request, response);
		}
	}

	public void destroy() {
		super.destroy();
	}
}
