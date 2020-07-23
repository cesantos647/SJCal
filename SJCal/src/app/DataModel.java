package app;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.TreeSet;

/**
 * The Class DataModel.
 */
public class DataModel {
	
	/** The one time events. */
	private ArrayList<Event> oneTimeEvents;
	
	/** The recurring events. */
	private ArrayList<RecurringEvent> recurringEvents;
	
	/** The today. */
	private LocalDate today;
	
	/** The reference. */
	private LocalDate reference;
	
	/** The current calendar view. */
	private LocalDate calView;
	
	/** The style. */
	private ViewStyle style;
	
	
	/**
	 * Instantiates a new data model.
	 */
	public DataModel() {
		oneTimeEvents = new ArrayList<>();
		recurringEvents = new ArrayList<>();
		today = LocalDate.now();
		reference = LocalDate.now();
		calView = LocalDate.now();
		style = ViewStyle.DAY;
		
		Event test = new Event("test", LocalTime.of(12, 0), LocalTime.of(15, 0), LocalDate.now());
		Event test2 = new Event("test2", LocalTime.of(10, 0), LocalTime.of(11, 0), LocalDate.now());
		Event test3 = new Event("test3", LocalTime.of(16, 0), LocalTime.of(18, 0), LocalDate.now());
		oneTimeEvents.add(test);
		oneTimeEvents.add(test2);
		oneTimeEvents.add(test3);
	}
	
	/**
	 * Sets the view style.
	 *
	 * @param newStyle the new view style
	 */
	public void setViewStyle(ViewStyle newStyle) {
		style = newStyle;
	}
	
	/**
	 * Gets the reference.
	 *
	 * @return the reference
	 */
	public LocalDate getReference() {
		return reference;
	}
	
	/**
	 * Gets the today.
	 *
	 * @return the today
	 */
	public LocalDate getToday() {
		return today;
	}
	
	/**
	 * Gets the viewing calendar date.
	 *
	 * @return the viewing calendar date
	 */
	public LocalDate getCalDate() {
		return calView;
	}
	
	/**
	 * Sets the reference.
	 *
	 * @param date the new reference
	 */
	public void setReference(LocalDate date) {
		reference = date;
	}
	
	/**
	 * Sets the viewing calendar date.
	 *
	 * @param date the new viewing calendar date
	 */
	public void setCalDate(LocalDate date) {
		calView = date;
	}
	
	/**
	 * Gets the events.
	 *
	 * @param date the date
	 * @return the events
	 */
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
	
	/**
	 * Gets the agenda events.
	 *
	 * @param startDate the start date
	 * @param endDate the end date
	 * @return the agenda events
	 */
	public String getAgendaEvents(LocalDate startDate, LocalDate endDate) {
		return "";
	}
	
	/**
	 * Gets the day events.
	 *
	 * @param date the date
	 * @return the day events
	 */
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
	
	/**
	 * Gets the week events.
	 *
	 * @param date the date
	 * @return the week events
	 */
	public String getWeekEvents(LocalDate date) {
		return "";
	}
	
	/**
	 * Gets the month events.
	 *
	 * @param date the date
	 * @return the month events
	 */
	public String getMonthEvents(LocalDate date) {
		return "";
	}
	
	
	
}
