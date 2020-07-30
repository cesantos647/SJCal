/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;

import javax.swing.*;

import app.DataModel;
import app.Event;
public class AgendaFrame extends JFrame implements ActionListener {
   JPanel panel;
   JLabel startDate, endDate;
   JTextField start;
   JTextField end;
   JButton submit, cancel;
   DataModel model;
   JTextArea text;
   public AgendaFrame(DataModel model, JTextArea text) {
	  this.model = model;
	  this.text = text;

	  startDate = new JLabel();
      startDate.setText("Start Date (M/d/yy)");
      startDate.setBounds(0,10,10,10);
      start = new JTextField(15);
      
      endDate = new JLabel();
      endDate.setBounds(0,10,10,10);
      endDate.setText("End Date (M/d/yy)");
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
   
   public boolean hasError(LocalDate s,LocalDate e){
       //TODO: somebody can fill the logic of hasError
        return s.isAfter(e);
   }
}