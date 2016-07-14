package at.fhj.itm.ssh.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import at.fhj.itm.ssh.db.SqlUtils;
import at.fhj.itm.ssh.model.City;

public class CityDAO extends GenericSqlDAO<City, Integer> {

	@Override
	public Integer create(City newInstance) {
		PreparedStatement stmt;
		try 
		{
			stmt = connection.prepareStatement("INSERT INTO CITY (CODE, NAME, COUNTRY) VALUES (?, ?, ?)");
			stmt.setString(1,newInstance.code);
			stmt.setString(2,newInstance.name);
			stmt.setInt(3,newInstance.country);
	        stmt.executeUpdate();   
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.err.println("City could not be created");
		}     
		
		newInstance.id = SqlUtils.getMaxInt("CITY", "ID");
		return newInstance.id;
	}

	@Override
	public City read(Integer id) {
		PreparedStatement stmt;
		City c = new City();
		
		c.id = -1;
		c.code = "unknown code";
		c.name = "unknown name";
		c.country = 0;
		
		try 
		{
			stmt = connection.prepareStatement("SELECT * FROM CITY WHERE ID = ?");
			stmt.setInt(1, id);
	        ResultSet rs = stmt.executeQuery();
	        rs.first();
	        
	        // Mapping
	        c.id = rs.getInt("ID");
			c.code = rs.getString("CODE");
			c.name = rs.getString("NAME"); 
			c.country = rs.getInt("FK_Country_ID");
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.err.println("Could not read City (" + id +")");
		}     
		
		return c;
	}

	@Override
	public void update(City transientObject) {
		PreparedStatement stmt;
		try 
		{
			stmt = connection.prepareStatement("UPDATE CITY SET CODE = ?, NAME = ?, FK_Country_ID = ? where ID = ?");
			stmt.setString(1, transientObject.code);
			stmt.setString(2, transientObject.name);
			stmt.setInt(3, transientObject.country);
			stmt.setInt(4, transientObject.id);

	        int affectedRows = stmt.executeUpdate();
	        
	        if(affectedRows != 1)
	        {
	        	System.out.println("Something strange is going on: City not found or City not unique: " + transientObject);
	        }	 
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.err.println("City could not be upgedated");
		}     
		
	}

	@Override
	public void delete(City persistentObject) {
		PreparedStatement stmt;
		try 
		{
			stmt = connection.prepareStatement("DELETE FROM CITY WHERE ID = ?");
			stmt.setInt(1, persistentObject.id);
	        int affectedRows = stmt.executeUpdate();
	        
	        if(affectedRows != 1)
	        {
	        	System.out.println("Something strange is going on: City not found or City not unique: " + persistentObject);
	        }	 
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.err.println("City could not be found");
		}   
		
	}

	public List<City> readAll()
	{
		List<City> cityList = new ArrayList<City>();
		
		PreparedStatement stmt;
		
		try 
		{
			stmt = connection.prepareStatement("SELECT * FROM CITY C");
			ResultSet rs = stmt.executeQuery();
	        
	        while(rs.next())
	        {
	        	// generation of a city object for each element
	        	City c = new City();
		        c.id = rs.getInt("ID");
				c.code = rs.getString("CODE");
				c.name = rs.getString("NAME"); 
				c.country = rs.getInt("FK_Country_ID"); 
	        	
	        	cityList.add(c);
	        	
	        }
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.err.println("An error occured reading Cities from Database");
		}     
		
		return cityList;
	}
}
