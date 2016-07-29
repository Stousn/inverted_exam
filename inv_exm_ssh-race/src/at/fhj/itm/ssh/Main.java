package at.fhj.itm.ssh;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import at.fhj.itm.ssh.dao.*;
import at.fhj.itm.ssh.model.*;
import at.fhj.itm.ssh.utils.ConnectionFactory;
import at.fhj.itm.ssh.utils.SqlUtils;


public class Main {
	
	
	public static void main(String[] args) {
		//TODO: forename instead of forname in DB and everywhere
		System.out.println("Welcome to the inverted exam by SSH :)");
		
//		ConnectionFactory.runInitScript();
//		ConnectionFactory.runScriptsFromScriptFolder();
		
//		RaceDAO rdao = new RaceDAO();
//		Race r = new Race(-1, 20, LocalTime.parse("13:00"), LocalTime.parse("00:40:00"), LocalDate.parse("2020-01-01"), 1);
//		rdao.create(r);
//		
//		TrackDAO tdao = new TrackDAO();
//		Track t = new Track(-1, "HeliTest", 42, LocalDateTime.parse("2001-01-01T00:02:01.111"), 42, 1);
//		System.out.println(t.fastestLap);
//		tdao.create(t);
		
		DriverDAO ddao = new DriverDAO();
		List<Driver> ld = new ArrayList<>();
		long before = System.nanoTime();
		int max = SqlUtils.getMaxInt("DRIVER", "ID");
		Driver heaviestDriver = ddao.read(1);
		for(int i=2; i<=max;i++){
			Driver d = new Driver();
			Driver d2 = new Driver();
			d.id = ddao.read(i).id;
			d.fName = ddao.readForname(i);
			d.lName = ddao.readLastname(i);
			d.dob = ddao.readDOB(i);
			d.weightInKg = ddao.readWeight(i);
			d.team = ddao.readTeam(i);
			
			d2.id = ddao.read(i-1).id;
			d2.fName = ddao.readForname(i-1);
			d2.lName = ddao.readLastname(i-1);
			d2.dob = ddao.readDOB(i-1);
			d2.weightInKg = ddao.readWeight(i-1);
			d2.team = ddao.readTeam(i);
			
			if(d.weightInKg>d2.weightInKg){
				if(d.weightInKg>heaviestDriver.weightInKg){
					heaviestDriver = d;
				}
				
			}
			
			
		}
		
		long after = System.nanoTime();
		System.out.println(heaviestDriver.toString());
		System.out.println((after-before)/1E9);
		
		long b2 = System.nanoTime();
		Driver test = ddao.readHeaviest();
		long a2 = System.nanoTime();
		
		System.out.println(test.toString());
		System.out.println((a2-b2)/1E9);
		
		
		

	}
	
	
	
}
