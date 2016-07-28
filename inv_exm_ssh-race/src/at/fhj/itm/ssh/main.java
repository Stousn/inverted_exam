package at.fhj.itm.ssh;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import at.fhj.itm.ssh.dao.*;
import at.fhj.itm.ssh.model.*;


public class Main {
	
	
	public static void main(String[] args) {
		
		System.out.println("Welcome to the inverted exam by SSH :)");
		
		
		RaceDAO rdao = new RaceDAO();
		Race r = new Race(-1, 20, LocalTime.parse("13:00"), LocalTime.parse("00:40:00"), LocalDate.parse("2020-01-01"), 1);
		rdao.create(r);
		
		TrackDAO tdao = new TrackDAO();
		Track t = new Track(-1, "HeliTest", 42, LocalDateTime.parse("2001-01-01T00:02:01.111"), 42, 1);
		System.out.println(t.fastestLap);
		tdao.create(t);
		
		
		

	}
	
	
	
}
