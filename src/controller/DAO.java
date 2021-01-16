package controller;
import java.sql.*;
import view.*;
public class DAO {
	private Connection conn;
	public Connection DAOC() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://localhost:1433;databaseName=QLDien;";
			conn= DriverManager.getConnection(url,"sa","sa");
            return conn;

		} catch (Exception e) {
			// TODO: handle exception

			e.printStackTrace();
			return null;
		}
	}
	
	public static void main(String[] args) {

		new FirstView();
	}
}
