package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Costumer;

public class CostumerDAOI implements CostumerDAO {

	@Override
	public List<Costumer> getCostumerList() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb","root","MySQLrp");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);
			ResultSet rs = statement.executeQuery("SELECT * FROM costumer");
			
			List<Costumer> cList = new ArrayList<Costumer>();
			while(rs.next()) {
				Costumer c = new Costumer(rs.getInt("costumer_ID"),
										  rs.getString("title"),
										  rs.getString("first_name"),
										  rs.getString("last_name"),
										  rs.getString("drivers_license_number"),
										  rs.getDate("birth_date"),
										  rs.getString("email"),
										  rs.getString("post_code"),
										  rs.getString("street"),
										  rs.getString("house_number"),
										  rs.getString("appartment_number"),
										  rs.getString("town"),
										  rs.getString("country"),
										  rs.getString("pwd_hash"),
										  rs.getString("salt"),
										  rs.getBoolean("active"));
				cList.add(c);
			}
			return cList;
		}catch (SQLException | ClassNotFoundException e) {
			System.err.println(e.getMessage());
		}finally {
			try {
				if (connection != null)
					connection.close();
			}catch (SQLException e) {
				System.err.println(e);
			}
		}
		return null;
	}

	@Override
	public Costumer getCostumerById(int ID) {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb","root","MySQLrp");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);
			String raw_query = "SELECT * FROM costumer WHERE costumer_ID = ?";
			PreparedStatement prepared = connection.prepareStatement(raw_query);
			prepared.setInt(1, ID);
			ResultSet rs = prepared.executeQuery();
			Costumer c = new Costumer(rs.getInt("costumer_ID"),
										  rs.getString("title"),
										  rs.getString("first_name"),
										  rs.getString("last_name"),
										  rs.getString("drivers_license_number"),
										  rs.getDate("birth_date"),
										  rs.getString("email"),
										  rs.getString("post_code"),
										  rs.getString("street"),
										  rs.getString("house_number"),
										  rs.getString("appartment_number"),
										  rs.getString("town"),
										  rs.getString("country"),
										  rs.getString("pwd_hash"),
										  rs.getString("salt"),
										  rs.getBoolean("active"));
			return c;
		}catch (SQLException | ClassNotFoundException e) {
			System.err.println(e.getMessage());
		}finally {
			try {
				if (connection != null)
					connection.close();
			}catch (SQLException e) {
				System.err.println(e);
			}
		}
		return null;
	}

	@Override
	public void addCostumer(Costumer c) {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb","root","MySQLrp");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);
			String raw_query = "INSERT INTO costumer (title, first_name, last_name, drivers_license_number, "
						     					   + "birth_date, email, post_code, street, house_number, "
						     					   + "appartment_number, town, country, pwd_hash, salt, active) "
						     			   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement prepared = connection.prepareStatement(raw_query);
			prepared.setString(1, c.getTitle());
			prepared.setString(2, c.getFirst_name());
			prepared.setString(3, c.getLast_name());
			prepared.setString(4, c.getDrivers_licens_number());
			java.util.Date utilBirthDate = c.getBirth_date();
			java.sql.Date sqlBirthDate = new java.sql.Date(utilBirthDate.getTime());
			prepared.setDate(5, sqlBirthDate);
			prepared.setString(6, c.getEmail());
			prepared.setString(7, c.getPost_code());
			prepared.setString(8, c.getStreet());
			prepared.setString(9, c.getHouse_number());
			prepared.setString(10, c.getAppartment_number());
			prepared.setString(11, c.getTown());
			prepared.setString(12, c.getCountry());
			prepared.setString(13, c.getPwd_hash());
			prepared.setString(14, c.getSalt());
			prepared.setBoolean(15, c.getActive());
			
			prepared.executeUpdate();
		}catch (SQLException | ClassNotFoundException e) {
			System.err.println(e.getMessage());
		}finally {
			try {
				if (connection != null)
					connection.close();
			}catch (SQLException e) {
				System.err.println(e);
			}
		}
	}

	@Override
	public void changeCostumer(Costumer c) {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb","root","MySQLrp");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);
			String raw_query = "UPDATE costumer SET title = ?, first_name = ? last_name = ?, drivers_license_number = ?, "
						     					 + "birth_date = ?, email = ?, post_code = ?, street = ?, house_number = ?, "
						     					 + "appartment_number = ?, town = ?, country = ?, pwd_bash = ?, salt = ?, "
						     					 + "active = ? WHERE costumer_ID = ? ";
			PreparedStatement prepared = connection.prepareStatement(raw_query);
			prepared.setString(1, c.getTitle());
			prepared.setString(2, c.getFirst_name());
			prepared.setString(3, c.getLast_name());
			prepared.setString(4, c.getDrivers_licens_number());
			prepared.setDate(5, (Date) c.getBirth_date());
			prepared.setString(6, c.getEmail());
			prepared.setString(7, c.getPost_code());
			prepared.setString(8, c.getStreet());
			prepared.setString(9, c.getHouse_number());
			prepared.setString(10, c.getAppartment_number());
			prepared.setString(11, c.getTown());
			prepared.setString(12, c.getCountry());
			prepared.setString(13, c.getPwd_hash());
			prepared.setString(14, c.getSalt());
			prepared.setBoolean(15, c.getActive());
			prepared.setInt(16, c.getCostumer_ID());
			
			prepared.executeUpdate();
		}catch (SQLException | ClassNotFoundException e) {
			System.err.println(e.getMessage());
		}finally {
			try {
				if (connection != null)
					connection.close();
			}catch (SQLException e) {
				System.err.println(e);
			}
		}
	}

	@Override
	public void deleteCostumer(int ID) {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb","root","MySQLrp");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);
			String raw_query = "SELECT * FROM costumer WHERE costumer_ID = ?";
			PreparedStatement prepared = connection.prepareStatement(raw_query);
			prepared.setInt(1, ID);
			prepared.executeUpdate();
		}catch (SQLException | ClassNotFoundException e) {
			System.err.println(e.getMessage());
		}finally {
			try {
				if (connection != null)
					connection.close();
			}catch (SQLException e) {
				System.err.println(e);
			}
		}
	}
}
