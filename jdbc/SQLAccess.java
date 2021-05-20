package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLAccess {

	final private static String hostName = "localhost";// 127.0.0.1 192.168.122.1
	final private static String userName = "root";
	final private static String password = "admin123";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			// Load Drivers
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Connect to Database using DriverManager
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://" + hostName + "/seminar?" + "user=" + userName + "&password=" + password);

			// Statement Created from connection which will allow us to run query
			Statement statement = connection.createStatement();

			/**
			 * statement.execute CREATION & INSERTION statement.executeUpdate CREATION &
			 * INSERTION, UPDATION statement.executeQuery - it returns you some data from db
			 */

			// Create Table in Database
			final String createTableQuery = "CREATE TABLE `seminar`.`sample` ("
					+ "  `id` INT AUTO_INCREMENT,"
					+ "  `first_name` VARCHAR(45) NULL,"
					+ "  `last_name` VARCHAR(45) NULL,"
					+ "  `phone_no` VARCHAR(45) NULL,"
					+ "  `address` VARCHAR(45) NULL,"
					+ "  PRIMARY KEY (`id`));";
			statement.executeUpdate(createTableQuery);

			// INSERT Data in Table
//			PreparedStatement preparedStatement = connection.prepareStatement(
//					"INSERT INTO seminar.sample(id,first_name,last_name, phone_no,address) values (2,?,?,?,?)"
//			);
//			preparedStatement.setString(1, "Test");
//			preparedStatement.setString(2, "Test");
//			preparedStatement.setString(3, "Test");
//			preparedStatement.setString(4, "Test");
//			preparedStatement.executeUpdate();

			//To show data from database table
			showData(statement);
			
			
			// DELETE Operation
//			PreparedStatement preparedStatement = connection
//					.prepareStatement("DELETE FROM SEMINAR.SAMPLE WHERE id = 2");
//			preparedStatement.executeUpdate();			
			
			//Update Operation
			PreparedStatement preparedStatement = connection
					.prepareStatement("UPDATE seminar.sample SET phone_no = '9191919191' WHERE first_name = 'Tejas'"); 
			preparedStatement.executeUpdate();
			showData(statement);
			
			//DROP Operation 
//			PreparedStatement preparedStatement2 = connection
//					.prepareStatement("DROP TABLE sample"); 
//			preparedStatement2.executeUpdate();
			
			showData(statement);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	static void showData(Statement statement) throws SQLException {
		// Running Select Query & storing all data in ResultSet
		ResultSet resultSet = statement.executeQuery("SELECT * FROM seminar.sample");
//		// Iterating all resultset objects & showing them in output
		while (resultSet.next()) {
			String firstName = resultSet.getString("first_name");
			String lastName = resultSet.getString("last_name");
			String phoneNumber = resultSet.getString("phone_no");
			String address = resultSet.getString("address");
			System.out.println("Name:" + firstName + " " + lastName);
			System.out.println("Phone Number:" + phoneNumber);
			System.out.println("Address:" + address);
			System.out.println("\n");
		}
	}

}
