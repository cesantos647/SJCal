/**
 * DataModel Class
 *  @author Christian Santos
 *  
 *  @version 1.0.0 07/27/20
 *  
 *  Copyright Christian Santos to Present
 *  All rights reserved
 */

package app;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.TreeSet;
import java.util.Scanner;

/**
 * The Class DataModel.
 */
public class DataModel {
	
	/** The one time events. */
	private OneTimeEventList<Event> oneTimeEvents;
	
	/** The recurring events. */
	private RecurringEventList<RecurringEvent> recurringEvents;
	
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
		oneTimeEvents = new OneTimeEventList<>();
		recurringEvents = new RecurringEventList<>();
		reference = LocalDate.now();
		calView = LocalDate.now();
		style = ViewStyle.DAY;
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
	 * Gets the viewing calendar date.
	 *
	 * @return the viewing calendar date
	 */
	public LocalDate getCalDate() {
		return calView;
	}
	
	public OneTimeEventList<Event> getOneTimeEvents() {
		return oneTimeEvents;
	}
	
	public RecurringEventList<RecurringEvent> getRecurringEvents() {
		return recurringEvents;
	}
	
	/**
	 * Change reference.
	 *
	 * @param change the change
	 */
	public void changeReference(int change) {
		//Sets day to today
		if(change == 0) {
			reference = LocalDate.now();
		}
		
		if(style == ViewStyle.DAY) {
			reference = reference.plusDays(change);
		}
		else if(style == ViewStyle.WEEK) {
			reference = reference.plusWeeks(change);
		}
		else if(style == ViewStyle.MONTH) {
			reference = reference.plusMonths(change);
		}
		else {
			System.out.println("An error has occurred");
		}
	}

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
	 * Change cal date.
	 *
	 * @param change the change
	 */
	public void changeCalDate(int change) {
		calView = calView.plusMonths(change);
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
		LocalDate current = startDate;
		String eventString = "";
		while(!current.isAfter(endDate)) {
			eventString = eventString + this.getDayEvents(current);
			current = current.plusDays(1);
		}
		return eventString;
	}
	
	/**
	 * Gets the day events.
	 *
	 * @param date the date
	 * @return the day's events
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
		if(events.isEmpty()) {
			eventString = eventString + "There are no events scheduled on this day \n";
		}
		for(Event e : events) {
			eventString = eventString + e.toString();
		}
		return eventString + "\n";
	}
	
	/**
	 * Gets the week events.
	 *
	 * @param date the date
	 * @return the week's events
	 */
	public String getWeekEvents(LocalDate date) {
		LocalDate current = date.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
		LocalDate endWeek = date.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
		String eventString = "";
		while(!current.isEqual(endWeek)) {
			eventString = eventString + this.getDayEvents(current);
			current = current.plusDays(1);
		}
		return eventString;
	}
	
	/**
	 * Gets the month events.
	 *
	 * @param date the date
	 * @return the month's events
	 */
	public String getMonthEvents(LocalDate date) {
		LocalDate current = date.with(TemporalAdjusters.firstDayOfMonth());
		LocalDate endMonth = date.with(TemporalAdjusters.lastDayOfMonth());
		String eventString = "";
		while(!current.isEqual(endMonth)) {
			eventString = eventString + this.getDayEvents(current);
			current = current.plusDays(1);
		}
		return eventString;
	}
	
	/**
	 * Adds the RecurringEvent.
	 *
	 * @param newEvent the new recurring event
	 */
	public boolean addEvent(RecurringEvent newEvent) {
		if(!checkEventConflicts(newEvent)) {
			recurringEvents.add(newEvent);
			return true;
		}
		else {
			System.out.println("There is a conflict. Please try again");
			return false;
		}
	}
	
	/**
	 * Adds the Event.
	 *
	 * @param newEvent the new event
	 */
	public boolean addEvent(Event newEvent) {
		if(!checkEventConflicts(newEvent)) {
			oneTimeEvents.add(newEvent);
			System.out.println("The event has successfully been added");
			return true;
		}
		else {
			System.out.println("There is a conflict. Please try again");
			return false;
		}
	}
	
	
	/**
	 * Check if there are any event conflicts between the event passed
	 * and the rest of the calendar.
	 *
	 * @param newEvent the new event
	 * @return true, if there are any conflicts with an event on the calendar
	 */
	private boolean checkEventConflicts(Event newEvent) {
		for(Event e : oneTimeEvents) {
			if(e.getTimeInterval().conflictsWith(newEvent.getTimeInterval()) 
					&& e.getDate().equals(newEvent.getDate())) {
				System.out.println(e.getName() + " conflicts");
				return true;
			}
		}
		for(RecurringEvent re : recurringEvents) {
			if(re.getTimeInterval().conflictsWith(newEvent.getTimeInterval())) {
				//If the date is within the RecurringEvent and if the day is within the daysOfWeek
				if(re.isWithin(newEvent.getDate()) && re.getDaysOfWeek().contains(newEvent.getDate().getDayOfWeek())) {
					System.out.println(re.getName() + " conflicts");
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Check event conflicts.
	 *
	 * @param newEvent the new event
	 * @return true, if successful
	 */
	private boolean checkEventConflicts(RecurringEvent newEvent) {
		for(Event e : oneTimeEvents) {
			if(newEvent.isWithin(e.getDate()) 
					&& newEvent.getDaysOfWeek().contains(e.getDate().getDayOfWeek()) 
					&& e.getTimeInterval().conflictsWith(newEvent.getTimeInterval())) {
				System.out.println(e.getName() + " conflicts");
				return true;
			}
		}
		for(RecurringEvent re : recurringEvents) {
			if(newEvent.getTimeInterval().conflictsWith(re.getTimeInterval())) {
				if(newEvent.isWithin(re.getStartDate()) || newEvent.isWithin(re.getEndDate())) {
					for(DayOfWeek day : newEvent.getDaysOfWeek()) {
						if(re.getDaysOfWeek().contains(day)) {
							System.out.println(re.getName() + " conflicts");
							return false;
						}
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * Populate events from a file.
	 *
	 * @param fileName the file path
	 */
	public boolean populateEvents(String fileName) {
		boolean allGood = true;
		try {
			
			Scanner eventLoader = new Scanner(new File(fileName));
			while(eventLoader.hasNextLine()) {
				String[] params = eventLoader.nextLine().split(";");
				String name = params[0];
				int year = Integer.parseInt(params[1]);
				int startMonth = Integer.parseInt(params[2]);
				int endMonth = Integer.parseInt(params[3]);
				LocalDate startDate = LocalDate.of(year, startMonth, 1);
				LocalDate endDate = startDate.plusMonths(endMonth-startMonth+1).minusDays(1);
				String days = params[4];
				LocalTime startTime = LocalTime.of(Integer.parseInt(params[5]), 0);
				LocalTime endTime = LocalTime.of(Integer.parseInt(params[6]), 0);
				
				RecurringEvent newEvent = new RecurringEvent(name, days, startTime, endTime, startDate, endDate);
				boolean added = addEvent(newEvent);			
				if(added) {
					System.out.println(newEvent.getName() + " added in");
				} 
				else {
					System.out.println(newEvent.getName() + " not added in");
					allGood = false;
				}
			}
			eventLoader.close();
			System.out.println("Loading is done!");
		}
		catch(FileNotFoundException e){
			System.out.println("File not found");
			allGood = false;
		}
		return allGood;
	}
}
