package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.user.User;

public class dbOperation {

	private String URL = "jdbc:mysql://localhost:3306/java";
	private String Username = "root";
	private String Password = "";

	public Connection dbconnect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(URL, Username, Password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public void insertUser(User user) {
		try {
			Connection conn = dbconnect();
			PreparedStatement ps = conn
					.prepareStatement("INSERT INTO `crud`(`name`, `email`, `contact`, `city`) VALUES (?,?,?,?);");
			System.out.println(user.getName() + user.getEmail() + user.getCity() + user.getNumber());
			ps.setString(1, user.getName());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getNumber());
			ps.setString(4, user.getCity());
			ps.executeUpdate();
		} catch (Exception e) {
			System.out.print(e);
			e.printStackTrace();
		}
	}

	public List<User> AllUsers() {
		List<User> data = new ArrayList<>();
		try {
			Connection conn = dbconnect();
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM `crud`;");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String number = rs.getString("contact");
				String city = rs.getString("city");
				data.add(new User(id, name, email, number, city));
			}

		} catch (Exception e) {
			System.out.print(e);
			e.printStackTrace();
		}
		return data;
	}

	public boolean deleteUser(int id) {
		boolean deletedrow = false;
		try {
			Connection conn = dbconnect();
			PreparedStatement ps = conn.prepareStatement("DELETE FROM `crud` WHERE id = ?;");

			ps.setInt(1, id);
			deletedrow = ps.executeUpdate() > 0;
		} catch (Exception e) {
			System.out.print(e);
			e.printStackTrace();
		}
		return deletedrow;
	}

	public User selectUser(int id) {
		User user = null;
		try {
			Connection conn = dbconnect();
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM `crud` WHERE id = ?;");

			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				String name = rs.getString("name");
				String email = rs.getString("email");
				String number = rs.getString("contact");
				String city = rs.getString("city");
				user = new User(id, name, email, number, city);
			}
		} catch (Exception e) {
			System.out.print(e);
			e.printStackTrace();
		}
		return user;
	}

	public boolean updateUser(User user) {
		boolean rowaffected = false;
		try {
			Connection conn = dbconnect();
			PreparedStatement ps = conn.prepareStatement(
					"UPDATE `crud` SET `name`=?,`email`=?,`contact`=?,`city`= ? WHERE id = ?;");
			System.out.println(user.getName() + user.getEmail() + user.getCity() + user.getNumber());
			ps.setString(1, user.getName());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getNumber());
			ps.setString(4, user.getCity());
			ps.setInt(5, user.getId());
			rowaffected = ps.executeUpdate() > 0;
		} catch (Exception e) {
			System.out.print(e);
			e.printStackTrace();
		}
		return rowaffected;
	}

}
