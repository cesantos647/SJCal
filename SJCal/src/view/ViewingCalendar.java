/**
 * AgendaFrame Class
 *  @author Siyuan Li
 *  @author Christian Santos
 *  
 *  @version 1.0.0 07/31/20
 * 
 *  Copyright SJCal to Present
 *  All rights reserved
 */
package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import app.DataModel;

/**
 * The Class ViewingCalendar.
 */
public class ViewingCalendar extends JPanel {
	
	/** The model. */
	private DataModel model;
	
	/** The date buttons. */
	private ArrayList<DateButton> dateButtons;
	
	/** The text. */
	JTextArea text;
	
	/**
	 * Instantiates a new viewing calendar.
	 *
	 * @param model the model
	 * @param text the text
	 */
	public ViewingCalendar(DataModel model, JTextArea text) {
		this.model = model;
		this.text = text;
		dateButtons = getButtons();
		
		this.setLayout(new BorderLayout());
		JPanel nonCalendarFrame = new JPanel();
		JPanel topPanelView = new JPanel();
		JLabel header = new JLabel(model.getCalDate().getMonth() + " " + model.getCalDate().getYear());
		JButton leftButton = new JButton("<");
		leftButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.changeCalDate(-1);
				header.setText(model.getCalDate().getMonth() + " " + model.getCalDate().getYear());
				ArrayList<LocalDate> newDates = getDates(model.getCalDate());
				for(int i = 0 ; i < dateButtons.size(); i++) {
					dateButtons.get(i).setDate(newDates.get(i));
					dateButtons.get(i).setText(Integer.toString(newDates.get(i).getDayOfMonth()));
					if(!dateButtons.get(i).getDate().getMonth().equals(model.getCalDate().getMonth())) {
						dateButtons.get(i).setForeground(Color.GRAY);
					}
					else {
						dateButtons.get(i).setForeground(Color.BLACK);
					}
				}
			}
			
		});
		JButton rightButton = new JButton(">");
		rightButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.changeCalDate(1);
				header.setText(model.getCalDate().getMonth() + " " + model.getCalDate().getYear());
				ArrayList<LocalDate> newDates = getDates(model.getCalDate());
				for(int i = 0 ; i < dateButtons.size(); i++) {
					dateButtons.get(i).setDate(newDates.get(i));
					dateButtons.get(i).setText(Integer.toString(newDates.get(i).getDayOfMonth()));
					if(!dateButtons.get(i).getDate().getMonth().equals(model.getCalDate().getMonth())) {
						dateButtons.get(i).setForeground(Color.GRAY);
					}
					else {
						dateButtons.get(i).setForeground(Color.BLACK);
					}
				}
				
			}
			
		});
		
		JPanel bottomPanelView = new JPanel();
		
		bottomPanelView.setLayout(new GridLayout(1, 7));
		bottomPanelView.add(new JLabel("S", SwingConstants.CENTER));
		bottomPanelView.add(new JLabel("M", SwingConstants.CENTER));
		bottomPanelView.add(new JLabel("T", SwingConstants.CENTER));
		bottomPanelView.add(new JLabel("W", SwingConstants.CENTER));
		bottomPanelView.add(new JLabel("R", SwingConstants.CENTER));
		bottomPanelView.add(new JLabel("F", SwingConstants.CENTER));
		bottomPanelView.add(new JLabel("S", SwingConstants.CENTER));
		
		
		JPanel calendarFrame = new JPanel();
		calendarFrame.setLayout(new GridLayout(6, 7));
		calendarFrame.setBorder(BorderFactory.createEmptyBorder());
		
		for(DateButton db : dateButtons) {
			calendarFrame.add(db);
		}

		topPanelView.add(header);
		topPanelView.add(leftButton);
		topPanelView.add(rightButton);
		
		nonCalendarFrame.setLayout(new BorderLayout());
		nonCalendarFrame.add(topPanelView, BorderLayout.PAGE_START);
		nonCalendarFrame.add(bottomPanelView, BorderLayout.CENTER);
		
		this.add(nonCalendarFrame, BorderLayout.PAGE_START);
		this.add(calendarFrame, BorderLayout.CENTER);
		
		this.setSize(100,50);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
	}
	
	/**
	 * Gets the buttons for the Viewing Calendar.
	 *
	 * @return the buttons
	 */
	public ArrayList<DateButton> getButtons() {
		ArrayList<DateButton> buttons = new ArrayList<>();
		//Get first day of month, then finds the date of the Monday of that week
		LocalDate startDate = (model.getCalDate().with(TemporalAdjusters.firstDayOfMonth())).with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
		//Get the last day of the month, then finds the date of the Sunday of that week
		LocalDate endDate = (model.getCalDate().with(TemporalAdjusters.lastDayOfMonth())).with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY));
		LocalDate current = startDate;
		while(!current.isAfter(endDate) || buttons.size() < 42) {
			DateButton newButton = new DateButton(current);
			newButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					model.setReference(newButton.getDate());
					text.setText(model.getEvents(model.getReference()));
				}
			});
			newButton.setPreferredSize(new Dimension(15,15));
			if(!newButton.getDate().getMonth().equals(model.getCalDate().getMonth())) {
				newButton.setForeground(Color.GRAY);
			}
			buttons.add(newButton);
			current = current.plusDays(1);
		}
		return buttons;
	}
	
	/**
	 * Gets the dates for the Viewing Calendar.
	 *
	 * @param date the date
	 * @return the dates
	 */
	public ArrayList<LocalDate> getDates(LocalDate date) {
		ArrayList<LocalDate> dates = new ArrayList<>();
		//Get first day of month, then finds the date of the Monday of that week
		LocalDate startDate = (date.with(TemporalAdjusters.firstDayOfMonth())).with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
		//Get the last day of the month, then finds the date of the Sunday of that week
		LocalDate endDate = (date.with(TemporalAdjusters.lastDayOfMonth())).with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY));
		LocalDate current = startDate;
		while(!current.isAfter(endDate) || dates.size() < 42) {
			dates.add(current);
			current = current.plusDays(1);
		}
		return dates;
	}

}
