package com.joe.billingSoftware;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;

public class Order {
	Connection con;
	static PreparedStatement ps;
	static ResultSet rs;
	static DecimalFormat df = new DecimalFormat("$#0.00");
	StringBuilder reciept;
	Double subtotal;



	Order() {
		reciept = new StringBuilder();
		subtotal = 0.0;
		con = Database.connectDB();
	}

	Double getSubtotal() {
		return this.subtotal;
	}

	void printRecipt() {
		System.out.println(this.reciept);
	}

	void findItem(int id, int qty) {
		double fullPrice = 0.0;

		try {
			//*TODO Add an if statement to only do this if id is in database.

			String sql = "select * from products where item_id = " + id;
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				Double price = rs.getDouble("price");
				String name = rs.getString("name");

				fullPrice = price * qty; 

				String entry = 
						"\nItem:\t" + name +
						"\n\tPrice:\t\t" + df.format(price) + 
						"\n\tQuanity:\t" + qty + 
						"\n\tFull Price\t" + df.format(fullPrice);
				System.out.println(entry);
				//*remove inventory from DB
				Database.removeInventory(id, qty);
				this.reciept.append(entry);
				this.subtotal =+ fullPrice;
			}

		}
		catch(SQLException e) {
			System.out.print("Could not find item!\n" + e);
		}

	}}
