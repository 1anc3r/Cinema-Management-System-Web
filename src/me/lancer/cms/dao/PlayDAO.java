package me.lancer.cms.dao;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import me.lancer.cms.idao.iPlayDAO;
import me.lancer.cms.model.Play;
import me.lancer.cms.util.DBUtil;

import java.sql.ResultSet;

public class PlayDAO implements iPlayDAO {

	@Override
	public int insert(Play ply) {
		try {
			String sqlstr = "insert into play( play_type_id, play_lang_id, play_name, play_introduction, play_image, play_length, play_ticket_price, play_status ) values( "
					+ ply.getTypeId() + ", " + ply.getLangId() + ", '" + ply.getName() + "', '" + ply.getIntroduction()
					+ "', '" + ply.getImage() + "', " + ply.getLength() + ", " + ply.getPrice() + ", " + ply.getStatus()
					+ " )";
			System.out.println(sqlstr);
			DBUtil db = new DBUtil();
			db.openConnection();
			ResultSet rst = db.getInsertObjectIDs(sqlstr);
			if (rst != null && rst.first()) {
				ply.setId(rst.getInt(1));
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
	public int update(Play ply) {
		int rtn = 0;
		try {
			String sqlstr = "update play set " + "play_id = " + ply.getId() + ", play_type_id = " + ply.getTypeId()
					+ ", play_lang_id = " + ply.getLangId() + ", play_name = '" + ply.getName()
					+ "', play_introduction = '" + ply.getIntroduction() + "', play_image = " + ply.getImage()
					+ ", play_length = " + ply.getLength() + ", play_ticket_price = " + ply.getPrice()
					+ ", play_status = " + ply.getStatus();

			sqlstr += " where play_id = " + ply.getId();
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
	public int delete(int ID) {
		int rtn = 0;
		try {
			String sqlstr = "delete from play ";
			sqlstr += " where play_id = " + ID;
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
	public List<Play> select(String condt) {
		List<Play> plyList = null;
		plyList = new LinkedList<Play>();
		try {
			String sqlstr = "select play_id ,play_type_id, play_lang_id, play_name, play_introduction, play_image, play_length, play_ticket_price, play_status from play ";
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
					Play ply = new Play();
					ply.setId(rst.getInt("play_id"));
					ply.setTypeId(rst.getInt("play_type_id"));
					ply.setLangId(rst.getInt("play_lang_id"));
					ply.setName(rst.getString("play_name"));
					ply.setIntroduction(rst.getString("play_introduction"));
					ply.setImage(rst.getString("play_image"));
					ply.setLength(rst.getInt("play_length"));
					ply.setPrice(rst.getInt("play_ticket_price"));
					ply.setStatus(rst.getInt("play_status"));
					plyList.add(ply);
				}
			}
			db.close(rst);
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return plyList;
	}

	@Override
	public List<Play> selectScheduledPlay(String condt) {
		List<Play> playList = null;
		playList = new LinkedList<Play>();
		try {
			String sqlstr = "select play.play_id, play_name from play, schedule where play.play_id=schedule.play_id ";
			condt.trim();
			if (!condt.isEmpty())
				sqlstr += " where " + condt;
			sqlstr += " group by play_name";
			DBUtil db = new DBUtil();
			if (!db.openConnection()) {
				return null;
			}
			ResultSet rst = db.execQuery(sqlstr);
			if (rst != null) {
				while (rst.next()) {
					Play stu = new Play();
					stu.setId(rst.getInt("play_id"));
					stu.setName(rst.getString("play_name"));
					playList.add(stu);
				}
			}
			db.close(rst);
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return playList;
	}

	@Override
	public List<Play> select_(Map<String, String> map) {
		List<Play> plyList = null;
		plyList = new LinkedList<Play>();
		String condt = "";
		if (map.get("id") != null && !map.get("id").equals("")) {
			if (condt.equals("")) {
				condt += " play_id=" + map.get("id") + "";
			} else {
				condt += " and play_id=" + map.get("id") + "";
			}
		}
		if (map.get("type") != null && !map.get("type").equals("")) {
			if (condt.equals("")) {
				condt += " play_type_id=" + map.get("type") + "";
			} else {
				condt += " and play_type_id=" + map.get("type") + "";
			}
		}
		if (map.get("lang") != null && !map.get("lang").equals("")) {
			if (condt.equals("")) {
				condt += " play_lang_id=" + map.get("lang") + "";
			} else {
				condt += " and play_lang_id=" + map.get("lang") + "";
			}
		}
		if (map.get("name") != null && !map.get("name").equals("")) {
			if (condt.equals("")) {
				condt += " play_name='" + map.get("name") + "'";
			} else {
				condt += " and play_name='" + map.get("name") + "'";
			}
		}
		if (map.get("introduction") != null && !map.get("introduction").equals("")) {
			if (condt.equals("")) {
				condt += " play_introduction='" + map.get("introduction") + "'";
			} else {
				condt += " and play_introduction='" + map.get("introduction") + "'";
			}
		}
		if (map.get("length") != null && !map.get("length").equals("")) {
			if (condt.equals("")) {
				condt += " play_length=" + map.get("length");
			} else {
				condt += " and play_length=" + map.get("length");
			}
		}
		if (map.get("price") != null && !map.get("price").equals("")) {
			if (condt.equals("")) {
				condt += " play_price=" + map.get("price");
			} else {
				condt += " and play_price=" + map.get("price");
			}
		}
		if (map.get("status") != null && !map.get("status").equals("")) {
			if (condt.equals("")) {
				condt += " play_status=" + map.get("status");
			} else {
				condt += " and play_status=" + map.get("status");
			}
		}
		try {
			String sqlstr = "select play_id ,play_type_id, play_lang_id, play_name, play_introduction, play_image, play_length, play_ticket_price, play_status from play ";
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
					Play ply = new Play();
					ply.setId(rst.getInt("play_id"));
					ply.setTypeId(rst.getInt("play_type_id"));
					ply.setLangId(rst.getInt("play_lang_id"));
					ply.setName(rst.getString("play_name"));
					ply.setIntroduction(rst.getString("play_introduction"));
					ply.setImage(rst.getString("play_image"));
					ply.setLength(rst.getInt("play_length"));
					ply.setPrice(rst.getInt("play_ticket_price"));
					ply.setStatus(rst.getInt("play_status"));
					plyList.add(ply);
				}
			}
			db.close(rst);
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return plyList;
	}
}
