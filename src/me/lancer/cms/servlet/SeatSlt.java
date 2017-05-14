package me.lancer.cms.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.lancer.cms.model.Seat;
import me.lancer.cms.service.SeatSrv;

public class SeatSlt extends HttpServlet {

	private static final long serialVersionUID = -9129614407989760383L;

	public SeatSlt() {
		super();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String method = request.getParameter("method");

		if ("fetch".equalsIgnoreCase(method)) {
			doFetch(request, response);
		} else if ("modify".equalsIgnoreCase(method)) {
			doModify(request, response);
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doFetch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String seatId = request.getParameter("id");
		String seatStudId = request.getParameter("studid");
		String seatRow = request.getParameter("row");
		String seatCol = request.getParameter("col");
		String seatStatus = request.getParameter("status");
		Map<String, String> map = new HashMap<String, String>();
		if (seatId != null && !seatId.equals("")) {
			map.put("id", seatId);
		}
		if (seatStudId != null && !seatStudId.equals("")) {
			map.put("studid", seatStudId);
		}
		if (seatRow != null && !seatRow.equals("")) {
			map.put("row", seatRow);
		}
		if (seatCol != null && !seatCol.equals("")) {
			map.put("col", seatCol);
		}
		if (seatStatus != null && !seatStatus.equals("")) {
			map.put("status", seatStatus);
		}
		List<Seat> seatList = new SeatSrv().Fetch_(map);
		if (seatList.size() > 0) {
			request.setAttribute("error", null);
			request.setAttribute("list", seatList);
			request.getRequestDispatcher("/main/seat/seat_list.jsp").forward(request, response);
		} else {
			request.setAttribute("error", "未找到符合条件的座位!");
			request.getRequestDispatcher("/main/seat/seat_list.jsp").forward(request, response);
		}
	}

	public void doModify(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String seatId = request.getParameter("id");
		String seatStudId = request.getParameter("studid");
		String seatRow = request.getParameter("row");
		String seatCol = request.getParameter("col");
		String seatStatus = request.getParameter("status");
		Seat seat = new Seat();
		Map<String, String> map = new HashMap<String, String>();
		if (seatId != null && !seatId.equals("")) {
			map.put("id", seatId);
		}
		if (seatStudId != null && !seatStudId.equals("")) {
			map.put("studid", seatStudId);
		}
		if (seatRow != null && !seatRow.equals("")) {
			map.put("row", seatRow);
		}
		if (seatCol != null && !seatCol.equals("")) {
			map.put("col", seatCol);
		}
		if (seatStatus != null && !seatStatus.equals("")) {
			map.put("status", seatStatus);
		}
		List<Seat> rst = new SeatSrv().Fetch_(map);
		if (rst.size() > 0) {
			seat = rst.get(0);
			if (seatStudId != null && !seatStudId.equals("")) {
				seat.setStudioId(Integer.parseInt(seatStudId));
			}
			if (seatRow != null && !seatRow.equals("")) {
				seat.setRow(Integer.parseInt(seatRow));
			}
			if (seatCol != null && !seatCol.equals("")) {
				seat.setColumn(Integer.parseInt(seatCol));
			}
			if (seatStatus != null && !seatStatus.equals("")) {
				seat.setSeatStatus(Integer.parseInt(seatStatus));
			}
			if (new SeatSrv().modify(seat) == 1) {
				request.setAttribute("error", "修改成功!");
				request.getRequestDispatcher("/main/seat/seat_modify.jsp").forward(request, response);
			} else {
				request.setAttribute("error", "修改失败!请检查数据库状态后再提交修改");
				request.getRequestDispatcher("/main/seat/seat_modify.jsp").forward(request, response);
			}
		} else {
			request.setAttribute("error", "修改失败!未找到符合条件的座位");
			request.getRequestDispatcher("/main/seat/seat_modify.jsp").forward(request, response);
		}
	}

	public void destroy() {
		super.destroy();
	}
}
