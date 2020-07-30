package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import app.DataModel;

public class ViewingCalendar extends JPanel {
	private DataModel model;
	private ArrayList<DateButton> dateButtons;
	JTextArea text;
	
	public ViewingCalendar(DataModel model, JTextArea text) {
		this.model = model;
		this.text = text;
		dateButtons = getButtons();
		System.out.println(dateButtons.size());
		
		this.setLayout(new BorderLayout());
		JPanel nonCalendarFrame = new JPanel();
		JPanel topPanelView = new JPanel();
		JLabel header = new JLabel(model.getCalDate().getMonth() + " " + model.getCalDate().getYear());
		JButton leftButton = new JButton("<");
		leftButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Left button pressed");
				model.changeCalDate(-1);
				header.setText(model.getCalDate().getMonth() + " " + model.getCalDate().getYear());
				ArrayList<LocalDate> newDates = getDates(model.getCalDate());
				for(int i = 0 ; i < dateButtons.size(); i++) {
					dateButtons.get(i).setDate(newDates.get(i));
					dateButtons.get(i).setText(Integer.toString(newDates.get(i).getDayOfMonth()));
				}
			}
			
		});
		JButton rightButton = new JButton(">");
		rightButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Right button pressed");
				model.changeCalDate(1);
				header.setText(model.getCalDate().getMonth() + " " + model.getCalDate().getYear());
				ArrayList<LocalDate> newDates = getDates(model.getCalDate());
				for(int i = 0 ; i < dateButtons.size(); i++) {
					dateButtons.get(i).setDate(newDates.get(i));
					dateButtons.get(i).setText(Integer.toString(newDates.get(i).getDayOfMonth()));
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
		
		this.setSize(270,200);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
	}
	
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
			buttons.add(newButton);
			current = current.plusDays(1);
		}
		return buttons;
	}
	
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
	
	public static void main(String[] args) {
		ViewingCalendar vc = new ViewingCalendar(new DataModel(), new JTextArea());
	}

}
