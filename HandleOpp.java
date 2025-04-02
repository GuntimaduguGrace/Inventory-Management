package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.sql.DataSource;

import models.Goods;
import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/HandleOpp")
public class HandleOpp extends HttpServlet {
    
    @Resource(name = "jdbc/MyDB")
    private DataSource dataSource;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getParameter("action");
        int id = Integer.parseInt(request.getParameter("id"));

        try (Connection conn = dataSource.getConnection()) {
            if ("delete".equals(action)) {
                String query = "DELETE FROM goods WHERE id=?";
                try (PreparedStatement stmt = conn.prepareStatement(query)) {
                    stmt.setInt(1, id);
                    stmt.executeUpdate();
                }
             
                response.sendRedirect("index.jsp");

            } else if ("edit".equals(action)) {
                String query = "SELECT * FROM goods WHERE id=?";
                try (PreparedStatement stmt = conn.prepareStatement(query)) {
                    stmt.setInt(1, id);
                    try (ResultSet res = stmt.executeQuery()) {
                        if (res.next()) {  
                            Goods item = new Goods(
                                res.getInt("id"),
                                res.getString("name"),
                                res.getString("description"), 
                                res.getInt("price")
                            );

                            request.setAttribute("goods", item);
                            RequestDispatcher dispatch = request.getRequestDispatcher("addInventory.jsp");
                            dispatch.forward(request, response);
                        } else {
                            response.getWriter().println("Item not found");
                        }
                    }
                }
            } else {
                response.getWriter().println("Invalid action!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}
