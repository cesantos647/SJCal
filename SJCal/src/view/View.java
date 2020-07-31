package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import app.DataModel;
import app.EventList;
import app.ViewStyle;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

/**
 * The Class View.
 */
public class View {
    
    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
    	DataModel data = new DataModel();
        JFrame frame = new JFrame();

        JPanel appViewPanel = new JPanel();
        JPanel mainViewPanel = new JPanel();
        JPanel leftViewPanel = new JPanel();
        JPanel rightViewPanel = new JPanel();
        JPanel leftPanel = new JPanel();
        leftPanel.setBorder(new EmptyBorder(20,100,20,100));
        rightViewPanel.setBorder(new EmptyBorder(20, 0,20,0));
        mainViewPanel.setBorder(new EmptyBorder(20, 0,20,0));

        mainViewPanel.setLayout(new BoxLayout(mainViewPanel, BoxLayout.Y_AXIS));

        JTextArea area=new JTextArea(20,30);
        area.setText(data.getDayEvents(data.getReference()));
        area.setEditable(false);
        JScrollPane scrollPane = new JScrollPane( area );

        mainViewPanel.add(new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 3)){{
            add(createButtonStyleChange(area, "Day", ViewStyle.DAY, data));
            add(createButtonStyleChange(area, "Week", ViewStyle.WEEK, data));
            add(createButtonStyleChange(area, "Month", ViewStyle.MONTH, data));
            add(new JButton("Agenda"){{
                addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                    	data.setViewStyle(ViewStyle.AGENDA);
                    	AgendaFrame agenda = new AgendaFrame(data, area);
                    }
                });
            }});
            add(new JButton("From file"){{
                addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        JFileChooser chooser = new JFileChooser();
                        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                                "Text Files Only", "txt");
                        chooser.setFileFilter(filter);
                        int returnVal = chooser.showOpenDialog(null);
                        if(returnVal == JFileChooser.APPROVE_OPTION) {
                            String absFilePath = chooser.getSelectedFile().getAbsolutePath();
                            System.out.println("You chose to open this file: " +
                                    absFilePath);
                            //controller handling calling to data model
                            boolean works = data.populateEvents(absFilePath);
                            if(works) {
                            	//Display message
                            }
                            else {
                            	//Display error message
                            }
                        }
                    }
                });
            }});
        }});
        mainViewPanel.add(scrollPane, BorderLayout.PAGE_START);

        //leftViewPanel.setLayout(new BoxLayout(leftViewPanel, BoxLayout.Y_AXIS));
        //leftViewPanel.setLayout();
        leftViewPanel.add(new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 3)){{
        	add(createButtonChangeReferenceDate(area, "Today", 0, data));
            add(createButtonChangeReferenceDate(area, "<", -1, data));
            add(createButtonChangeReferenceDate(area, ">", 1, data));
            add(new JButton("Create"){{
                addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                    	CreateFrame create = new CreateFrame(data, area);
                    }
                });
            }});
        }});
        
        leftPanel.setLayout(new BorderLayout());
        ViewingCalendar vc = new ViewingCalendar(data, area);
        leftPanel.add(vc, BorderLayout.CENTER);
        leftPanel.add(leftViewPanel, BorderLayout.PAGE_START);
        rightViewPanel.setLayout(new BoxLayout(rightViewPanel, BoxLayout.Y_AXIS));
        rightViewPanel.add(new JPanel(new FlowLayout(FlowLayout.CENTER)){{
            //add(createButtonWithEventListener(area, "From File Button Clicked!", "From File"));
        	add(createButtonAdditionalFeature("One Time Event Info", data.getOneTimeEvents()));
        	add(createButtonAdditionalFeature("Recurring Event Info", data.getRecurringEvents()));
        }});

        appViewPanel.setLayout(new BorderLayout());
        //appViewPanel.add(leftViewPanel, BorderLayout.WEST);
        appViewPanel.add(leftPanel, BorderLayout.WEST);
        appViewPanel.add(mainViewPanel, BorderLayout.CENTER);
        appViewPanel.add(rightViewPanel, BorderLayout.EAST);

        frame.add(appViewPanel);
        //frame.setSize(800,600);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     * Creates the button style change.
     *
     * @param componentToUpdate the component to update
     * @param labelName the label name
     * @param style the style
     * @param data the data
     * @return the j button
     */
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
    
    /**
     * Creates the button change reference date.
     *
     * @param componentToUpdate the component to update
     * @param labelName the label name
     * @param change the change
     * @param data the data
     * @return the j button
     */
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
    
    /**
     * Creates the button additional feature.
     *
     * @param labelName the label name
     * @param el the el
     * @return the j button
     */
    public static JButton createButtonAdditionalFeature(String labelName, EventList el) {
    	return new JButton(labelName) {{
    		addMouseListener(new MouseAdapter() {
    			@Override
    			public void mouseClicked(MouseEvent e) {
    				JOptionPane.showMessageDialog(new JFrame(),el.getData(),"Events Information",JOptionPane.INFORMATION_MESSAGE);
    			}
    		});
    	}};
    }
}
