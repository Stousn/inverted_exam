package at.fhj.itm.ssh;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import at.fhj.itm.ssh.dao.CountryDAO;
import at.fhj.itm.ssh.dao.DriverDAO;
import at.fhj.itm.ssh.dao.TeamDAO;
import at.fhj.itm.ssh.dao.TrackDAO;
import at.fhj.itm.ssh.model.Driver;

public class InvertedExam {
	
	DriverDAO driverDao;
	TrackDAO trackDao;
	TeamDAO teamDao;
	CountryDAO countryDao;

	@Before
	public void setUp() throws Exception {
		driverDao = new DriverDAO();
		trackDao = new TrackDAO();
		teamDao = new TeamDAO();
		countryDao = new CountryDAO();
	}

	@Test
	public void aufgabe1_heli_right(){
		Driver d = driverDao.getHeaviestDriverRIGHT();
		assertEquals(d.weightInKg, 200);
	};
	
	@Test
	public void aufgabe1_heli_wrong(){
		Driver d = driverDao.getHeaviestDriverWRONG();
		assertEquals(d.weightInKg, 200);
		
	}
	
	@Test
	public void aufgabe2_heli_right(){
		int count = countryDao.getCountryWithMostTeamsRIGHT().get("United Arab Emirates");
		assertEquals(count,8);
	}
	@Test
	public void aufabe2_heli_wrong(){
		int count = countryDao.getCountryWithMostTeamsWrong().get("United Arab Emirates");
		assertEquals(count, 8);
		
	}
	@Test
	public void aufgabe3_heli_wrong(){
		List<Driver> ld = new ArrayList<>();
		ld = driverDao.getDriverFromCountryWRONG("Austria");
		assertEquals(ld.size(), 20);
	}
	
	@Test
	public void aufgabe3_heli_right(){
		List<Driver> ld = new ArrayList<>();
		ld = driverDao.getDriverFromCountryRIGHT("Austria");
		assertEquals(ld.size(), 20);
	}
	
	
}
