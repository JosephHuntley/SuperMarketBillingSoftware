package com.joe.billingSoftware;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class Database {
	static Connection con;



	static Connection connectDB(){
		final String JDBC_URL = "jdbc:mysql://10.0.0.75:3306/sample_store";
		final String DB_USERNAME ="admin";
		final String DB_PASSWD = "toor";

		try {
			con = DriverManager.getConnection( JDBC_URL,
					DB_USERNAME, DB_PASSWD);
			if (con != null) {
				System.out.println("\tConnected to the database!");
			}

		}
		catch (SQLException e) {
			System.out.println("\nError: could not connect to the database!");
			System.out.println(e);
		}

		return con;
	}

	static void removeInventory(int id, int qty) {

		try {
			Statement stmt = (Statement) con.createStatement();
			String sql = "select inventory from products where item_id = " + id;
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int inventory = rs.getInt("inventory");
				inventory -= qty;

				String querry = "UPDATE `sample_store`.`products` SET `inventory` = "
						+ inventory + " WHERE(`item_id` = " + id + ")";
				stmt.executeUpdate(querry);
			}


		}
		catch(SQLException e) {
			System.out.print(e);

		}
	}

}
