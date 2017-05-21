package me.lancer.cms.mobile;

import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import me.lancer.cms.model.Sale;
import me.lancer.cms.service.SaleSrv;

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

		String empid = request.getParameter("empid");
		String time = request.getParameter("time");
		String payment = request.getParameter("payment");
		String change = request.getParameter("change");
		String type = request.getParameter("type");
		String status = request.getParameter("status");

		if (empid != null && time != null && payment != null && change != null && type != null && type != null
				&& !empid.equals("") && !time.equals("") && !payment.equals("") && !change.equals("")
				&& !type.equals("") && !type.equals("")) {
			Sale stud = new Sale();
			stud.setEmpId(Integer.parseInt(empid));
			stud.setTime(Date.valueOf(time));
			stud.setPayment(Float.parseFloat(payment));
			stud.setChange(Float.parseFloat(change));
			stud.setType(Integer.parseInt(type));
			stud.setStatus(Integer.parseInt(status));
			if (new SaleSrv().add(stud) == 1) {
				// request.setAttribute("error", "添加成功!");
				// request.getRequestDispatcher("/main/studio/studio_add.jsp").forward(request,
				// response);
				String str = "\"添加成功!\"";
				System.out.println(str);
				request.setAttribute("data", "{\"code\":0,\"data\":" + stud.getTime() + "}");
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

		String id = request.getParameter("id");
		String empid = request.getParameter("empid");
		String time = request.getParameter("time");
		String payment = request.getParameter("payment");
		String change = request.getParameter("change");
		String type = request.getParameter("type");
		String status = request.getParameter("status");
		Map<String, String> map = new HashMap<String, String>();
		if (id != null && !id.equals("")) {
			map.put("id", id);
		}
		if (empid != null && !empid.equals("")) {
			map.put("empid", empid);
		}
		if (time != null && !time.equals("")) {
			map.put("time", time);
		}
		if (payment != null && !payment.equals("")) {
			map.put("payment", payment);
		}
		if (change != null && !change.equals("")) {
			map.put("change", change);
		}
		if (type != null && !type.equals("")) {
			map.put("type", type);
		}
		if (status != null && !status.equals("")) {
			map.put("status", status);
		}
		List<Sale> saleList = new SaleSrv().Fetch_(map);
		if (saleList.size() > 0) {
			// request.setAttribute("error", null);
			// request.setAttribute("list", studList);
			// request.getRequestDispatcher("/main/studio/studio_fetch.jsp").forward(request,
			// response);
			Gson gson = new Gson();
			String str = gson.toJson(saleList);
			System.out.println(request.getRequestedSessionId());
			request.setAttribute("data", "{\"code\":0,\"data\":" + str + "}");
			request.getRequestDispatcher("/main/mobile_data.jsp").forward(request, response);
		} else {
			// request.setAttribute("error", "未找到符合条件的演出厅!");
			// request.getRequestDispatcher("/main/studio/studio_fetch.jsp").forward(request,
			// response);
			String str = "\"未找到符合条件的发票!\"";
			System.out.println(str);
			request.setAttribute("data", "{\"code\":1,\"data\":" + str + "}");
			request.getRequestDispatcher("/main/mobile_data.jsp").forward(request, response);
		}
	}

	public void doModify(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");
		String empid = request.getParameter("empid");
		String time = request.getParameter("time");
		String payment = request.getParameter("payment");
		String change = request.getParameter("change");
		String type = request.getParameter("type");
		String status = request.getParameter("status");

		Sale stud = new Sale();
		stud.setId(Integer.parseInt(id));
		List<Sale> rst = new SaleSrv().Fetch(" sale_id=" + id);
		if (rst.size() > 0) {
			stud = rst.get(0);
			if (!empid.equals("")) {
				stud.setEmpId(Integer.parseInt(empid));
			}
			if (!time.equals("")) {
				stud.setTime(Date.valueOf(time));
			}
			if (!payment.equals("")) {
				stud.setPayment(Float.parseFloat(payment));
			}
			if (!change.equals("")) {
				stud.setChange(Float.parseFloat(change));
			}
			if (!type.equals("")) {
				stud.setType(Integer.parseInt(type));
			}
			if (!status.equals("")) {
				stud.setStatus(Integer.parseInt(status));
			}
			if (new SaleSrv().modify(stud) == 1) {
				// request.setAttribute("error", "修改成功!");
				// request.getRequestDispatcher("/main/studio/studio_modify.jsp").forward(request,
				// response);
				String str = "\"修改成功!\"";
				System.out.println(str);
				request.setAttribute("data", "{\"code\":0,\"data\":" + str + "}");
				request.getRequestDispatcher("/main/mobile_data.jsp").forward(request, response);
			} else {
				// request.setAttribute("error", "修改失败!请检查数据库状态后再提交修改");
				// request.getRequestDispatcher("/main/studio/studio_modify.jsp").forward(request,
				// response);
				String str = "\"修改失败!请稍后再提交修改\"";
				System.out.println(str);
				request.setAttribute("data", "{\"code\":1,\"data\":" + str + "}");
				request.getRequestDispatcher("/main/mobile_data.jsp").forward(request, response);
			}
		} else {
			// request.setAttribute("error", "修改失败!未找到符合条件的演出厅");
			// request.getRequestDispatcher("/main/studio/studio_modify.jsp").forward(request,
			// response);
			String str = "\"修改失败!未找到符合条件的影厅\"";
			System.out.println(str);
			request.setAttribute("data", "{\"code\":2,\"data\":" + str + "}");
			request.getRequestDispatcher("/main/mobile_data.jsp").forward(request, response);
		}
	}

	public void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String studId = request.getParameter("id");

		Sale stud = new Sale();
		stud.setId(Integer.parseInt(studId));
		List<Sale> rst = new SaleSrv().Fetch(" sale_id=" + studId);
		if (rst.size() > 0) {
			stud = rst.get(0);
			if (new SaleSrv().delete(Integer.parseInt(studId)) == 1) {
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
			String str = "\"删除失败!未找到符合条件的影厅\"";
			System.out.println(str);
			request.setAttribute("data", "{\"code\":2,\"data\":" + str + "}");
			request.getRequestDispatcher("/main/mobile_data.jsp").forward(request, response);
		}
	}

	public void destroy() {
		super.destroy();
	}
}
