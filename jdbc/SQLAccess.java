package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLAccess {

	final private static String hostName = "localhost";
	final private static String userName = "root";
	final private static String password = "admin123";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			// Load Drivers
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//Connect to Database using DriverManager
			Connection connection = DriverManager.getConnection("jdbc:mysql://"+hostName+"/seminar?"+"user="+userName+ "&password="+password);
			
			//Statement Created from connection which will allow us to run query
			Statement statement = connection.createStatement();
			
			// Running Select Query & storing all data in ResultSet
			ResultSet resultSet = statement.executeQuery("SELECT * FROM seminar.class");
			// Iterating all resultset objects & showing them in output
			while(resultSet.next()) {
				String firstName = resultSet.getString("first_name");
				String lastName = resultSet.getString("last_name");
				String phoneNumber = resultSet.getString("phone_number");
				String address = resultSet.getString("address");
				System.out.println("Name:"+ firstName + " "+ lastName);
				System.out.println("Phone Number:"+ phoneNumber);
				System.out.println("Address:"+ address);
				System.out.println("\n\n");
			}
//			System.out.println("End of the code");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
