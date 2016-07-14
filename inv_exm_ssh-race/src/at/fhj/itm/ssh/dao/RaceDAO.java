package at.fhj.itm.ssh.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import at.fhj.itm.ssh.db.SqlUtils;
import at.fhj.itm.ssh.model.Race;

public class RaceDAO extends GenericSqlDAO<Race, Integer> {

	@Override
	public Integer create(Race newInstance) {
		PreparedStatement stmt;
		try 
		{
			stmt = connection.prepareStatement("INSERT INTO RACE (LAPS, START_TIME, DURATION, R_DATE, FK_Track_ID) VALUES (?, ?, ?, ?, ?)");
			stmt.setInt(1,newInstance.lapCount);
			stmt.setString(2,newInstance.startTime);
			stmt.setString(3,newInstance.duration);
			stmt.setDate(4,newInstance.date);
			stmt.setInt(5, newInstance.track);
	        stmt.executeUpdate();   
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.err.println("Race could not be created");
		}     
		
		newInstance.id = SqlUtils.getMaxInt("RACE", "ID");
		return newInstance.id;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Race read(Integer id) {
		PreparedStatement stmt;
		Race r = new Race();
		
		r.id = -1;
		r.lapCount = -1;
		r.track = -1;
		r.startTime = "Keine Startzeit";
		r.duration = "0 Runden";
		r.date = new Date(1800, 0, 1);
		
		try 
		{
			stmt = connection.prepareStatement("SELECT * FROM RACE WHERE ID = ?");
			stmt.setInt(1, id);
	        ResultSet rs = stmt.executeQuery();
	        rs.first();
	        
	        // Mapping
	        r.id = rs.getInt("ID");
			r.lapCount = rs.getInt("LAPS");
			r.track = rs.getInt("FK_Track_ID");
			r.startTime = rs.getString("START_TIME");
			r.duration = rs.getString("DURATION");
			r.date = rs.getDate("R_DATE");
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.err.println("Could not read Race (" + id +")");
		}     
		
		return r;
	}

	@Override
	public void update(Race transientObject) {
		PreparedStatement stmt;
		try 
		{
			stmt = connection.prepareStatement("UPDATE RACE SET LAPS = ?, START_TIME = ?, DURATION = ?, R_DATE = ?, FK_Track_ID = ? where ID = ?");
			stmt.setInt(1,transientObject.lapCount);
			stmt.setString(2,transientObject.startTime);
			stmt.setString(3,transientObject.duration);
			stmt.setDate(4,transientObject.date);
			stmt.setInt(5, transientObject.track);
			stmt.setInt(6, transientObject.id);

	        int affectedRows = stmt.executeUpdate();
	        
	        if(affectedRows != 1)
	        {
	        	System.out.println("Something strange is going on: Race not found or Race not unique: " + transientObject);
	        }	 
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.err.println("Race could not be upgedated");
		}
		
	}

	@Override
	public void delete(Race persistentObject) {
		PreparedStatement stmt;
		try 
		{
			stmt = connection.prepareStatement("DELETE FROM RACE WHERE ID = ?");
			stmt.setInt(1, persistentObject.id);
	        int affectedRows = stmt.executeUpdate();
	        
	        if(affectedRows != 1)
	        {
	        	System.out.println("Something strange is going on: Race not found or Race not unique: " + persistentObject);
	        }	 
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.err.println("Race could not be found");
		}   
		
	}
	
	public List<Race> readAll()
	{
		List<Race> raceList = new ArrayList<Race>();
		
		PreparedStatement stmt;
		
		try 
		{
			stmt = connection.prepareStatement("SELECT * FROM RACE R");
			ResultSet rs = stmt.executeQuery();
	        
	        while(rs.next())
	        {
	        	// generation of a brand object for each element
	        	Race r = new Race();
	        	r.id = rs.getInt("ID");
	   			r.lapCount = rs.getInt("LAPS");
	   			r.track = rs.getInt("FK_Track_ID");
	   			r.startTime = rs.getString("START_TIME");
	   			r.duration = rs.getString("DURATION");
	   			r.date = rs.getDate("R_DATE"); 
	        	
	        	raceList.add(r);
	        	
	        }
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.err.println("An error occured reading Races from Database");
		}     
		
		return raceList;
	}

}
