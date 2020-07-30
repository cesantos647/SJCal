package view;

import java.time.LocalDate;

import javax.swing.JButton;

public class DateButton extends JButton{
	private LocalDate date;
	
	public DateButton(LocalDate date) {
		super(Integer.toString(date.getDayOfMonth()));
		this.date = date;
	}
	
	public LocalDate getDate() {
		return date;
	}
	
	public void setDate(LocalDate newDate) {
		date = newDate;
	}
	
}
