package application;

import db.DB;
import db.DbException;
import db.DbIntegrityException;

import java.sql.*;

public class Program {

    public static void main(String[] args) {

    Connection conn = null;
    Statement st = null;

    try{
        conn = DB.getConnection();

        conn.setAutoCommit(false);

        st = conn.createStatement();

        int rows = st.executeUpdate("UPDATE seller SET BaseSalary = 2090"
                +" WHERE DepartmentId = 1");

        /*
        int x = 1;
        if(x<2){
            throw new SQLException("Fake error");
        }*/

        int rows1 = st.executeUpdate("UPDATE seller SET BaseSalary = 3090"
                +" WHERE DepartmentId = 2");

        conn.commit();

        System.out.println("Done! Rows affected: " + rows + ", " + rows1);

    }
    catch (SQLException e){
        try {
            conn.rollback();
            throw new DbException("Transaction rolled back! Caused by: " + e.getMessage());
        } catch (SQLException ex) {
            throw new DbException("Error trying to rollback! Caused by: " + e.getMessage());
        }
    }
    finally {
        DB.closeStatement(st);
        DB.closeConnection();
    }







    }
}
