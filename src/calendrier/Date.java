package calendrier;

public class Date {
	  private int year;
	  private int month;
	  private int day;
	  private int hour;
	  private int minute;

	  public Date(int year, int month, int day, int hour, int minute) {
	    this.year = year;
	    this.month = month;
	    this.day = day;
	    this.hour=hour;
	    this.minute=minute;
	  }

	  public int getYear() {
	    return year;
	  }

	  public int getMonth() {
	    return month;
	  }

	  public int getDay() {
	    return day;
	  }
	  
	  public int getHour() {
		  return hour;
	  }
	  
	  public int getMinute() {
		  return minute;
	  }
	  
	  public void setHours(int hours) {
	        this.hour = hours;
	    }

	    public void setMinutes(int minutes) {
	        this.minute = minutes;
	    }
	  
	  

	  public boolean isAfter(Date date) {
	    if (year > date.year) {
	      return true;
	    } 
	    else if (year == date.year) {
	      if (month > date.month) {
	        return true;
	      } 
	      else if (month == date.month) {
	        if (day > date.day) {
	        	return true;
	        }
	      }
	    }
	    return false;
	  }

	  public boolean isBefore(Date date) {
		  if(!isAfter(date) && !equals(date)) {
			  return true;
		  }
		  return false;
	  }
	  
	  public String toString() {
		  return getDay()+ "/" + getMonth() +"/"+ getYear();
		  
	  }
}
