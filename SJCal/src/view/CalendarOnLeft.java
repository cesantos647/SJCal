package view;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import java.awt.event.*;
import javax.swing.event.*;

import app.DataModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarOnLeft implements ChangeListener {
	
	private DataModel model;
	private DAYS[]dayArray=DAYS.values();
	private MONTHS[]monthArray=MONTHS.values();
	private int highlight=-1;
	private int maxDays;
	private GregorianCalendar cal=new GregorianCalendar();
	
	private JFrame frame=new JFrame("SJCAL");
	private JPanel monthViewPanel=new JPanel();
	private JLabel monthLabel=new JLabel();
	private JButton create=new JButton("create");
	private JButton nextDay=new JButton(">");
	private JButton prevDay=new JButton("<");
	private ArrayList<JButton>dayButton=new ArrayList<JButton>();
	
	public CalendarOnLeft(DataModel model){
		this.model=model;
		maxDays=cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		monthViewPanel.setLayout(new GridLayout(0,7));
		
		createDayButtons();
		addBlankButtons();
		addDayButtons();
		//highlightEvents();
		//highlightSelectedDate(model.getSelectedDay()-1);
		
		//create a new event when "create" is clicked"
		create.addActionListener(event->{
			//TODO: the function of create a dialog
			createDialog();
		});
		
		//set the function of prevMonth
		JButton prevMonth=new JButton("<");
		prevMonth.addActionListener(event->{
			//dataModel.prevMonth();// TODO: dataModel part should have the function prevMonth
			create.setEnabled(false);
			nextDay.setEnabled(false);
			prevDay.setEnabled(false);
		});
		
		//set the function of nextMonth
		JButton nextMonth=new JButton(">");
		nextMonth.addActionListener(event->{
			//dataModel.nextMonth();//TODO: dataModel part should have the function nextMonth
			create.setEnabled(false);
			nextDay.setEnabled(false);
			prevDay.setEnabled(false);
		});
		
		JPanel monthContainer=new JPanel();
		monthContainer.setLayout(new BorderLayout());
		monthLabel.setText(monthArray[cal.get(Calendar.MONTH)]+" "+cal.get(Calendar.YEAR));
		monthContainer.add(monthLabel,BorderLayout.NORTH);
		monthContainer.add(new JLabel("   Su   Mo   Tu   We   Th   Fr   Sa"),BorderLayout.CENTER);
		monthContainer.add(monthViewPanel,BorderLayout.SOUTH);
		
		JPanel buttonsPanel=new JPanel();
		nextDay.addActionListener(event->{
			//model.nextDay();//TODO: dataModel part should add the nextDay() function 
		});
		
		prevDay.addActionListener(event->{
			//model.prevDay();//TODO: dataModel part should add the prevDay() function
		});
		
		
		
		JButton saved=new JButton("saved");
		saved.addActionListener(event->{
			//dataModel.savedEvent();//TODO: dataModel should have a saved event function
			System.exit(0);
		});
		
		buttonsPanel.add(prevDay);
		buttonsPanel.add(nextDay);
		buttonsPanel.add(saved);
		
		frame.add(prevMonth);
		frame.add(monthContainer);
		frame.add(nextMonth);
		frame.add(buttonsPanel);
		frame.add(saved);
		frame.setLayout(new FlowLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	private void createDialog(){}
	/*
	//highlight the selected daate
	private void highlightSelectedDate(int d){
		Border border=new LineBorder(Color.BLUE,4);
		dayButton.get(d).setBorder(border);
		if(highlight!=-1){
			dayButton.get(highlight).setBorder(new JButton().getBorder());
		}
		highlight=d;
	}
	
	//highlight the days containing events 
	private void highlightEvents(){
		for(int i=1;i<=maxDays;i++){
			//TODO: model part should have hasEvent function 
		   if(model.hasEvent()){
			   dayButton.get(i-1).setBackground(Color.YELLOW):
		   }
		}
	}
	*/
	private void createDayButtons(){
		for(int i=1;i<=maxDays;i++){
			int day=i;
			JButton currentDay=new JButton(day+"");
			currentDay.addActionListener(event->{
				//highlightSelectedDate(day-1);
				create.setEnabled(false);
				nextDay.setEnabled(false);
				prevDay.setEnabled(false);
			});
			
			dayButton.add(currentDay);
		}
	}
	
	//add all the day buttons onto the month view panel 
	private void addDayButtons(){
		for(JButton day:dayButton){
			monthViewPanel.add(day);
		}
	}
	
	//there is some blankButtons before the starting day of the month
	private void addBlankButtons(){
		//TODO model part should have the getDayOfWeek method
		/*for(int j=1;j<model.;j++){
			JButton blank=new JButton();
			blank.setEnabled(false);
			monthViewPanel.add(blank);
		}*/
	}
	
	public void stateChanged(ChangeEvent e){}
	public static void main(String[] args) {
		CalendarOnLeft left = new CalendarOnLeft(new DataModel());
	}
}