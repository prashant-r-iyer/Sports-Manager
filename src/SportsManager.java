import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SportsManager {

    // FIXME: just fix it
    
    public static void UI() {
        JFrame frame = new JFrame("Introduction to Sports Manager");
        String introToSportsManager = "Welcome to the SportsManager utility";
        JLabel intro = new JLabel(introToSportsManager);
        JPanel panel = new JPanel();
        intro.setBounds(50, 50, 300, 50);
        panel.add(intro);
        JButton button = new JButton("Click here");
        button.setBounds(50, 200, 100, 50);
        panel.add(button);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
            }
        });
        frame.add(panel);

        frame.setSize(2560, 1440);
        frame.setLayout(new FlowLayout());
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    

    }
}
