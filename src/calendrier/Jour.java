package calendrier;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Jour {
  private LocalDate date;
  private List<Event> events;

  public Jour(LocalDate date) {
    this.date = date;
    this.events = new ArrayList<>();
  } 

  public LocalDate getDate() {
    return date;
  }

  public List<Event> getEvents() {
    return events;
  }

  public void addEvent(Event event) {
    events.add(event);
  }

  public void removeEvent(Event event) {
    events.remove(event);
  }
}
		
		
		