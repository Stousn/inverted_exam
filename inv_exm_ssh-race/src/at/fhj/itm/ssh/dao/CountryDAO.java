package at.fhj.itm.ssh.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import at.fhj.itm.ssh.model.Country;

public class CountryDAO extends GenericSqlDAO<Country, Integer> {

	@Override
	public Integer create(Country newInstance) {
		PreparedStatement stmt;
		try 
		{
			stmt = connection.prepareStatement("INSERT INTO COUNTRY (CODE, NAME) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1,newInstance.code);
			stmt.setString(2,newInstance.name);
	        stmt.executeUpdate();
	        ResultSet rs = stmt.getGeneratedKeys();
	        rs.next();
	        newInstance.id = rs.getInt(1);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.err.println("Country could not be created");
		}     
		
		return newInstance.id;
	}

	@Override
	public Country read(Integer id) {
		PreparedStatement stmt;
		Country c = new Country();
		
		c.id = -1;
		c.code = "unknown code";
		c.name = "unknown name";
		
		try 
		{
			stmt = connection.prepareStatement("SELECT * FROM COUNTRY WHERE ID = ?");
			stmt.setInt(1, id);
	        ResultSet rs = stmt.executeQuery();
	        rs.first();
	        
	        // Mapping
	        c.id = rs.getInt("ID");
			c.code = rs.getString("CODE");
			c.name = rs.getString("NAME"); 
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.err.println("Could not read Country (" + id +")");
		}     
		
		return c;
	}

	@Override
	public void update(Country transientObject) {
		PreparedStatement stmt;
		try 
		{
			stmt = connection.prepareStatement("UPDATE COUNTRY SET CODE = ?, NAME = ? where ID = ?");
			stmt.setString(1, transientObject.code);
			stmt.setString(2, transientObject.name);
			stmt.setInt(3, transientObject.id);

	        int affectedRows = stmt.executeUpdate();
	        
	        if(affectedRows != 1)
	        {
	        	System.out.println("Something strange is going on: Country not found or Country not unique: " + transientObject);
	        }	 
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.err.println("Country could not be upgedated");
		}
	}

	@Override
	public void delete(Country persistentObject) {
		PreparedStatement stmt;
		try 
		{
			stmt = connection.prepareStatement("DELETE FROM COUNTRY WHERE ID = ?");
			stmt.setInt(1, persistentObject.id);
	        int affectedRows = stmt.executeUpdate();
	        
	        if(affectedRows != 1)
	        {
	        	System.out.println("Something strange is going on: Country not found or Country not unique: " + persistentObject);
	        }	 
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.err.println("Country could not be found");
		}
		
	}
	
	public List<Country> readAll()
	{
		List<Country> countryList = new ArrayList<Country>();
		
		PreparedStatement stmt;
		
		try 
		{
			stmt = connection.prepareStatement("SELECT * FROM COUNTRY C");
			ResultSet rs = stmt.executeQuery();
	        
	        while(rs.next())
	        {
	        	// generation of a country object for each element
	        	Country c = new Country();
		        c.id = rs.getInt("ID");
				c.code = rs.getString("CODE");
				c.name = rs.getString("NAME"); 
	        	
	        	countryList.add(c);
	        	
	        }
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.err.println("An error occured reading Countries from Database");
		}     
		
		return countryList;
	}

}
