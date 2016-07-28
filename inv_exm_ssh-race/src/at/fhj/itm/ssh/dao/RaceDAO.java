package at.fhj.itm.ssh.dao;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import at.fhj.itm.ssh.model.Race;

public class RaceDAO extends GenericSqlDAO<Race, Integer> {

	@Override
	public Integer create(Race newInstance) {
		PreparedStatement stmt;
		try 
		{
			stmt = connection.prepareStatement("INSERT INTO RACE (LAPS, START_TIME, DURATION, R_DATE, FK_Track_ID) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1,newInstance.lapCount);
			stmt.setTime(2, Time.valueOf(newInstance.startTime));
			stmt.setTime(3, Time.valueOf(newInstance.duration));
			stmt.setDate(4, Date.valueOf(newInstance.date));
			stmt.setInt(5, newInstance.trackId);
	        stmt.executeUpdate();
	        ResultSet rs = stmt.getGeneratedKeys();
	        rs.next();
	        newInstance.id = rs.getInt(1);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.err.println("Race could not be created");
		}     
		
		return newInstance.id;
	}

	@Override
	public Race read(Integer id) {
		PreparedStatement stmt;
		Race r = new Race();
		
		r.id = -1;
		r.lapCount = -1;
		r.trackId = -1;
		r.startTime = LocalTime.parse("00:00:00");
		r.duration = LocalTime.parse("00:00:00");
		r.date = LocalDate.parse("1.1.1900");
		
		try 
		{
			stmt = connection.prepareStatement("SELECT * FROM RACE WHERE ID = ?");
			stmt.setInt(1, id);
	        ResultSet rs = stmt.executeQuery();
	        rs.first();
	        
	        // Mapping
	        r.id = rs.getInt("ID");
			r.lapCount = rs.getInt("LAPS");
			r.trackId = rs.getInt("FK_Track_ID");
			r.startTime = rs.getTime("START_TIME").toLocalTime();
			r.duration = rs.getTime("DURATION").toLocalTime();
			r.date = rs.getDate("R_DATE").toLocalDate();
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
			stmt.setTime(2, Time.valueOf(transientObject.startTime));
			stmt.setTime(3, Time.valueOf(transientObject.duration));
			stmt.setDate(4, Date.valueOf(transientObject.date));
			stmt.setInt(5, transientObject.trackId);
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
	        	// generation of a race object for each element
	        	Race r = new Race();
	        	r.id = rs.getInt("ID");
	   			r.lapCount = rs.getInt("LAPS");
	   			r.trackId = rs.getInt("FK_Track_ID");
	   			r.startTime = rs.getTime("START_TIME").toLocalTime();
	   			r.duration = rs.getTime("DURATION").toLocalTime();
	   			r.date = rs.getDate("R_DATE").toLocalDate(); 
	        	
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
