/**
 * DateButton Class
 *  @author Christian Santos
 *  @author Siyuan Li
 *  
 *  @version 1.0.0 07/31/20
 *  
 *  Copyright SJCal to Present
 *  All rights reserved
 */

package view;

import java.time.LocalDate;

import javax.swing.JButton;

/**
 * The Class DateButton.
 */
public class DateButton extends JButton{
	
	/** The date. */
	private LocalDate date;
	
	/**
	 * Instantiates a new date button.
	 *
	 * @param date the date
	 */
	public DateButton(LocalDate date) {
		super(Integer.toString(date.getDayOfMonth()));
		this.date = date;
	}
	
	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public LocalDate getDate() {
		return date;
	}
	
	/**
	 * Sets the date.
	 *
	 * @param newDate the new date
	 */
	public void setDate(LocalDate newDate) {
		date = newDate;
	}
	
}
