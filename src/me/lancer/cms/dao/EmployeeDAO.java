package me.lancer.cms.dao;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import me.lancer.cms.idao.iEmployeeDAO;
import me.lancer.cms.model.Employee;
import me.lancer.cms.model.Studio;
import me.lancer.cms.util.DBUtil;

import java.sql.ResultSet;

public class EmployeeDAO implements iEmployeeDAO {

	@Override
	public int insert(Employee emp) {
		try {
			String sqlstr = "insert into employee(emp_access, emp_no, emp_name, emp_password, emp_addr, emp_tel, emp_email, emp_image) values( "
					+ emp.getAccess() + ", " + emp.getNo() + ", '" + emp.getName() + "', '" + emp.getPassword() + "', '"
					+ emp.getAddr() + "', '" + emp.getTel() + "', '" + emp.getEmail() + "', '" + emp.getImage() + "')";
			System.out.println(sqlstr);
			DBUtil db = new DBUtil();
			db.openConnection();
			ResultSet rst = db.getInsertObjectIDs(sqlstr);
			if (rst != null && rst.first()) {
				emp.setId(rst.getInt(1));
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
	public int update(Employee emp) {
		int rtn = 0;
		try {
			String sqlstr = "update employee set emp_id = " + emp.getId() + ", emp_access = " + emp.getAccess()
					+ ", emp_name = '" + emp.getName() + "', emp_password = '" + emp.getPassword() + "', emp_tel = '"
					+ emp.getTel() + "', emp_addr = '" + emp.getAddr() + "', emp_email = '" + emp.getEmail()
					+ "', emp_no = " + emp.getNo();
			sqlstr += " where emp_id = " + emp.getId();
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
			String sqlstr = "delete from employee ";
			sqlstr += " where emp_id = " + id;
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
	public List<Employee> select(String condt) {
		List<Employee> empList = null;
		empList = new LinkedList<Employee>();
		try {
			String sqlstr = "select * from employee ";
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
					Employee emp = new Employee();
					emp.setId(rst.getInt("emp_id"));
					emp.setAccess(rst.getInt("emp_access"));
					emp.setName(rst.getString("emp_name"));
					emp.setPassword(rst.getString("emp_password"));
					emp.setTel(rst.getString("emp_tel"));
					emp.setAddr(rst.getString("emp_addr"));
					emp.setEmail(rst.getString("emp_email"));
					emp.setNo(rst.getInt("emp_no"));
					emp.setImage(rst.getString("emp_image"));
					empList.add(emp);
				}
			}
			db.close(rst);
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return empList;
	}

	@Override
	public List<Employee> select_(Map<String, String> map) {
		List<Employee> empList = null;
		empList = new LinkedList<Employee>();
		String condt = "";
		if (map.get("access")!= null && !map.get("access").equals("")) {
			if (condt.equals("")) {
				condt += " emp_access=" + map.get("access") + "";
			} else {
				condt += " and emp_access=" + map.get("access") + "";
			}
		}
		if (map.get("id")!= null && !map.get("id").equals("")) {
			if (condt.equals("")) {
				condt += " emp_id='" + map.get("id") + "'";
			} else {
				condt += " and emp_id='" + map.get("id") + "'";
			}
		}
		if (map.get("name")!= null && !map.get("name").equals("")) {
			if (condt.equals("")) {
				condt += " emp_name='" + map.get("name") + "'";
			} else {
				condt += " and emp_name='" + map.get("name") + "'";
			}
		}
		if (map.get("address")!= null && !map.get("address").equals("")) {
			if (condt.equals("")) {
				condt += " emp_addr='" + map.get("address") + "'";
			} else {
				condt += " and emp_addr='" + map.get("address") + "'";
			}
		}
		if (map.get("tel")!= null && !map.get("tel").equals("")) {
			if (condt.equals("")) {
				condt += " emp_tel='" + map.get("tel") + "'";
			} else {
				condt += " and emp_tel='" + map.get("tel") + "'";
			}
		}
		if (map.get("email")!= null && !map.get("email").equals("")) {
			if (condt.equals("")) {
				condt += " emp_email='" + map.get("email") + "'";
			} else {
				condt += " and emp_email='" + map.get("email") + "'";
			}
		}
		try {
			String sqlstr = "select emp_id, emp_access, emp_name, emp_password, emp_tel, emp_addr, emp_email,emp_no, emp_image from employee ";
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
					Employee emp = new Employee();
					emp.setId(rst.getInt("emp_id"));
					emp.setAccess(rst.getInt("emp_access"));
					emp.setName(rst.getString("emp_name"));
					emp.setPassword(rst.getString("emp_password"));
					emp.setTel(rst.getString("emp_tel"));
					emp.setAddr(rst.getString("emp_addr"));
					emp.setEmail(rst.getString("emp_email"));
					emp.setNo(rst.getInt("emp_no"));
					emp.setImage(rst.getString("emp_image"));
					empList.add(emp);
				}
			}
			db.close(rst);
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return empList;
	}
}
