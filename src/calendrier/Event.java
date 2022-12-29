package calendrier;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Event {
  protected String name;
  private String description;
  private Date startTime;
  private Date endTime;
  
  public Event(String name, String description,Date startDate, Date endDate) {
    this.name = name;
    this.description = description;
    this.startTime = startDate;
    this.endTime = endDate;
  }

public String getTitle() {
    return name;
  }

  public String getDescription() {
    return description;
  }
  
  public Date getStartTime() {
	  return startTime;
  }
  
  public Date getEndTime() {
	  return endTime;
  }
  

  
  public void printEvent() {
	  System.out.println("Name:" + name + "\nDescription :" + description +  "\nDate de début:" + startTime+ "\nDate de fin:" + endTime);
  }

}