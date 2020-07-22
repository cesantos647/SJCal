package app;

import java.time.LocalDate;
import java.util.ArrayList;

public class DataModel {
	private ArrayList<Event> oneTimeEvents;
	private ArrayList<RecurringEvent> recurringEvents;
	private LocalDate today;
	private LocalDate reference;
	private LocalDate currentCal;
	private ViewStyle style;
	
	
	public DataModel() {
		today = LocalDate.now();
		reference = LocalDate.now();
		currentCal = LocalDate.now();
	}
	
	public void setViewStyle(ViewStyle newStyle) {
		//
	}
	
	public String getEvents(LocalDate date) {		
		return "";
	}
	
	public String getEvents(LocalDate startDate, LocalDate endDate) {
		return "";
	}
	
	ArrayList<LocalDate> changeCurrentCal(int monthChange) {
		return null;
	}
	
	
}
