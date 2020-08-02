/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;


import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.swing.*;
import app.Event;
import app.DataModel;
public class CreateFrame extends JFrame implements ActionListener {
   JPanel panel;
   JLabel StartTime, EndTime,EventName,Date;
   JTextField start;
   JTextField end;
   JTextField name;
   JTextField date;
   JButton submit, cancel;
   DataModel model;
   JTextArea text;
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
   public static void main(String[] args) {
      new CreateFrame(new DataModel(), new JTextArea());
   }
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
   
   public boolean hasError(LocalTime startTime,LocalTime endTime){
        return startTime.isAfter(endTime);
   }
}