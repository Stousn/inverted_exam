package at.fhj.itm.ssh;

import static org.junit.Assert.*;
import java.util.*;

import org.junit.Before;
import org.junit.Test;

import at.fhj.itm.ssh.dao.*;
import at.fhj.itm.ssh.model.*;
import at.fhj.itm.ssh.utils.SqlUtils;

public class InvertedExamSolution {

	DriverDAO driverDao;
	TrackDAO trackDao;
	CountryDAO countryDao;
	TeamDAO teamDao;

	@Before
	public void setUp() throws Exception{

		driverDao = new DriverDAO();
		trackDao = new TrackDAO();
		countryDao = new CountryDAO();
		teamDao = new TeamDAO();


	}

	@Test
	public void getHeaviestDriver() {
		Driver heaviest = new Driver();
		heaviest = driverDao.readHeaviest();

		assertEquals(heaviest.weightInKg, 86);
	}

	@Test
	public void teamspercountry(){
		/*
		 * wir ham gemeint das ma eine Abfrage machen welche Länder wie viele
		 * Teams haben und des mit einem berechnenten Wert.
		 * 
		 * 
		 * */

//		List<Driver> drivers = new ArrayList<Driver>();
		HashMap teamsperland = new HashMap();
		Hashtable teams = new Hashtable();

//		drivers = driverDao.readAll();

		int maxcountry = SqlUtils.getMaxInt("COUNTRY", "ID");
		int maxdriver = SqlUtils.getMaxInt("DRIVER", "ID");

		for(int i = 1; i <= maxcountry; i++){
			//			for (Driver driver : drivers) {
			//				if(driver.country == i){
			//					teams.put(teamDao.read(driver.team).name, 1);
			//				}
			//			}	
			for (int j = 1; j <= maxdriver; j++){
				if(driverDao.read(j).country == i){
					teams.put(teamDao.read(driverDao.read(j).team).name,1);
				}
			}

			teamsperland.put(countryDao.read(i).name, teams.size());
			teams.clear();
		}


		System.out.println(teamsperland.get("United Arab Emirates"));


		assertEquals(teamsperland.get("United Arab Emirates"), 8);

	}


	@Test
	//	public void aufgabe3(){
	public void getCityTrackList(){

		/*
		 * Ich hab mir gedacht wir machen ein Beispiel das ma
		 * eine sortierte Liste bekommen die folgende spalten hat.
		 * 
		 * Stadt | Streckenname | Streckenlänge | Schnellste Runde
		 * 
		 * 
		 * nach was sie genau sortiert is könn ma uns ja noch ausmachen
		 * jedenfalls solls wenn ma einen INDEX anlegt dann schneller gehen
		 * 
		 * 
		 * */

	}
}
