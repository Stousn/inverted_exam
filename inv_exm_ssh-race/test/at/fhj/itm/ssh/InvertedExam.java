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
	public void aufgabe1(){
		Driver d = driverDao.getHeaviestDriver();
		assertEquals(d.weightInKg, 200);
	};
	
	
	@Test
	public void aufgabe2(){
		int count = countryDao.getCountryWithMostTeams().get("United Arab Emirates");
		assertEquals(count,8);
	}
	
	@Test
	public void aufgabe3(){
		List<Driver> ld = new ArrayList<>();
		ld = driverDao.getDriversFromCountry("Austria");
		assertEquals(ld.size(), 20);
	}
	
	
}
