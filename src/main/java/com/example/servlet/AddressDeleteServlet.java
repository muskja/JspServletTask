package com.example.servlet;

import com.example.util.DBUtil;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddressDeleteServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user_Id = request.getParameter("user_Id");
        String Id = request.getParameter("Id");

        // Assuming you have a DBUtil class to manage database connections
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // Get a database connection using your DBUtil class
            conn = DBUtil.getConnection();

            // Prepare the SQL statement to delete the address
            String sql = "DELETE FROM addresses WHERE user_Id = ? AND Id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user_Id);
            pstmt.setString(2, Id);

            // Execute the update
            pstmt.executeUpdate();

            // Redirect back to the original JSP page
            response.sendRedirect("index.jsp");
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            // Close the database resources
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
