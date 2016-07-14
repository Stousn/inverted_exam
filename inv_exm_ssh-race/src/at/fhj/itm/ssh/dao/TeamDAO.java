package at.fhj.itm.ssh.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import at.fhj.itm.ssh.db.SqlUtils;
import at.fhj.itm.ssh.model.Team;

public class TeamDAO extends GenericSqlDAO<Team, Integer> {

	@Override
	public Integer create(Team newInstance) {
		PreparedStatement stmt;
		try 
		{
			stmt = connection.prepareStatement("INSERT INTO TEAM (CODE, NAME, FK_Brand_ID) VALUES (?, ?, ?)");
			stmt.setString(1,newInstance.code);
			stmt.setString(2,newInstance.name);
			stmt.setInt(3,newInstance.brand);
	        stmt.executeUpdate();   
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.err.println("Team could not be created");
		}     
		
		newInstance.id = SqlUtils.getMaxInt("TEAM", "ID");
		return newInstance.id;
	}

	@Override
	public Team read(Integer id) {
		PreparedStatement stmt;
		Team t = new Team();
		
		t.id = -1;
		t.code = "unknown code";
		t.name = "unknown name";
		t.brand = -1;
		
		try 
		{
			stmt = connection.prepareStatement("SELECT * FROM TEAM WHERE ID = ?");
			stmt.setInt(1, id);
	        ResultSet rs = stmt.executeQuery();
	        rs.first();
	        
	        // Mapping
	        t.id = rs.getInt("ID");
			t.code = rs.getString("CODE");
			t.name = rs.getString("NAME"); 
			t.brand = rs.getInt("FK_Brand_ID");
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.err.println("Could not read Team (" + id +")");
		}     
		
		return t;
	}

	@Override
	public void update(Team transientObject) {
		PreparedStatement stmt;
		try 
		{
			stmt = connection.prepareStatement("UPDATE TEAM SET CODE = ?, NAME = ?, FK_Brand_ID = ? where ID = ?");
			stmt.setString(1, transientObject.code);
			stmt.setString(2, transientObject.name);
			stmt.setInt(3, transientObject.brand);
			stmt.setInt(4, transientObject.id);

	        int affectedRows = stmt.executeUpdate();
	        
	        if(affectedRows != 1)
	        {
	        	System.out.println("Something strange is going on: Team not found or Team not unique: " + transientObject);
	        }	 
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.err.println("Team could not be upgedated");
		}		
	}

	@Override
	public void delete(Team persistentObject) {
		PreparedStatement stmt;
		try 
		{
			stmt = connection.prepareStatement("DELETE FROM TEAM WHERE ID = ?");
			stmt.setInt(1, persistentObject.id);
	        int affectedRows = stmt.executeUpdate();
	        
	        if(affectedRows != 1)
	        {
	        	System.out.println("Something strange is going on: Team not found or Team not unique: " + persistentObject);
	        }	 
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.err.println("Team could not be found");
		}
	}

	public List<Team> readAll()
	{
		List<Team> teamList = new ArrayList<Team>();
		
		PreparedStatement stmt;
		
		try 
		{
			stmt = connection.prepareStatement("SELECT * FROM TEAM T");
			ResultSet rs = stmt.executeQuery();
	        
	        while(rs.next())
	        {
	        	Team t = new Team();
		        t.id = rs.getInt("ID");
				t.code = rs.getString("CODE");
				t.name = rs.getString("NAME"); 
				t.brand = rs.getInt("FK_Brand_ID");
	        	
	        	teamList.add(t);
	        	
	        }
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.err.println("An error occured reading Teams from Database");
		}     
		
		return teamList;
	}
}
