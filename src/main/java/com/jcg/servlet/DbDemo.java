package com.jcg.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/servletDbConnectionDemo")
public class DbDemo extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/***** This Method Is Called By The Servlet Container To Process A 'GET' Request *****/
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		handleRequest(request, response);
	}

	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		/***** Set Response Content Type *****/
		response.setContentType("text/html");

		/***** Print The Response *****/
		PrintWriter out = response.getWriter();
		String title = "Employee Details";
		String pageTitle = "Servlet Database Connectivity Example";
		String docType = "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n";
		out.println(docType +
				"<html>\n" + "<head><title>" + pageTitle + "</title></head>\n");

		try {
			ResultSet rs = DbDao.getEmployeeList();
			if(rs.next()) {
				out.println("<body>\n" + "<h2 align = \"center\" style = \"color: green;\">" + title + "</h2>\n" + 
						"<table width = \"450px\" border = \"1\" align = \"center\">\n" + 
						"<thead><tr align = \"center\"><th><strong>Emp. Id</strong></th><th><strong>Emp. Name</strong></th><th><strong>Emp. Salary (in '$')</strong></th></tr></thead>\n<tbody>");

				do {
					out.println("<tr align = \"center\"><td>" + rs.getString("emp_id") + "</td><td>" + rs.getString("emp_name") + "</td><td>" + rs.getString("emp_salary") + "</td></tr>");
				} while(rs.next()) ;

				out.println("</tbody>\n</table></body></html>");
			} else {
				out.println("<body>\n" + "<h2 align = \"center\" style = \"color: red;\">No Employees Found In The Db....!</h2>\n" + "</body>");
			}
			out.println("</html>");
			out.close();
		} catch(Exception exObj) {
			exObj.printStackTrace();
		} finally {
			DbDao.disconnectDb();
		}
	}
}