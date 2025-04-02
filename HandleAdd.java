package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;
import models.Goods;
import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/HandleAdd")
public class HandleAdd extends HttpServlet{
	
	@Resource(name="jdbc/MyDB")
	private DataSource dataSource; //we fetched database info
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) {
		
		String name= request.getParameter("itemname");
		String desc = request.getParameter("itemdesc");
		int price = Integer.parseInt(request.getParameter("itemprice"));

		try (Connection conn = dataSource.getConnection()){
			if(request.getParameter("id")!=null) { //if its update opp id is present
				int id = Integer.parseInt( request.getParameter("id"));
				//update query
				String query = "UPDATE goods SET name=?, description=?,price=? WHERE id=?";
				try(PreparedStatement stmt = conn.prepareStatement(query)){
					stmt.setString(1, name);
					stmt.setString(2, desc);
					stmt.setInt(3, price);
					stmt.setInt(4,id);
					stmt.executeUpdate();
					response.sendRedirect("show");
				}
			}else {
				//create opp
				String query = "INSERT INTO goods (name,description,price) VALUES(?,?,?)";
				try(PreparedStatement stmt = conn.prepareStatement(query)){
					stmt.setString(1, name);
					stmt.setString(2, desc);
					stmt.setInt(3, price);
					stmt.executeUpdate();
					response.sendRedirect("show");
				}catch(Exception e) {
					response.getWriter().println(e.getMessage());
				}
			}
		
			
			
			
		}catch(Exception e) {
			System.out.println("<h1>"+e.getStackTrace()+"</h1>");
		}
		
	}
	

}
