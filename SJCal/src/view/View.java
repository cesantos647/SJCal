package view;

import javax.swing.*;

import app.DataModel;
import app.ViewStyle;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class View {
    public static void main(String[] args) {
    	DataModel data = new DataModel();
        JFrame frame = new JFrame();

        JPanel appViewPanel = new JPanel();
        JPanel mainViewPanel = new JPanel();
        JPanel leftViewPanel = new JPanel();
        JPanel rightViewPanel = new JPanel();

        mainViewPanel.setLayout(new BoxLayout(mainViewPanel, BoxLayout.Y_AXIS));

        JTextArea area=new JTextArea(20,30);
        JScrollPane scrollPane = new JScrollPane( area );

        mainViewPanel.add(new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 3)){{
            add(createButtonStyleChange(area, "Day", ViewStyle.DAY, data));
            add(createButtonStyleChange(area, "Week", ViewStyle.WEEK, data));
            add(createButtonStyleChange(area, "Month", ViewStyle.MONTH, data));
            add(createButtonStyleChange(area, "Agenda", ViewStyle.MONTH, data));
        }});
        mainViewPanel.add(scrollPane, BorderLayout.PAGE_START);

        leftViewPanel.setLayout(new BoxLayout(leftViewPanel, BoxLayout.Y_AXIS));
        leftViewPanel.add(new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 3)){{
        	add(createButtonChangeReferenceDate(area, "Today", 0, data));
            add(createButtonChangeReferenceDate(area, "<", -1, data));
            add(createButtonChangeReferenceDate(area, ">", 1, data));
        }});
        //leftViewPanel.add(createButtonWithEventListener(area, "CREATE Button Clicked!", "CREATE"));

        rightViewPanel.setLayout(new BoxLayout(rightViewPanel, BoxLayout.Y_AXIS));
        rightViewPanel.add(new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 3)){{
            //add(createButtonWithEventListener(area, "From File Button Clicked!", "From File"));
        }});

        appViewPanel.setLayout(new FlowLayout());
        appViewPanel.add(leftViewPanel);
        appViewPanel.add(mainViewPanel);
        appViewPanel.add(rightViewPanel);

        frame.add(appViewPanel);
        frame.setSize(1000,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static JButton createButtonStyleChange(JTextArea componentToUpdate, String labelName,
                                                        ViewStyle style, DataModel data){
        return new JButton(labelName){{
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                	data.setViewStyle(style);
                	String valueToUpdate = data.getEvents(data.getReference());
                    componentToUpdate.setText(valueToUpdate);
                }
            });
        }};
    }
    
    public static JButton createButtonChangeReferenceDate(JTextArea componentToUpdate, String labelName, 
    														int change, DataModel data) {
    	return new JButton(labelName){{
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                	data.changeReference(change);
                	String valueToUpdate = data.getEvents(data.getReference());
                    componentToUpdate.setText(valueToUpdate);
                }
            });
        }};
    }
}
