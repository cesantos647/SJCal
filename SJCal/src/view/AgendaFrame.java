/**
 * AgendaFrame Class
 *  @author Siyuan Li
 *  @author Christian Santos
 *  
 *  @version 1.0.0 07/27/20
 *  
 *  Copyright SJCal to Present
 *  All rights reserved
 */
package view;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;

import javax.swing.*;

import app.DataModel;
import app.Event;

/**
 * The Class AgendaFrame.
 */
public class AgendaFrame extends JFrame implements ActionListener {
   
   /** The panel. */
   JPanel panel;
   
   /** The end date. */
   JLabel startDate, endDate;
   
   /** The start. */
   JTextField start;
   
   /** The end. */
   JTextField end;
   
   /** The cancel, submit buttons */
   JButton submit, cancel;
   
   /** The model. */
   DataModel model;
   
   /** The text. */
   JTextArea text;
   
   /**
    * Instantiates a new agenda frame.
    *
    * @param model the model
    * @param text the text
    */
   public AgendaFrame(DataModel model, JTextArea text) {
	  this.model = model;
	  this.text = text;

	  startDate = new JLabel();
      startDate.setText("Start Date (E.g. 4/15/20)");
      startDate.setBounds(0,10,10,10);
      start = new JTextField(15);
      
      endDate = new JLabel();
      endDate.setBounds(0,10,10,10);
      endDate.setText("End Date (E.g. 4/23/20)");
      end = new JTextField(15);
      
      // Submit
      submit = new JButton("SUBMIT");
      submit.setSize(4,4);
      panel = new JPanel(new GridLayout(5, 3));
      panel.add(startDate);
      panel.add(start);
      panel.add(endDate);
      panel.add(end);
      panel.add(submit);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      // Adding the listeners to components..
      submit.addActionListener(this);
      add(panel, BorderLayout.CENTER);
      setTitle("Agenda Frame");
      setSize(300,300);
      setVisible(true);
      setResizable(false);
   }
   
   /**
    * Action to get the events for the Agenda
    *
    * @param ae the action event
    */
   @Override
   public void actionPerformed(ActionEvent ae) {
	  LocalDate startDate = LocalDate.parse(start.getText(), Event.DATEFORMATTER);
	  LocalDate endDate = LocalDate.parse(end.getText(), Event.DATEFORMATTER);
      //get the format of the string and manipulate it
      if(!hasError(startDate,endDate)){
          text.setText(model.getAgendaEvents(startDate, endDate));
          this.setVisible(false);
      }
      else {
    	  JOptionPane.showMessageDialog(new JFrame(),"There is a date conflict with your start time or end time","Warning",JOptionPane.ERROR_MESSAGE);
    	  this.setVisible(false);
    	  
      }
   }
   
   /**
    * Checks for error.
    *
    * @param s the start date
    * @param e the end date
    * @return true, if start date is after the end date
    */
   public boolean hasError(LocalDate s,LocalDate e){
        return s.isAfter(e);
   }
}