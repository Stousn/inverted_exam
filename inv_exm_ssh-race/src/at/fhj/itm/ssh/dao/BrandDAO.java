package at.fhj.itm.ssh.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import at.fhj.itm.ssh.model.Brand;

public class BrandDAO extends GenericSqlDAO<Brand, Integer> {

	@Override
	public Integer create(Brand newInstance) {
		PreparedStatement stmt;
		try 
		{
			stmt = connection.prepareStatement("INSERT INTO BRAND (CODE, NAME) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
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
			System.err.println("Brand could not be created");
		}     
		
		return newInstance.id;
	}

	@Override
	public Brand read(Integer id) {
		PreparedStatement stmt;
		Brand b = new Brand();
		
		b.id = -1;
		b.code = "unknown code";
		b.name = "unknown name";
		
		try 
		{
			stmt = connection.prepareStatement("SELECT * FROM BRAND WHERE ID = ?");
			stmt.setInt(1, id);
	        ResultSet rs = stmt.executeQuery();
	        rs.first();
	        
	        // Mapping
	        b.id = rs.getInt("ID");
			b.code = rs.getString("CODE");
			b.name = rs.getString("NAME"); 
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.err.println("Could not read Brand (" + id +")");
		}     
		
		return b;
	}

	@Override
	public void update(Brand transientObject) {
		PreparedStatement stmt;
		try 
		{
			stmt = connection.prepareStatement("UPDATE BRAND SET CODE = ?, NAME = ? where ID = ?");
			stmt.setString(1, transientObject.code);
			stmt.setString(2, transientObject.name);
			stmt.setInt(3, transientObject.id);

	        int affectedRows = stmt.executeUpdate();
	        
	        if(affectedRows != 1)
	        {
	        	System.out.println("Something strange is going on: Brand not found or Brand not unique: " + transientObject);
	        }	 
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.err.println("Brand could not be upgedated");
		}     
		
	}

	@Override
	public void delete(Brand persistentObject) {
		PreparedStatement stmt;
		try 
		{
			stmt = connection.prepareStatement("DELETE FROM BRAND WHERE ID = ?");
			stmt.setInt(1, persistentObject.id);
	        int affectedRows = stmt.executeUpdate();
	        
	        if(affectedRows != 1)
	        {
	        	System.out.println("Something strange is going on: Brand not found or Brand not unique: " + persistentObject);
	        }	 
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.err.println("Brand could not be found");
		}   
		
	}

	public List<Brand> readAll()
	{
		List<Brand> brandList = new ArrayList<Brand>();
		
		PreparedStatement stmt;
		
		try 
		{
			stmt = connection.prepareStatement("SELECT * FROM BRAND B");
			ResultSet rs = stmt.executeQuery();
	        
	        while(rs.next())
	        {
	        	// generation of a brand object for each element
	        	Brand b = new Brand();
		        b.id = rs.getInt("ID");
				b.code = rs.getString("CODE");
				b.name = rs.getString("NAME"); 
	        	
	        	brandList.add(b);
	        	
	        }
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.err.println("An error occured reading Brands from Database");
		}     
		
		return brandList;
	}
	
}
