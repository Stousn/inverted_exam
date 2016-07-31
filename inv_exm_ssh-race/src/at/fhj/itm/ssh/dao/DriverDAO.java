package at.fhj.itm.ssh.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import at.fhj.itm.ssh.model.Driver;
import at.fhj.itm.ssh.utils.SqlUtils;

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
	
	
	public Driver getHeaviestDriverRIGHT() {
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
			//stmt = connection.prepareStatement("SELECT * FROM DRIVER WHERE  WEIGHT = (SELECT MAX(WEIGHT) FROM DRIVER)");
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
	
	public Driver getHeaviestDriverWRONG(){
		
		int max = SqlUtils.getMaxInt("DRIVER", "ID");
		Driver heaviestDriver = this.read(1);
		//Compare every Driver
		for(int i=2; i<=max;i++){
			Driver d = new Driver();
			Driver d2 = new Driver();
			
			d.id = this.read(i).id;
			d.fName = this.read(i).fName;
			d.lName = this.read(i).lName;
			d.dob = this.read(i).dob;
			d.weightInKg = this.read(i).weightInKg;
			d.team = this.read(i).team;
			
			d2.id = this.read(i-1).id;
			d2.fName = this.read(i-1).fName;
			d2.lName = this.read(i-1).lName;
			d2.dob = this.read(i-1).dob;
			d2.weightInKg = this.read(i-1).weightInKg;
			d2.team = this.read(i).team;
			
			if(d.weightInKg>d2.weightInKg){
				if(d.weightInKg>heaviestDriver.weightInKg){
					heaviestDriver = d;
				}	
			}
		}
		
		return heaviestDriver;
	}
	
	public Driver getLightestDriverRight() {
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
			stmt = connection.prepareStatement("SELECT * FROM DRIVER WHERE  WEIGHT <= (SELECT MIN(WEIGHT) FROM DRIVER)");
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
			System.err.println("Could not find lightest Driver");
		}     
		
		return d;
	}
	
	public List<Driver> getDriverFromCountryWRONG(String countryName){
		PreparedStatement stmt;
		List<Driver> driversFromCountry = new ArrayList<>();
		try{
			stmt = connection.prepareStatement("SELECT * FROM DRIVER CROSS JOIN COUNTRY");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				if(rs.getInt(6) == rs.getInt(8) && rs.getString("NAME").equals(countryName)){
					Driver d = new Driver(rs.getInt(1), rs.getInt(5), rs.getDate("DOB").toLocalDate(), rs.getString("FORENAME"), rs.getString("LASTNAME"), rs.getInt("FK_COUNTRY_ID"), rs.getInt("FK_TEAM_ID"));
					driversFromCountry.add(d);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Could not get Driver from " + countryName);
		}
		
		return driversFromCountry;
	}
	
	public List<Driver> getDriverFromCountryRIGHT(String countryName){
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
