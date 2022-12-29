package calendrier;

public class Time {
	  private int hour;
	  private int minute;

	  public Time(int hour, int minute) {
	    this.hour = hour;
	    this.minute = minute;
	  }

	  public int getHour() {
	    return hour;
	  }

	  public int getMinute() {
	    return minute;
	  }

	  public boolean isAfter(Time temps) {
	    if (hour > temps.hour) {
	      return true;
	    } 
	    else if (hour == temps.hour) {
	      if(minute > temps.minute) {
	    	  return true;
	      }
	    }
	    return false;
	  }

	  public boolean isBefore(Time temps) {
	    if (!isAfter(temps) && !equals(temps)) {
	    	return true;
	    }
	    return false;
	  }
}
