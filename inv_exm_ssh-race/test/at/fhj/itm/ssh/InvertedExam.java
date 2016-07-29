package at.fhj.itm.ssh;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import at.fhj.itm.ssh.dao.*;
import at.fhj.itm.ssh.model.*;

public class InvertedExam {
	
	DriverDAO driverDao;

	@Before
	public void setUp() throws Exception {
		driverDao = new DriverDAO();
	}

	@Test
	public void getHeaviestDriver() {
		Driver heaviest = new Driver();
		heaviest = driverDao.readHeaviest();
		
		assertEquals(heaviest.weightInKg, 86);
	}

}
