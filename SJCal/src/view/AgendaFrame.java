/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class AgendaFrame extends JFrame implements ActionListener {
   JPanel panel;
   JLabel startDate, endDate;
   JTextField start;
   JTextField end;
   JButton submit, cancel;
   AgendaFrame() {
      // Username Label
      startDate = new JLabel();
      startDate.setText("Start Date");
      startDate.setBounds(0,10,10,10);
      start = new JTextField(15);
      
      // Password Label
      endDate = new JLabel();
      endDate.setBounds(0,10,10,10);
      endDate.setText("End Date");
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
   public static void main(String[] args) {
      new AgendaFrame();
   }
   @Override
   public void actionPerformed(ActionEvent ae) {
      String StartDate = start.getText();
      String EndDate = start.getText();
      //get the format of the string and manipulate it
      if(hasError(StartDate,EndDate)){
          JOptionPane.showMessageDialog(new JFrame(),"Wrong format or Time conflict","Warning",JOptionPane.ERROR_MESSAGE);
      }
   }
   
   public boolean hasError(String s,String e){
       //TODO: somebody can fill the logic of hasError
        return true;
   }
}