package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class View {
    public static void main(String[] args) {
        JFrame frame = new JFrame();

        JPanel appViewPanel = new JPanel();
        JPanel mainViewPanel = new JPanel();
        JPanel leftViewPanel = new JPanel();
        JPanel rightViewPanel = new JPanel();

        mainViewPanel.setLayout(new BoxLayout(mainViewPanel, BoxLayout.Y_AXIS));

        JTextArea area=new JTextArea(20,30);
        JScrollPane scrollPane = new JScrollPane( area );

        mainViewPanel.add(new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 3)){{
            add(createButtonWithEventListener(area, "Day Button Clicked!", "Day"));
            add(createButtonWithEventListener(area, "Week Button Clicked!", "Week"));
            add(createButtonWithEventListener(area, "Month Button Clicked!", "Month"));
            add(createButtonWithEventListener(area, "Agenda Button Clicked!", "Agenda"));
        }});
        mainViewPanel.add(scrollPane, BorderLayout.PAGE_START);

        leftViewPanel.setLayout(new BoxLayout(leftViewPanel, BoxLayout.Y_AXIS));
        leftViewPanel.add(new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 3)){{
            add(createButtonWithEventListener(area, "Today Button Clicked!", "Today"));
            add(createButtonWithEventListener(area, "< Button Clicked!", "<"));
            add(createButtonWithEventListener(area, "> Button Clicked!", ">"));
        }});
        leftViewPanel.add(createButtonWithEventListener(area, "CREATE Button Clicked!", "CREATE"));

        rightViewPanel.setLayout(new BoxLayout(rightViewPanel, BoxLayout.Y_AXIS));
        rightViewPanel.add(new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 3)){{
            add(createButtonWithEventListener(area, "From File Button Clicked!", "From File"));
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

    public static JButton createButtonWithEventListener(JTextArea componentToUpdate,
                                                        String valueToUpdate, String labelName){
        return new JButton(labelName){{
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    componentToUpdate.setText(valueToUpdate);
                }
            });
        }};
    }
}
