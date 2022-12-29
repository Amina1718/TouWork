package calendrier;

import calendrier.Date;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Calendar {
  private List<Event> events;

  public Calendar() {
    this.events = new ArrayList<>();
  }

  public void addEvent(Event event) {
    events.add(event);
  }

  public void removeEvent(Event event) {
    events.remove(event);
  }

  public List<Event> getAllEvents() {
    return events;
  }
  
  public Event getEventByTitle(String title) {
	  for (Event event : events) {
	    if (event.getTitle().equals(title)) {
	      return event;
	    }
	  }
	  return null;
	}
  
  public Event getEventByDate(Date date) {
	  for(Event event: events) {
		  int yDate= date.getYear();
		  int mDate= date.getMonth();
		  int jDate= date.getDay();
		  
		  int yEvent= event.getStartTime().getYear();
		  int mEvent= event.getStartTime().getMonth();
		  int jEvent= event.getStartTime().getDay();
		  if (yEvent==yDate && mEvent==mDate && jEvent==jDate){
			  return event;
		  }
	  }
	  return null;
}
  
  public Event getEventAt(LocalDate date, DayOfWeek dayOfWeek, LocalTime time) {
	    // Recherche de l'événement à l'emplacement spécifié
	    for (Event event : events) {
	        if (event.getStartTime().equals(date) && event.getStartTime().equals(dayOfWeek) && event.getStartTime().equals(time)) {
	            return event;
	        }
	    }
	    // Si aucun événement n'a été trouvé, renvoie null
	    return null;
	}

  
  
 public List<Event> getEventsByDate(Date date) {
	    List<Event> eventsOnDate = new ArrayList<>();
	    for (Event event : events) {
	        int yDate = date.getYear();
	        int mDate = date.getMonth();
	        int jDate = date.getDay();

	        int yEvent = event.getStartTime().getYear();
	        int mEvent = event.getStartTime().getMonth();
	        int jEvent = event.getStartTime().getDay();
	        if (yEvent == yDate && mEvent == mDate && jEvent == jDate) {
	            eventsOnDate.add(event);
	        }
	    }
	    return eventsOnDate;
	}
}





