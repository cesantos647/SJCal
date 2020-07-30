package app;

import java.util.TreeSet;

public class RecurringEventList<E> extends TreeSet<E> implements EventList {

	@Override
	public String getData() {
		return "There are " + this.size() + " recurring events in the calendar";
	}

}
