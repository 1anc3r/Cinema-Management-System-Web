package me.lancer.cms.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import me.lancer.cms.model.Play;
import me.lancer.cms.service.PlaySrv;

public class PlaySlt extends HttpServlet {

	private static final long serialVersionUID = -1753934941774130429L;

	public PlaySlt() {
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
//		String playType = request.getParameter("type");
//		String playLang = request.getParameter("lang");
//		String playName = request.getParameter("name");
//		String playIntroduction = request.getParameter("introduction");
//		String playLength = request.getParameter("length");
//		String playPrice = request.getParameter("price");
//		if (playType != null && playLang != null && playName != null && playIntroduction != null && playLength != null
//				&& playPrice != null && !playType.equals("") && !playLang.equals("") && !playName.equals("")
//				&& !playIntroduction.equals("") && !playLength.equals("") && !playPrice.equals("")) {
//			Play play = new Play();
//			play.setTypeId(Integer.parseInt(playType));
//			play.setLangId(Integer.parseInt(playLang));
//			play.setName(playName);
//			play.setIntroduction(playIntroduction);
//			play.setLength(Integer.parseInt(playLength));
//			play.setPrice(Float.parseFloat(playPrice));
//			if (new PlaySrv().add(play) == 1) {
//				request.setAttribute("error", "添加成功!");
//				request.getRequestDispatcher("/main/play/play_add.jsp").forward(request, response);
//			} else {
//				request.setAttribute("error", "添加失败!请检查数据库状态后再提交添加");
//				request.getRequestDispatcher("/main/play/play_add.jsp").forward(request, response);
//			}
//		} else {
//			request.setAttribute("error", "添加失败!请将信息补充完整后再提交添加");
//			request.getRequestDispatcher("/main/play/play_add.jsp").forward(request, response);
//		}
		String paramName = "", paramValue = "";
		Play play = new Play();
		DiskFileItemFactory dff = new DiskFileItemFactory();
		dff.setSizeThreshold(1024000);
		ServletFileUpload sfu = new ServletFileUpload(dff);
		sfu.setFileSizeMax(1024 * 1024 * 2);
		try {
			List<FileItem> uploaditems = sfu.parseRequest(request);
			for (FileItem fileItem : uploaditems) {
				paramName = fileItem.getFieldName();
				System.out.print(paramName + ":");

				if (fileItem.isFormField()) {
					paramValue = new String(fileItem.getString().getBytes("ISO-8859-1"), "UTF-8");
					System.out.println(paramValue);

					if ("play_type".equals(paramName))
						play.setTypeId(Integer.parseInt(paramValue));
					if ("play_lang".equals(paramName))
						play.setLangId(Integer.parseInt(paramValue));
					if ("play_name".equals(paramName))
						play.setName(paramValue);
					if ("play_introduction".equals(paramName))
						play.setIntroduction(paramValue);
					if ("play_length".equals(paramName))
						play.setLength(Integer.parseInt(paramValue));
					if ("play_ticket_price".equals(paramName))
						play.setPrice(Float.parseFloat(paramValue));
				} else {
					Long size = fileItem.getSize();
					String fileName = fileItem.getName();
					fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
					System.out.println("\n文件名：" + fileName + "\t大小：" + size + "byte");
					File file = new File(getServletContext().getRealPath("/") + fileName);
					fileItem.write(file);
					play.setImage(getServletContext().getRealPath("/") + fileName);
				}
			}
			if (new PlaySrv().add(play) == 1) {
				System.out.println("添加成功!");
			} else {
				System.out.println("添加失败!请检查数据库状态后再提交添加");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("play", play);
		request.getRequestDispatcher("/main/play/play_list.jsp").forward(request, response);
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
			request.setAttribute("error", null);
			request.setAttribute("list", playList);
			request.getRequestDispatcher("/main/play/play_fetch.jsp").forward(request, response);
		} else {
			request.setAttribute("error", "未找到符合条件的剧目!");
			request.getRequestDispatcher("/main/play/play_fetch.jsp").forward(request, response);
		}
	}

	public void doModify(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

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
				request.setAttribute("error", "修改成功!");
				request.getRequestDispatcher("/main/play/play_modify.jsp").forward(request, response);
			} else {
				request.setAttribute("error", "修改失败!请检查数据库状态后再提交修改");
				request.getRequestDispatcher("/main/play/play_modify.jsp").forward(request, response);
			}
		} else {
			request.setAttribute("error", "修改失败!未找到符合条件的演出厅");
			request.getRequestDispatcher("/main/play/play_modify.jsp").forward(request, response);
		}
	}

	public void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		String playId = request.getParameter("id");

		Play play = new Play();
		play.setId(Integer.parseInt(playId));
		List<Play> rst = new PlaySrv().Fetch(" play_id=" + playId);
		if (rst.size() > 0) {
			play = rst.get(0);
			if (new PlaySrv().delete(Integer.parseInt(playId)) == 1) {
				request.setAttribute("error", "删除成功!");
				request.getRequestDispatcher("/main/play/play_delete.jsp").forward(request, response);
			} else {
				request.setAttribute("error", "删除失败!请检查数据库状态后再提交删除");
				request.getRequestDispatcher("/main/play/play_delete.jsp").forward(request, response);
			}
		} else {
			request.setAttribute("error", "删除失败!未找到符合条件的演出厅");
			request.getRequestDispatcher("/main/play/play_delete.jsp").forward(request, response);
		}
	}

	public void destroy() {
		super.destroy();
	}

}
