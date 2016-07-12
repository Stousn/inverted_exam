package at.fhj.itm.ssh.db;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class MySQLAccess {
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	private static final String DB_PROPFILE = "db.properties";

	private static String db_username 		= "";
	private static String db_password 		= "";
	private static String db_url			= "";
	private static String db_database 		= "";

	public static void setProperties() {
		Properties props = new Properties();
		try(InputStream in = MySQLAccess.class.getClassLoader().getResourceAsStream(DB_PROPFILE))
		{
			props.load(in);
			in.close();

			db_username = props.getProperty("db.user");
			db_password = props.getProperty("db.password");
			db_url = props.getProperty("jdbc.url");
			db_database = props.getProperty("db.database");
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			System.err.println(DB_PROPFILE + " nicht gefunden");
			System.exit(1);
		}
	}

	public MySQLAccess() {
		setProperties();
		try {
			//DEBUG INFO: db.properties: 
//			System.out.println("DB.URL: "+db_url);
//			System.out.println("DB.DB: "+db_database);
//			System.out.println("DB.USER: "+db_username);
//			System.out.println("DB.PW: "+db_password);
			connect = DriverManager
					.getConnection(db_url+db_database+"?user="+db_username+"&password="+db_password);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void init() throws Exception {
		try {
			ScriptRunner runner = new ScriptRunner(connect, false, true);
			runner.runScript(new BufferedReader(new FileReader("test.sql")));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		return this.connect;
	}
	
	

	public void readDataBase() throws Exception {
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			//      connect = DriverManager
			//          .getConnection("jdbc:mysql://localhost:3306/inverted_exam?"
			//              + "user=root");
			//&password=xyz

			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();
			// Result set get the result of the SQL query
			resultSet = statement
					.executeQuery("select * from BRAND");
			writeResultSet(resultSet);


		} catch (Exception e) {
			throw e;
//		} finally {
//			close();
		}

	}

	private void writeResultSet(ResultSet resultSet) throws SQLException {
		// ResultSet is initially before the first data set
		while (resultSet.next()) {
			// It is possible to get the columns via name
			// also possible to get the columns via the column number
			// which starts at 1
			// e.g. resultSet.getSTring(2);
			int i1 = resultSet.getInt("ID");
			String i2 = resultSet.getString("CODE");
			String i3 = resultSet.getString("NAME");
//			int i4 = resultSet.getInt("d");
//			int i5 = resultSet.getInt("e");
			System.out.println("i1: " + i1);
			System.out.println("i2: " + i2);
			System.out.println("i3: " + i3);
//			System.out.println("i4: " + i4);
//			System.out.println("i5: " + i5);
		}
	}

	// You need to close the resultSet
	private void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}

			if (statement != null) {
				statement.close();
			}

			if (connect != null) {
				connect.close();
			}
		} catch (Exception e) {

		}
	}

} 