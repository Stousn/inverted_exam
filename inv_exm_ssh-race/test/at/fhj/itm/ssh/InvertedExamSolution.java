package at.fhj.itm.ssh;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import at.fhj.itm.ssh.dao.*;
import at.fhj.itm.ssh.model.*;

public class InvertedExamSolution {

	DriverDAO driverDao;
	TrackDAO trackDao;
	
	
	@Before
	public void setUp() throws Exception{
		
		driverDao = new DriverDAO();
		trackDao = new TrackDAO();

	}
	
	@Test
	public void getHeaviestDriver() {
		Driver heaviest = new Driver();
		heaviest = driverDao.readHeaviest();
		
		assertEquals(heaviest.weightInKg, 86);
	}
	
	@Test
	public void aufgabe2(){
		/*
		 * wir ham gemeint das ma eine Abfrage machen welche Länder wie viele
		 * Teams haben und des mit einem berechnenten Wert.
		 * 
		 * 
		 * */
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
