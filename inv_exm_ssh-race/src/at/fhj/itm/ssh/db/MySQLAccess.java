package at.fhj.itm.ssh.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLAccess {
  private Connection connect = null;
  private Statement statement = null;
  private PreparedStatement preparedStatement = null;
  private ResultSet resultSet = null;

  public void readDataBase() throws Exception {
    try {
      // This will load the MySQL driver, each DB has its own driver
      Class.forName("com.mysql.jdbc.Driver");
      // Setup the connection with the DB
      connect = DriverManager
          .getConnection("jdbc:mysql://localhost:3306/inverted_exam?"
              + "user=root");
      //&password=xyz

      // Statements allow to issue SQL queries to the database
      statement = connect.createStatement();
      // Result set get the result of the SQL query
      resultSet = statement
          .executeQuery("select * from test");
      writeResultSet(resultSet);

     
    } catch (Exception e) {
      throw e;
    } finally {
      close();
    }

  }

  private void writeResultSet(ResultSet resultSet) throws SQLException {
    // ResultSet is initially before the first data set
    while (resultSet.next()) {
      // It is possible to get the columns via name
      // also possible to get the columns via the column number
      // which starts at 1
      // e.g. resultSet.getSTring(2);
      int i1 = resultSet.getInt("a");
      int i2 = resultSet.getInt("b");
      int i3 = resultSet.getInt("c");
      int i4 = resultSet.getInt("d");
      int i5 = resultSet.getInt("e");
      System.out.println("i1: " + i1);
      System.out.println("i2: " + i2);
      System.out.println("i3: " + i3);
      System.out.println("i4: " + i4);
      System.out.println("i5: " + i5);
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