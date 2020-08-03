/**
 * CreateFrame Class
 *  @author Siyuan Li
 *  @author Christian Santos
 *  
 *  @version 1.0.0 07/28/20
 *  
 *  Copyright SJCal to Present
 *  All rights reserved
 */
package view;


import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.swing.*;
import app.Event;
import app.DataModel;

/**
 * The Class CreateFrame.
 */
public class CreateFrame extends JFrame implements ActionListener {
   
   /** The panel. */
   JPanel panel;
   
   /** The Date. */
   JLabel StartTime, EndTime,EventName,Date;
   
   /** The start. */
   JTextField start;
   
   /** The end. */
   JTextField end;
   
   /** The name. */
   JTextField name;
   
   /** The date. */
   JTextField date;
   
   /** The cancel. */
   JButton submit, cancel;
   
   /** The model. */
   DataModel model;
   
   /** The text. */
   JTextArea text;
   
   /**
    * Instantiates a new creates the frame.
    *
    * @param model the model
    * @param text the text
    */
   CreateFrame(DataModel model, JTextArea text) {
      
      this.model=model;
      this.text=text;
      
      EventName=new JLabel();
      EventName.setText("Name");
      EventName.setBounds(0,10,10,10);
      name=new JTextField(15);
      
      StartTime = new JLabel();
      StartTime.setText("Start Time (E.g. )");
      StartTime.setBounds(0,10,10,10);
      start = new JTextField(15);
      
      
      EndTime = new JLabel();
      EndTime.setBounds(0,10,10,10);
      EndTime.setText("End Time");
      end = new JTextField(15);
      
      Date = new JLabel();
      Date.setBounds(0,10,10,10);
      Date.setText("Date (E.g. 6/23/20)");
      date = new JTextField(15);
      // Submit
      submit = new JButton("SUBMIT");
      submit.setSize(4,4);
      panel = new JPanel(new GridLayout(10, 1));
      panel.add(EventName);
      panel.add(name);
      panel.add(StartTime);
      panel.add(start);
      panel.add(EndTime);
      panel.add(end);
      panel.add(Date);
      panel.add(date);
      
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      // Adding the listeners to components..
      submit.addActionListener(this);
      add(panel, BorderLayout.CENTER);
      add(submit,BorderLayout.SOUTH);
      setTitle("Create Frame");
      setSize(300,300);
      setVisible(true);
      setResizable(false);
   }
   
   /**
    * Action performed to add a new Event to the Calendar
    *
    * @param ae the Action Event
    */
   @Override
   public void actionPerformed(ActionEvent ae) {
	   String title = name.getText();
	   LocalTime startTime = LocalTime.parse(start.getText(), Event.TIMEFORMATTER);
	   LocalTime endTime = LocalTime.parse(end.getText(), Event.TIMEFORMATTER);
	   LocalDate d = LocalDate.parse(date.getText(), Event.DATEFORMATTER);
      
      //get the format of the string and manipulate it
      if(!hasError(startTime,endTime)){
    	  Event e = new Event(title, startTime, endTime, d);
          boolean added = model.addEvent(e);
          if(added) {
        	  JOptionPane.showMessageDialog(new JFrame(),"Your event has successfully been added!","Success!",JOptionPane.INFORMATION_MESSAGE);
              this.setVisible(false);
          }
          else {
        	  JOptionPane.showMessageDialog(new JFrame(),"Wrong format or Time conflict","Warning",JOptionPane.ERROR_MESSAGE);
          }
      }
      else {
    	  JOptionPane.showMessageDialog(new JFrame(),"Wrong format or Time conflict","Warning",JOptionPane.ERROR_MESSAGE);
      }
   }
   
   /**
    * Checks for error.
    *
    * @param startTime the start time
    * @param endTime the end time
    * @return true, if the start time is after the end time
    */
   public boolean hasError(LocalTime startTime,LocalTime endTime){
        return startTime.isAfter(endTime);
   }
}