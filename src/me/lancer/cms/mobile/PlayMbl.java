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

import me.lancer.cms.model.Play;
import me.lancer.cms.service.PlaySrv;

public class PlayMbl extends HttpServlet {

	private static final long serialVersionUID = -1753934941774130429L;

	public PlayMbl() {
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
		String playType = request.getParameter("type");
		String playLang = request.getParameter("lang");
		String playName = request.getParameter("name");
		String playIntroduction = request.getParameter("introduction");
		String playLength = request.getParameter("length");
		String playPrice = request.getParameter("price");
		String playStatus = request.getParameter("pricstatuse");
		if (playType != null && playLang != null && playName != null && playIntroduction != null && playLength != null
				&& playPrice != null && !playType.equals("") && !playLang.equals("") && !playName.equals("")
				&& !playIntroduction.equals("") && !playLength.equals("") && !playPrice.equals("")) {
			Play play = new Play();
			play.setTypeId(Integer.parseInt(playType));
			play.setLangId(Integer.parseInt(playLang));
			play.setName(playName);
			play.setIntroduction(playIntroduction);
			play.setLength(Integer.parseInt(playLength));
			play.setPrice(Float.parseFloat(playPrice));
			play.setStatus(Integer.parseInt(playStatus));
			if (new PlaySrv().add(play) == 1) {
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

	public void doFetch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String playId = request.getParameter("id");
		String playType = request.getParameter("type");
		String playLang = request.getParameter("lang");
		String playName = request.getParameter("name");
		String playIntroduction = request.getParameter("introduction");
		String playLength = request.getParameter("length");
		String playPrice = request.getParameter("price");
		String playStatus = request.getParameter("status");
		Map<String, String> map = new HashMap<String, String>();
		if (playId != null && !playId.equals("")) {
			map.put("id", playId);
		}
		if (playType != null && !playType.equals("")) {
			map.put("type", playType);
		}
		if (playLang != null && !playLang.equals("")) {
			map.put("lang", playLang);
		}
		if (playName != null && !playName.equals("")) {
			map.put("name", playName);
		}
		if (playIntroduction != null && !playIntroduction.equals("")) {
			map.put("introduction", playIntroduction);
		}
		if (playLength != null && !playLength.equals("")) {
			map.put("length", playLength);
		}
		if (playPrice != null && !playPrice.equals("")) {
			map.put("price", playPrice);
		}
		if (playStatus != null && !playPrice.equals("")) {
			map.put("status", playStatus);
		}
		List<Play> playList = new PlaySrv().Fetch_(map);
		if (playList.size() > 0) {
			// request.setAttribute("error", null);
			// request.setAttribute("list", studList);
			// request.getRequestDispatcher("/main/studio/studio_fetch.jsp").forward(request,
			// response);
			Gson gson = new Gson();
			String str = gson.toJson(playList);
			System.out.println(request.getRequestedSessionId());
			request.setAttribute("data", "{\"code\":0,\"data\":" + str + "}");
			request.getRequestDispatcher("/main/mobile_data.jsp").forward(request, response);
		} else {
			// request.setAttribute("error", "未找到符合条件的演出厅!");
			// request.getRequestDispatcher("/main/studio/studio_fetch.jsp").forward(request,
			// response);
			String str = "\"未找到符合条件的演出厅!\"";
			System.out.println(str);
			request.setAttribute("data", "{\"code\":1,\"data\":" + str + "}");
			request.getRequestDispatcher("/main/mobile_data.jsp").forward(request, response);
		}
	}

	public void doModify(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		String playId = request.getParameter("id");
		String playType = request.getParameter("type");
		String playLang = request.getParameter("lang");
		String playName = request.getParameter("name");
		String playIntroduction = request.getParameter("introduction");
		String playLength = request.getParameter("length");
		String playPrice = request.getParameter("price");
		String playStatus = request.getParameter("status");

		Play play = new Play();
		Map<String, String> map = new HashMap<String, String>();
		if (playId != null && !playId.equals("")) {
			map.put("id", playId);
		}
		if (playType != null && !playType.equals("")) {
			map.put("type", playType);
		}
		if (playLang != null && !playLang.equals("")) {
			map.put("lang", playLang);
		}
		if (playName != null && !playName.equals("")) {
			map.put("name", playName);
		}
		if (playIntroduction != null && !playIntroduction.equals("")) {
			map.put("introduction", playIntroduction);
		}
		if (playLength != null && !playLength.equals("")) {
			map.put("length", playLength);
		}
		if (playPrice != null && !playPrice.equals("")) {
			map.put("price", playPrice);
		}
		if (playStatus != null && !playPrice.equals("")) {
			map.put("status", playStatus);
		}
		List<Play> rst = new PlaySrv().Fetch_(map);
		if (rst.size() > 0) {
			play = rst.get(0);
			if (playType != null && !playType.equals("")) {
				play.setTypeId(Integer.parseInt(playType));
			}
			if (playLang != null && !playLang.equals("")) {
				play.setLangId(Integer.parseInt(playLang));
			}
			if (playName != null && !playName.equals("")) {
				play.setName(playName);
			}
			if (playIntroduction != null && !playIntroduction.equals("")) {
				play.setIntroduction(playIntroduction);
			}
			if (playLength != null && !playLength.equals("")) {
				play.setLength(Integer.parseInt(playLength));
			}
			if (playPrice != null && !playPrice.equals("")) {
				play.setPrice(Float.parseFloat(playPrice));
			}
			if (playStatus != null && !playStatus.equals("")) {
				play.setStatus(Integer.parseInt(playStatus));
			}
			if (new PlaySrv().modify(play) == 1) {
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
			String str = "\"修改失败!未找到符合条件的演出厅\"";
			System.out.println(str);
			request.setAttribute("data", "{\"code\":2,\"data\":" + str + "}");
			request.getRequestDispatcher("/main/mobile_data.jsp").forward(request, response);
		}
	}

	public void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		String playId = request.getParameter("id");

		Play play = new Play();
		play.setId(Integer.parseInt(playId));
		List<Play> rst = new PlaySrv().Fetch(" play_id=" + playId);
		if (rst.size() > 0) {
			play = rst.get(0);
			if (new PlaySrv().delete(Integer.parseInt(playId)) == 1) {
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
			String str = "\"删除失败!未找到符合条件的演出厅\"";
			System.out.println(str);
			request.setAttribute("data", "{\"code\":2,\"data\":" + str + "}");
			request.getRequestDispatcher("/main/mobile_data.jsp").forward(request, response);
		}
	}

	public void destroy() {
		super.destroy();
	}

}
