package at.fhj.itm.ssh.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.DriverManager;
import java.sql.*;
import java.util.Properties;


public class ConnectionFactory {

	private static final String DB_PROPFILE = "db.properties";

	private static String db_username = "";
	private static String db_password = "";
	private static String jdbc_driver = "";
	private static String jdbc_url = "";
	private static String app_initscript = "";

	static {
		Properties props = new Properties();
		try(InputStream in = ConnectionFactory.class.getClassLoader().getResourceAsStream(DB_PROPFILE)){
			props.load(in);

			//Reads the properties
			db_username = props.getProperty("db.user");
			db_password = props.getProperty("db.password");
			jdbc_driver = props.getProperty("jdbc.driver");
			jdbc_url = props.getProperty("jdbc.url");
			app_initscript = props.getProperty("application.initscript");

		} catch(IOException e){
			e.printStackTrace();
			System.err.println(DB_PROPFILE + " not found");
			System.exit(1);
		}

		//Test if DB-Driver exists
		try {
			Class.forName(jdbc_driver);
			System.out.println("Driver found");
		} catch (ClassNotFoundException e){
			e.printStackTrace();
			System.err.println("jdbc.driver can't be loaded");
			System.exit(3);
		}

	}

	public static Connection getConnection(){
		Connection theConnection = null;

		try {
			theConnection = DriverManager.getConnection(jdbc_url, db_username, db_password);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return theConnection;
	}
	
	public static void runInitScript(){
		try(InputStream in = ConnectionFactory.class.getClassLoader().getResourceAsStream(app_initscript)){
			ScriptRunner s = new ScriptRunner(ConnectionFactory.getConnection(), true, true);
			s.runScript(new InputStreamReader(in));
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("application.initscript not found");
			System.exit(3);
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("application.initscript caused an error");
			System.exit(4);
		}
	}
	
	public static void runScriptsFromScriptFolder(){
		
		try {
			//get all files from the scripts folder
			Files.walk(Paths.get("./scripts"))
			// only use files
			 .filter(Files::isRegularFile)
			 .forEach(file -> {
				 // get filename
				 Path p = file.getFileName();
				 String name = p.toString();
				 // if extension == sql AND it's not the app_initscript
				 if(name.substring(name.lastIndexOf('.')+1).equals("sql") && !name.equals(app_initscript)){
					 System.out.println("Run: " + name);
					 // read the file and execute it
					 try(InputStream in = new FileInputStream(new File("./scripts/" + name))){
							ScriptRunner s = new ScriptRunner(ConnectionFactory.getConnection(), true, true);
							s.runScript(new InputStreamReader(in));
						} catch (IOException e) {
							e.printStackTrace();
							System.err.println("Files in 'scripts' folder not found");
							System.exit(3);
						} catch (SQLException e) {
							e.printStackTrace();
							System.err.println("runScriptsFromScriptFolder() caused an error");
							System.exit(4);
						}
				 }
			 });
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Can't get all files from 'script' folder");
		}
		
	}


}
