package at.fhj.itm.ssh.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import at.fhj.itm.ssh.model.Track;

public class TrackDAO extends GenericSqlDAO<Track, Integer> {

	@Override
	public Integer create(Track newInstance) {
		PreparedStatement stmt;
		try 
		{
			stmt = connection.prepareStatement("INSERT INTO TRACK (NAME, LENGTH, FASTEST_LAP, SEATS, FK_City_ID) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1,newInstance.name);
			stmt.setInt(2,newInstance.lenghtInM);
			stmt.setTimestamp(3, Timestamp.valueOf(newInstance.fastestLap));
			stmt.setInt(4, newInstance.seatCount);
			stmt.setInt(5, newInstance.city);
	        stmt.executeUpdate(); 
	        ResultSet rs = stmt.getGeneratedKeys();
	        rs.next();
	        newInstance.id = rs.getInt(1);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.err.println("Track could not be created");
		}     
		
		return newInstance.id;
	}

	@Override
	public Track read(Integer id) {
		PreparedStatement stmt;
		Track t = new Track();
		
		t.id = -1;		
		t.name = "unknown name";
		t.lenghtInM = 0;
		t.fastestLap = new Timestamp(0).toLocalDateTime();
		t.seatCount = -1;
		t.city = -1;
		
		try 
		{
			stmt = connection.prepareStatement("SELECT * FROM TRACK WHERE ID = ?");
			stmt.setInt(1, id);
	        ResultSet rs = stmt.executeQuery();
	        rs.first();
	        
	        // Mapping
	        t.id = rs.getInt("ID");
	        t.name = rs.getString("NAME");
			t.lenghtInM = rs.getInt("LENGTH");
			t.fastestLap = rs.getTimestamp("FASTEST_LAP").toLocalDateTime();
			t.seatCount = rs.getInt("SEATS");
			t.city =  rs.getInt("FK_City_ID");
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.err.println("Could not read Track (" + id +")");
		}     
		
		return t;
	}

	@Override
	public void update(Track transientObject) {
		PreparedStatement stmt;
		try 
		{
			stmt = connection.prepareStatement("UPDATE TRACK SET NAME = ?, LENGTH = ?, FASTEST_LAP = ?, SEATS = ?, FK_City_ID = ? where ID = ?");
			stmt.setString(1,transientObject.name);
			stmt.setInt(2,transientObject.lenghtInM);
			stmt.setTimestamp(3,Timestamp.valueOf(transientObject.fastestLap));
			stmt.setInt(4,transientObject.seatCount);
			stmt.setInt(5,transientObject.city);
			stmt.setInt(6,transientObject.id);

	        int affectedRows = stmt.executeUpdate();
	        
	        if(affectedRows != 1)
	        {
	        	System.out.println("Something strange is going on: Track not found or Track not unique: " + transientObject);
	        }	 
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.err.println("Track could not be upgedated");
		}
		
	}

	@Override
	public void delete(Track persistentObject) {
		PreparedStatement stmt;
		try 
		{
			stmt = connection.prepareStatement("DELETE FROM TRACK WHERE ID = ?");
			stmt.setInt(1, persistentObject.id);
	        int affectedRows = stmt.executeUpdate();
	        
	        if(affectedRows != 1)
	        {
	        	System.out.println("Something strange is going on: Track not found or Track not unique: " + persistentObject);
	        }	 
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.err.println("Track could not be found");
		}   
		
	}
	public List<Track> readAll()
	{
		List<Track> trackList = new ArrayList<Track>();
		
		PreparedStatement stmt;
		
		try 
		{
			stmt = connection.prepareStatement("SELECT * FROM TRACK T");
			ResultSet rs = stmt.executeQuery();
	        
	        while(rs.next())
	        {
	        	// generation of a track object for each element
	        	Track t = new Track();
	        	t.id = rs.getInt("ID");
		        t.name = rs.getString("NAME");
				t.lenghtInM = rs.getInt("LENGTH");
				t.fastestLap = rs.getTimestamp("FASTEST_LAP").toLocalDateTime();
				t.seatCount = rs.getInt("SEATS");
				t.city =  rs.getInt("FK_City_ID"); 
	        	
	        	trackList.add(t);
	        	
	        }
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.err.println("An error occured reading Tracks from Database");
		}     
		
		return trackList;
	}
	
}
