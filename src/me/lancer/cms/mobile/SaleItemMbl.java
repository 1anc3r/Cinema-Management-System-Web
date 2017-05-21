package me.lancer.cms.mobile;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import me.lancer.cms.model.SaleItem;
import me.lancer.cms.model.Studio;
import me.lancer.cms.service.SaleItemSrv;
import me.lancer.cms.service.StudioSrv;

public class SaleItemMbl extends HttpServlet {

	private static final long serialVersionUID = 819624997597986006L;

	public SaleItemMbl() {
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

		// String id = request.getParameter("id");
		String tickid = request.getParameter("tickid");
		String saleid = request.getParameter("saleid");
		String price = request.getParameter("price");

		if (tickid.length() > 0 && saleid.length() > 0 && price.length() > 0) {
			SaleItem stud = new SaleItem();
			stud.setTicketId(Integer.parseInt(tickid));
			stud.setSaleId(Integer.parseInt(saleid));
			stud.setPrice(Float.parseFloat(price));
			if (new SaleItemSrv().add(stud) == 1) {
				// // request.setAttribute("error", "添加成功!");
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

	public void doFetch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String id = request.getParameter("id");
		String tickid = request.getParameter("tickid");
		String saleid = request.getParameter("saleid");
		String price = request.getParameter("price");

		Map<String, String> map = new HashMap<String, String>();
		if (id != null && !id.equals("")) {
			map.put("id", id);
		}
		if (tickid != null && !tickid.equals("")) {
			map.put("tickid", tickid);
		}
		if (saleid != null && !saleid.equals("")) {
			map.put("saleid", saleid);
		}
		if (price != null && !price.equals("")) {
			map.put("price", price);
		}
		List<SaleItem> studList = new SaleItemSrv().Fetch_(map);
		if (studList.size() > 0) {
			// request.setAttribute("error", null);
			// request.setAttribute("list", studList);
			// request.getRequestDispatcher("/main/studio/studio_fetch.jsp").forward(request,
			// response);
			Gson gson = new Gson();
			String str = gson.toJson(studList);
			System.out.println(request.getRequestedSessionId());
			request.setAttribute("data", "{\"code\":0,\"data\":" + str + "}");
			request.getRequestDispatcher("/main/mobile_data.jsp").forward(request, response);
		} else {
			// request.setAttribute("error", "未找到符合条件的演出厅!");
			// request.getRequestDispatcher("/main/studio/studio_fetch.jsp").forward(request,
			// response);
			String str = "\"未找到符合条件的流水!\"";
			System.out.println(str);
			request.setAttribute("data", "{\"code\":1,\"data\":" + str + "}");
			request.getRequestDispatcher("/main/mobile_data.jsp").forward(request, response);
		}
	}

	public void doModify(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");
		String tickid = request.getParameter("tickid");
		String saleid = request.getParameter("saleid");
		String price = request.getParameter("price");
		SaleItem stud = new SaleItem();
		stud.setId(Integer.parseInt(id));
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		List<SaleItem> rst = new SaleItemSrv().Fetch_(map);
		if (rst.size() > 0) {
			stud = rst.get(0);
			if (tickid != null && !tickid.equals("")) {
				stud.setTicketId(Integer.parseInt(tickid));
			}
			if (saleid != null && !saleid.equals("")) {
				stud.setSaleId(Integer.parseInt(saleid));
			}
			if (price != null && !price.equals("")) {
				stud.setPrice(Float.parseFloat(price));
			}
			if (new SaleItemSrv().modify(stud) == 1) {
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
			String str = "\"修改失败!未找到符合条件的流水\"";
			System.out.println(str);
			request.setAttribute("data", "{\"code\":2,\"data\":" + str + "}");
			request.getRequestDispatcher("/main/mobile_data.jsp").forward(request, response);
		}
	}

	public void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String studId = request.getParameter("id");

		SaleItem stud = new SaleItem();
		stud.setId(Integer.parseInt(studId));
		List<SaleItem> rst = new SaleItemSrv().Fetch(" sale_item_id=" + studId);
		if (rst.size() > 0) {
			stud = rst.get(0);
			if (new SaleItemSrv().delete(Integer.parseInt(studId)) == 1) {
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
