package main;

import java.util.List;
import java.util.Scanner;

import calendrier.Calendar;
import calendrier.Date;
import calendrier.Event;
import guicalendrier.Semaine;



public class Test {
	
	public static void main(String[] args) {
		Calendar calendar = new Calendar();
		Scanner scanner = new Scanner(System.in);
		//Event event1=new Event("Cours", "cours de BD", LocalDateTime.of(2022,Month.DECEMBER,22,8,0);
		//Event event1=new Event("Cours", "cours de BD", LocalDateTime.parse("2022-12-22 08:00", DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm")), LocalDateTime.parse("2022-12-22 10:00", DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm")));
		
		//Event event2=new Event("Cours", "cours de algo", LocalDateTime.parse("2022-12-23 08:00", DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm")), LocalDateTime.parse("2022-12-22 08:00", DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm")));
		//Event event3=new Event("Cours", "cours de BD2", LocalDateTime.parse("2022-12-23 08:00", DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm")), LocalDateTime.parse("2022-12-22 08:00", DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm")));
		
		Event event3=new Event("Courstest", "cours de BD2", new Date(2022,12,28, 13,0) , new Date(2022,12,28, 15,0));
		
		Event event1=new Event("Cours1", "cours de BD", new Date(2022,12,29, 13, 0), new Date(2022,12,29,17 , 0));
		
		Event event2=new Event("Cours2", "cours de algo", new Date(2023,01,01, 11, 0), new Date(2023,01,01, 14, 0));
	
		//calendar.addEvent(event3);
		//calendar.addEvent(event2);
		//calendar.addEvent(event1);
		
		event1.printEvent();
		//event2.printEvent();
		event3.printEvent();
		
		//List<Event> stock = calendar.getEventsByDate(new Date(2022,12,23));
		
		//calendar.getEventByDate(LocalDate.of(2022,12,22));
		
		List<Event> eventsOnDate = calendar.getEventsByDate(new Date(2022,12,23, 12,0));

		System.out.println("Liste des événements du " + new Date(2022,12,23, 14,0)+ " :");
		for (Event event : eventsOnDate) {
		    System.out.println("- " + event.getDescription());
		}
		
		
		Semaine frame = new Semaine();
        frame.setVisible(true);
        frame.addEvent(event1);
        frame.addEvent(event2);
		
}   
	
}