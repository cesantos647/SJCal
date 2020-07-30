package app;

import java.util.TreeSet;

public class OneTimeEventList<E> extends TreeSet<E> implements EventList {

	@Override
	public String getData() {
		return "There are " + this.size() + " one time events in the calendar";
	}
	
}
