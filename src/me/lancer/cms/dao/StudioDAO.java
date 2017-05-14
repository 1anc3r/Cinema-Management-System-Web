package me.lancer.cms.dao;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import me.lancer.cms.idao.iStudioDAO;
import me.lancer.cms.model.Seat;
import me.lancer.cms.model.Studio;
import me.lancer.cms.util.DBUtil;

import java.sql.ResultSet;

public class StudioDAO implements iStudioDAO {

	SeatDAO seatDAO = new SeatDAO();

	@Override
	public int insert(Studio stud) {
		try {
			String sqlstr = "insert into studio( studio_name, studio_row_count, studio_col_count, studio_introduction, studio_flag ) values( '"
					+ stud.getName() + "', " + stud.getRowCount() + ", " + stud.getColCount() + ", '"
					+ stud.getIntroduction() + "', 1 )";
			DBUtil db = new DBUtil();
			db.openConnection();
			ResultSet rst = db.getInsertObjectIDs(sqlstr);
			if (rst != null && rst.first()) {
				stud.setID(rst.getInt(1));
				createSeats(stud);
			}
			db.close(rst);
			db.close();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int update(Studio stud) {
		int rtn = 0;
		try {
			String sqlstr = "update studio set studio_name = '" + stud.getName() + "', studio_row_count = "
					+ stud.getRowCount() + ", studio_col_count = " + stud.getColCount() + ", studio_introduction = '"
					+ stud.getIntroduction() + "', studio_flag = " + stud.getStudioFlag();
			sqlstr += " where studio_id = " + stud.getID();
			DBUtil db = new DBUtil();
			db.openConnection();
			rtn = db.execCommand(sqlstr);
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtn;
	}

	@Override
	public int delete(int id) {
		int rtn = 0;
		try {
			String sqlstr = "delete from studio ";
			sqlstr += " where studio_id = " + id;
			DBUtil db = new DBUtil();
			db.openConnection();
			rtn = db.execCommand(sqlstr);
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtn;
	}

	@Override
	public List<Studio> select(String condt) {
		List<Studio> stuList = null;
		stuList = new LinkedList<Studio>();
		try {
			String sqlstr = "select studio_id, studio_name, studio_row_count, studio_col_count, studio_introduction , studio_flag from studio ";
			condt.trim();
			if (!condt.isEmpty() && !condt.contains("limit")) {
				sqlstr += " where " + condt;
			} else {
				sqlstr += condt;
			}
			DBUtil db = new DBUtil();
			if (!db.openConnection()) {
				return null;
			}
			ResultSet rst = db.execQuery(sqlstr);
			if (rst != null) {
				while (rst.next()) {
					Studio stu = new Studio();
					stu.setID(rst.getInt("studio_id"));
					stu.setColCount(rst.getInt("studio_col_count"));
					stu.setRowCount(rst.getInt("studio_row_count"));
					stu.setStudioFlag(rst.getInt("studio_flag"));
					stu.setName(rst.getString("studio_name"));
					stu.setIntroduction(rst.getString("studio_introduction"));
					stuList.add(stu);
				}
			}
			db.close(rst);
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return stuList;
	}

	@Override
	public int createSeats(Studio stud) {
		Seat seat = new Seat();
		try {
			for (int i = 1; i <= stud.getRowCount(); i++)
				for (int j = 1; j <= stud.getColCount(); j++) {
					seat.setStudioId(stud.getID());
					seat.setRow(i);
					seat.setColumn(j);
					seat.setSeatStatus(1);
					seatDAO.insert(seat);
				}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

	public List<Studio> select_(Map<String, String> map) {
		List<Studio> stuList = null;
		stuList = new LinkedList<Studio>();
		String condt = "";
		if (map.get("id")!= null && !map.get("id").equals("")) {
			if (condt.equals("")) {
				condt += " studio_id=" + map.get("id") + "";
			} else {
				condt += " and studio_id=" + map.get("id") + "";
			}
		}
		if (map.get("name")!= null && !map.get("name").equals("")) {
			if (condt.equals("")) {
				condt += " studio_name='" + map.get("name") + "'";
			} else {
				condt += " and studio_name='" + map.get("name") + "'";
			}
		}
		if (map.get("row")!= null && !map.get("row").equals("")) {
			if (condt.equals("")) {
				condt += " studio_row_count=" + map.get("row");
			} else {
				condt += " and studio_row_count=" + map.get("row");
			}
		}
		if (map.get("col")!= null && !map.get("col").equals("")) {
			if (condt.equals("")) {
				condt += " studio_col_count=" + map.get("col");
			} else {
				condt += " and studio_col_count=" + map.get("col");
			}
		}
		if (map.get("introduction")!= null && !map.get("introduction").equals("")) {
			if (condt.equals("")) {
				condt += " studio_introduction='" + map.get("introduction") + "'";
			} else {
				condt += " and studio_introduction='" + map.get("introduction") + "'";
			}
		}
		if (map.get("flag")!= null && !map.get("flag").equals("")) {
			if (condt.equals("")) {
				condt += " studio_flag=" + map.get("flag");
			} else {
				condt += " and studio_flag=" + map.get("flag");
			}
		}
		try {
			String sqlstr = "select studio_id, studio_name, studio_row_count, studio_col_count, studio_introduction , studio_flag from studio ";
			condt.trim();
			if (!condt.isEmpty())
				sqlstr += " where " + condt;
			System.out.println(sqlstr);
			DBUtil db = new DBUtil();
			if (!db.openConnection()) {
				return null;
			}
			ResultSet rst = db.execQuery(sqlstr);
			if (rst != null) {
				while (rst.next()) {
					Studio stu = new Studio();
					stu.setID(rst.getInt("studio_id"));
					stu.setColCount(rst.getInt("studio_col_count"));
					stu.setRowCount(rst.getInt("studio_row_count"));
					stu.setStudioFlag(rst.getInt("studio_flag"));
					stu.setName(rst.getString("studio_name"));
					stu.setIntroduction(rst.getString("studio_introduction"));
					stuList.add(stu);
				}
			}
			db.close(rst);
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return stuList;
	}
}
