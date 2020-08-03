/**
 * RecurringEventList Class
 *  @author Christian Santos
 *  
 *  @version 1.0.0 07/31/20
 *  
 *  Copyright Christian Santos to Present
 *  All rights reserved
 */

package app;

import java.util.TreeSet;

/**
 * The Class RecurringEventList.
 *
 * @param <E> the element type
 */
public class RecurringEventList<E> extends TreeSet<E> implements EventList {

	/**
	 * Gets the data.
	 *
	 * @return the data about the RecurringEventList
	 */
	@Override
	public String getData() {
		return "There are " + this.size() + " recurring events in the calendar";
	}

}
