package app;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.TreeSet;

public class DataModel {
	private ArrayList<Event> oneTimeEvents;
	private ArrayList<RecurringEvent> recurringEvents;
	private LocalDate today;
	private LocalDate reference;
	private LocalDate currentCal;
	private ViewStyle style;
	
	
	public DataModel() {
		oneTimeEvents = new ArrayList<>();
		recurringEvents = new ArrayList<>();
		today = LocalDate.now();
		reference = LocalDate.now();
		currentCal = LocalDate.now();
		style = ViewStyle.DAY;
		
		Event test = new Event("test", LocalTime.of(12, 0), LocalTime.of(15, 0), LocalDate.now());
		Event test2 = new Event("test2", LocalTime.of(10, 0), LocalTime.of(11, 0), LocalDate.now());
		Event test3 = new Event("test3", LocalTime.of(16, 0), LocalTime.of(18, 0), LocalDate.now());
		oneTimeEvents.add(test);
		oneTimeEvents.add(test2);
		oneTimeEvents.add(test3);
	}
	
	public void setViewStyle(ViewStyle newStyle) {
		style = newStyle;
	}
	
	public LocalDate getReference() {
		return reference;
	}
	
	public LocalDate getToday() {
		return today;
	}
	
	public LocalDate getCalDate() {
		return currentCal;
	}
	
	public void setReference(LocalDate date) {
		reference = date;
	}
	
	public void setCalDate(LocalDate date) {
		currentCal = date;
	}
	
	public String getEvents(LocalDate date) {
		if(style == ViewStyle.DAY) {
			return getDayEvents(date);
		}
		else if(style == ViewStyle.WEEK) {
			return getWeekEvents(date);
		}
		else if(style == ViewStyle.MONTH) {
			return getMonthEvents(date);
		}
		else {
			return "An error has occurred";
		}

	}
	
	public String getAgendaEvents(LocalDate startDate, LocalDate endDate) {
		return "";
	}
	
	public String getDayEvents(LocalDate date) {
		TreeSet<Event> events = new TreeSet<>();
		for(Event e : oneTimeEvents) {
			if(e.getDate().isEqual(date)) {
				events.add(e);
			}
		}
		for(RecurringEvent re : recurringEvents) {
			if(re.getDaysOfWeek().contains(date.getDayOfWeek()) && re.isWithin(date)) {
				events.add(new Event(date, re));
			}
		}
		String eventString = date.format(Event.FORMALDATEFORMATTER) + "\n";
		for(Event e : events) {
			eventString = eventString + e.toString();
		}
		return eventString;
	}
	
	public String getWeekEvents(LocalDate date) {
		return "";
	}
	
	public String getMonthEvents(LocalDate date) {
		return "";
	}
	
	ArrayList<LocalDate> changeCurrentCal(int monthChange) {
		return null;
	}
	
	
}
