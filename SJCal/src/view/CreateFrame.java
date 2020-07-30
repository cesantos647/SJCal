/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;
//import app.DataModel

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class CreateFrame extends JFrame implements ActionListener {
   JPanel panel;
   JLabel StartTime, EndTime,EventName,Date;
   JTextField start;
   JTextField end;
   JTextField name;
   JTextField date;
   JButton submit, cancel;
   //DataModel model;
   JTextField text;
   CreateFrame(JTextField text) {
      
      //this.model=model;
      this.text=text;
      
      EventName=new JLabel();
      EventName.setText("Name");
      EventName.setBounds(0,10,10,10);
      name=new JTextField(15);
      
      StartTime = new JLabel();
      StartTime.setText("Start Time");
      StartTime.setBounds(0,10,10,10);
      start = new JTextField(15);
      
      // Password Label
      EndTime = new JLabel();
      EndTime.setBounds(0,10,10,10);
      EndTime.setText("End Time");
      end = new JTextField(15);
      
      Date = new JLabel();
      Date.setBounds(0,10,10,10);
      Date.setText("Date");
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
      new CreateFrame(new JTextField());
   }
   @Override
   public void actionPerformed(ActionEvent ae) {
      String sTime = start.getText();
      String eTime = end.getText();
      String title= name.getText();
      String d=date.getText();
      //get the format of the string and manipulate it
      if(hasError(sTime,eTime,title,d)){
          JOptionPane.showMessageDialog(new JFrame(),"Wrong format or Time conflict","Warning",JOptionPane.ERROR_MESSAGE);
      }
   }
   
   public boolean hasError(String startTime,String emdTime,String title,String date){
       //TODO: somebody can fill the logic of hasError
        return true;
   }
}