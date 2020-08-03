/**
 * OneTimeEventList Class
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
 * The Class OneTimeEventList.
 *
 * @param <E> the element type
 */
public class OneTimeEventList<E> extends TreeSet<E> implements EventList {

	/**
	 * Gets the data.
	 *
	 * @return the data about this OneTimeEventList
	 */
	@Override
	public String getData() {
		return "There are " + this.size() + " one time events in the calendar";
	}
	
}
