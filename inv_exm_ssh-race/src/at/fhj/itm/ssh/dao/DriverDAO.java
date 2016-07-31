package at.fhj.itm.ssh.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import at.fhj.itm.ssh.model.Driver;

public class DriverDAO extends GenericSqlDAO<Driver, Integer> {

	@Override
	public Integer create(Driver newInstance) {
		PreparedStatement stmt;
		try 
		{
			stmt = connection.prepareStatement("INSERT INTO DRIVER (FORENAME, LASTNAME, DOB, WEIGHT, FK_Country_ID, FK_Team_ID) VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1,newInstance.fName);
			stmt.setString(2,newInstance.lName);
			stmt.setDate(3,Date.valueOf(newInstance.dob));
			stmt.setInt(4,newInstance.weightInKg);
			stmt.setInt(5,newInstance.country);
			stmt.setInt(6,newInstance.team);
	        stmt.executeUpdate();
	        ResultSet rs = stmt.getGeneratedKeys();
	        rs.next();
	        newInstance.id = rs.getInt(1);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.err.println("Driver could not be created");
		}     
		
		return newInstance.id;
	}

	@Override
	public Driver read(Integer id) {
		PreparedStatement stmt;
		Driver d = new Driver();
		
		d.id = -1;
		d.weightInKg = -1;
		d.dob = null;
		d.fName = "Unbekannt";
		d.lName = "Unbekannt";
		d.team = 0;
		d.country = 0;
		
		try 
		{
			stmt = connection.prepareStatement("SELECT * FROM DRIVER WHERE ID = ?");
			stmt.setInt(1, id);
	        ResultSet rs = stmt.executeQuery();
	        rs.first();
	        
	        // Mapping
	        d.id = rs.getInt("ID");
			d.weightInKg = rs.getInt("WEIGHT");
			d.dob = rs.getDate("DOB").toLocalDate();
			d.fName = rs.getString("FORENAME");
			d.lName = rs.getString("LASTNAME");
			d.team = rs.getInt("FK_Team_ID");
			d.country = rs.getInt("FK_Country_ID");
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.err.println("Could not read Driver (" + id +")");
		}     
		
		return d;
	}

	@Override
	public void update(Driver transientObject) {
		PreparedStatement stmt;
		try 
		{
			stmt = connection.prepareStatement("UPDATE DRIVER SET FORENAME = ?, LASTNAME = ?, DOB = ?, WEIGHT = ?, FK_Country_ID = ?, FK_Team_ID = ? where ID = ?");
			stmt.setString(1,transientObject.fName);
			stmt.setString(2,transientObject.lName);
			stmt.setDate(3,Date.valueOf(transientObject.dob));
			stmt.setInt(4,transientObject.weightInKg);
			stmt.setInt(5,transientObject.country);
			stmt.setInt(6,transientObject.team);

	        int affectedRows = stmt.executeUpdate();
	        
	        if(affectedRows != 1)
	        {
	        	System.out.println("Something strange is going on: Driver not found or Driver not unique: " + transientObject);
	        }	 
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.err.println("Driver could not be upgedated");
		} 
		
	}

	@Override
	public void delete(Driver persistentObject) {
		PreparedStatement stmt;
		try 
		{
			stmt = connection.prepareStatement("DELETE FROM DRIVER WHERE ID = ?");
			stmt.setInt(1, persistentObject.id);
	        int affectedRows = stmt.executeUpdate();
	        
	        if(affectedRows != 1)
	        {
	        	System.out.println("Something strange is going on: Driver not found or Driver not unique: " + persistentObject);
	        }	 
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.err.println("Driver could not be found");
		}
		
	}
	
	public List<Driver> readAll()
	{
		List<Driver> driverList = new ArrayList<Driver>();
		
		PreparedStatement stmt;
		
		try 
		{
			stmt = connection.prepareStatement("SELECT * FROM DRIVER D");
			ResultSet rs = stmt.executeQuery();
	        
	        while(rs.next())
	        {
	        	// generation of a driver object for each element
	        	Driver d = new Driver();
				d.id = rs.getInt("ID");
				d.weightInKg = rs.getInt("WEIGHT");
				d.dob = rs.getDate("DOB").toLocalDate();
				d.fName = rs.getString("FORENAME");
				d.lName = rs.getString("LASTNAME");
				d.team = rs.getInt("FK_Team_ID");
				d.country = rs.getInt("FK_Country_ID");
				
	        	driverList.add(d);
	        	
	        }
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.err.println("An error occured reading Drivers from Database");
		}     
		
		return driverList;
	}
	
	
	public Driver getHeaviestDriver() {
		PreparedStatement stmt;
		Driver d = new Driver();
		
		d.id = -1;
		d.weightInKg = -1;
		d.dob = null;
		d.fName = "Unbekannt";
		d.lName = "Unbekannt";
		d.team = 0;
		d.country = 0;
		
		try 
		{
			stmt = connection.prepareStatement("SELECT * FROM DRIVER ORDER BY WEIGHT DESC LIMIT 1");
	        ResultSet rs = stmt.executeQuery();
	        rs.first();
	        
	     // Mapping
	        d.id = rs.getInt("ID");
			d.weightInKg = rs.getInt("WEIGHT");
			d.dob = rs.getDate("DOB").toLocalDate();
			d.fName = rs.getString("FORENAME");
			d.lName = rs.getString("LASTNAME");
			d.team = rs.getInt("FK_Team_ID");
			d.country = rs.getInt("FK_Country_ID");
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.err.println("Could not find heaviest Driver");
		}     
		
		return d;
	}
	
	public List<Driver> getDriversFromCountry(String countryName){
		PreparedStatement stmt;
		List<Driver> driversFromCountry = new ArrayList<>();
		try{
			stmt = connection.prepareStatement("SELECT * FROM DRIVER D JOIN COUNTRY C ON D.FK_COUNTRY_ID = C.ID WHERE C.NAME =  ?");
			stmt.setString(1, countryName);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				Driver d = new Driver(rs.getInt(1), rs.getInt(5), rs.getDate("DOB").toLocalDate(), rs.getString("FORENAME"), rs.getString("LASTNAME"), rs.getInt("FK_COUNTRY_ID"), rs.getInt("FK_TEAM_ID"));
				driversFromCountry.add(d);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Could not get Driver from " + countryName);
		}
		
		return driversFromCountry;
	}
	
	
	
}
