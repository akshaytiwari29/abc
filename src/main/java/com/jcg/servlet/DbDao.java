package com.jcg.servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbDao {

	static ResultSet rsObj = null;
	static Statement stmtObj = null;
	static Connection connObj = null;

	/***** Method #1 :: This Method Is Used To Create A Connection With The Database *****/
	private static Connection connectDb() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connObj = DriverManager.getConnection("jdbc:mysql://34.73.203.147:3306/servletDb", "root", "admin");			
		} catch (Exception exObj) {
			exObj.printStackTrace();
		}
		return connObj;
	}

	/***** Method #2 :: This Method Is Used To Retrieve The Records From The Database *****/
	public static ResultSet getEmployeeList() {				
		try {
			stmtObj = connectDb().createStatement();

			String sql = "SELECT * FROM servletDb.EmployeeTbl";
			rsObj = stmtObj.executeQuery(sql);
		} catch (Exception exObj) {
			exObj.printStackTrace();
		}
		return rsObj;
	}

	/***** Method #3 :: This Method Is Used To Close The Connection With The Database *****/
	public static void disconnectDb() {
		try {
			rsObj.close();
			stmtObj.close();
			connObj.close();
		} catch (Exception exObj) {
			exObj.printStackTrace();
		}		
	}
}