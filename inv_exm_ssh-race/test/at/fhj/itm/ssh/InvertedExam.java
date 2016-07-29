package at.fhj.itm.ssh;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import at.fhj.itm.ssh.dao.*;
import at.fhj.itm.ssh.model.*;
import at.fhj.itm.ssh.utils.SqlUtils;

public class InvertedExam {
	
	DriverDAO driverDao;
	

	@Before
	public void setUp() throws Exception {
		driverDao = new DriverDAO();
	}

	@Test
	public void getHeaviestDriver(){
		
		int max = SqlUtils.getMaxInt("DRIVER", "ID");
		Driver heaviestDriver = driverDao.read(1);
		for(int i=2; i<=max;i++){
			Driver d = new Driver();
			Driver d2 = new Driver();
			d.id = driverDao.read(i).id;
			d.fName = driverDao.readForename(i);
			d.lName = driverDao.readLastname(i);
			d.dob = driverDao.readDOB(i);
			d.weightInKg = driverDao.readWeight(i);
			d.team = driverDao.readTeam(i);
			
			d2.id = driverDao.read(i-1).id;
			d2.fName = driverDao.readForename(i-1);
			d2.lName = driverDao.readLastname(i-1);
			d2.dob = driverDao.readDOB(i-1);
			d2.weightInKg = driverDao.readWeight(i-1);
			d2.team = driverDao.readTeam(i);
			
			if(d.weightInKg>d2.weightInKg){
				if(d.weightInKg>heaviestDriver.weightInKg){
					heaviestDriver = d;
				}	
			}
		}
	
		assertEquals(heaviestDriver.weightInKg, 86);
		
	}
	
	
}
