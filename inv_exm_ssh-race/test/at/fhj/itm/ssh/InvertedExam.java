package at.fhj.itm.ssh;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import at.fhj.itm.ssh.dao.CountryDAO;
import at.fhj.itm.ssh.dao.DriverDAO;
import at.fhj.itm.ssh.model.Driver;

public class InvertedExam {
	
	DriverDAO driverDao;
	CountryDAO countryDao;

	@Before
	public void setUp() throws Exception {
		driverDao = new DriverDAO();
		countryDao = new CountryDAO();
	}

	@Test
	public void aufgabe1(){
		//Change the method 'getHeaviestDriver' in the DriverDAO-Class
		//to pass this test in under 0.5 seconds
		//(7.671s vs. 0.024s in our tests)
		Driver d = driverDao.getHeaviestDriver();
		assertEquals(d.weightInKg, 200);
	};
	
	
	@Test
	public void aufgabe2(){
		//Change the method 'getCountryWithMostTeams' in the CountryDAO-Class
		//to pass this test in under 0.5 seconds
		//(176.216s vs. 0.017s in our tests)
		int count = countryDao.getCountryWithMostTeams().get("United Arab Emirates");
		assertEquals(count,8);
	}
	
	@Test
	public void aufgabe3(){
		//Change the method 'getDriversFromCountry' in the DriverDAO-Class
		//to pass this test in under 0.5 second
		//(5.448s vs. 0.011s in our tests)
		List<Driver> ld = new ArrayList<>();
		ld = driverDao.getDriversFromCountry("Austria");
		assertEquals(ld.size(), 20);
	}
	
	
}
