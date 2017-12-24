package datagenerate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import dao.CostumerDAOI;
import model.Costumer;

public class CostumerGenerator {
	
	public static void createDB() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/?user=root&password=MySQLrp");
			PreparedStatement ps = connection.prepareStatement("CREATE DATABASE myimsedb");
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (connection != null)
					connection.close();
			}catch (SQLException e) {
				System.err.println(e);
			}
		}
	}
	
	public static void dropDB() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/?user=root&password=MySQLrp");
			PreparedStatement ps = connection.prepareStatement("DROP DATABASE myimsedb");
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (connection != null)
					connection.close();
			}catch (SQLException e) {
				System.err.println(e);
			}
		}
	}
	
	
	public static void createCostumer() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//DriverManager.registerDriver(new com.mysql.jdbc.Driver ());
			connection = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb","root","MySQLrp");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);
			String raw_query = "CREATE TABLE costumer (costumer_ID int AUTO_INCREMENT, " + 
													  "title varchar(20), " + 
													  "first_name varchar(120) NOT NULL, " + 
													  "last_name varchar(120) NOT NULL, " + 
													  "drivers_license_number varchar(15) NOT NULL UNIQUE, " + 
													  "birth_date date NOT NULL, " + 
													  "email varchar(120) NOT NULL, " + 
													  "post_code varchar(10), " + 
													  "street varchar(120), " + 
													  "house_number	varchar(10), " + 
													  "appartment_number varchar(10), " + 
													  "town	varchar(100), " + 
													  "country varchar(50), " + 
													  "pwd_hash	varchar(50), " + 
													  "salt	varchar(20), " + 
													  "active boolean DEFAULT true, " + 
													  "PRIMARY KEY (costumer_ID));";
			statement.executeUpdate(raw_query);
		}catch (SQLException | ClassNotFoundException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}finally {
			try {
				if (connection != null)
					connection.close();
			}catch (SQLException e) {
				System.err.println(e);
			}
		}
	}
	
	public static void fillCostumer() {
		CostumerDAOI cost = new CostumerDAOI();
		String[] title = {"'Prof.'", "'Dr.'", "'Mag.'","'PhD.'"};
	    String[] firstname = {"'Franz'", "'Hans'", "'Sieglinde'", "'Manuela'", "'Karl'"};
	    String[] lastname = {"'Markart'", "'Pliger'", "'Stuffer'","'Mair'","'Sauermoser'"};
	    String[] dln = {"'12345A'", "'34856B'", "'G57H4'", "'OPER3'", "'K3589'"};
	    String[] postcode = {"'39042'", "'1160'", "'39040'", "'1001'", "'34097'"};
	    String[] street = {"'Gassergasse'", "'Baeckergasse'", "'Sandleitengasse'", "'Turmgasse'", "'Route 66'"};
	    String[] hn = {"'1'", "'34'", "'3'", "'12'", "'765'"};
	    String[] an = {"'1'", "'43'", "'7'", "'68'", "'123'"};
	    String[] town = {"'Brixen'", "'Wien'", "'Bozen'", "'New York'", "'Peking'"};
	    String[] country = {"'Italien'", "'Schweden'", "'USA'", "'Argentinien'", "'Ghana'"};
	    String[] pwd = {"'pgög'", "'Hahjda'", "'afhaeuf'", "'adf'", "'sdfar'"};
	    String[] salt = {"'234'", "'467'", "'366'", "'754'", "'460'"};
	    
	    int tii = 0;
	    int fi = 0;
	    int li = 0;
	    int di = 0;
	    int poi = 0;
	    int sti = 0;
	    int hi = 0;
	    int ai = 0;
	    int toi = 0;
	    int ci = 0;
	    int pi = 0;
	    int si = 0;
	    
	    for (int i = 0; i < 10; i++) {
			tii = (int)((Math.random()) * 4);
			fi = (int)((Math.random()) * 5);
			li = (int)((Math.random()) * 5);
			poi = (int)((Math.random()) * 5);
			sti = (int)((Math.random()) * 5);
			hi = (int)((Math.random()) * 5);
			ai = (int)((Math.random()) * 5);
			toi = (int)((Math.random()) * 5);
			ci = (int)((Math.random()) * 5);
			pi = (int)((Math.random()) * 5);
			si = (int)((Math.random()) * 5);
			
			Costumer c = new Costumer(0, title[tii], firstname[fi], lastname[li], dln[di] + i, 
									  new Date(), firstname[fi] + i + "@gmail.com", postcode[poi], 
									  street[sti], hn[hi], an[ai], town[toi], country[ci], pwd[pi], 
									  salt[si], true);
			
			cost.addCostumer(c);
		}
	}
	
	public static void main(String[] args) {
		dropDB();
		createDB();
		createCostumer();
		fillCostumer();
		CostumerDAOI co = new CostumerDAOI();
		System.out.println(co.getCostumerList().toString());
	}
}
