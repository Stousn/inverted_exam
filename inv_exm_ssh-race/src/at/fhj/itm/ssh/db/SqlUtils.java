package at.fhj.itm.ssh.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

/**
 * @author Günther Hutter
 *
 */
public class SqlUtils 
{
	/**
	 * Deliveres the maximum Integer of a column of a table back.
	 * @param tablename
	 * @param columnname
	 * @return int maximum id of a table
	 */
	public static int getMaxInt(String tablename, String columnname)
	{
		int ret = -1;
		
		try {
			Connection c = MySQLAccess.getConnection();
			PreparedStatement s = c.prepareStatement("SELECT MAX(" + columnname + ") FROM " + tablename);	
			
			ResultSet rs = s.executeQuery();
			rs.first();
			
			ret = rs.getInt(1);
			
			rs.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.err.println("Max(" + columnname + ") of " + tablename + " could not be calculated");
		}
		
		return ret;
	}
	
	/**
	 * Erzeugt aus einem ResultSet ein TableModel
	 * Generates a TableModel from a ResultSet
	 * 
	 * @see http://stackoverflow.com/questions/10620448/most-simple-code-to-populate-jtable-from-resultset
	 * 
	 * @author Günther Hutter
	 * 
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	public static DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {

	    ResultSetMetaData metaData = rs.getMetaData();

	    // names of columns
	    Vector<String> columnNames = new Vector<String>();
	    int columnCount = metaData.getColumnCount();
	    for (int column = 1; column <= columnCount; column++) {
	        columnNames.add(metaData.getColumnName(column));
	    }

	    // data of the table
	    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	    while (rs.next()) {
	        Vector<Object> vector = new Vector<Object>();
	        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
	            vector.add(rs.getObject(columnIndex));
	        }
	        data.add(vector);
	    }

	    return new DefaultTableModel(data, columnNames);

	}
	
}
