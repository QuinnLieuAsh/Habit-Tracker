package ui;

import javax.swing.*;

import model.Habit;

import java.awt.*;

// A GUI for the HabitTracker
public class GUI {
    private Controller controller;
    private DefaultListModel<Habit> habListModel;

    public GUI() {
        
        //BackGround
        JFrame appFrame = new JFrame("Habits");
        appFrame.setSize(500, 500);
        appFrame.setResizable(false);
        appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        appFrame.setLayout(null);

        //Scrollable List of Habits
        JPanel habitsPanel = new JPanel();
        
        habitsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        habitsPanel.setLayout( new FlowLayout(FlowLayout.LEFT));

        JScrollPane scrollPane = new JScrollPane(habitsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(250, 100));
        scrollPane.setBounds(20, 50, 250,150);

        

        //Panel for Habit title
        JLabel titleLabel = new JLabel("TODO:");
        titleLabel.setVerticalAlignment(JLabel.CENTER);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
  
        JPanel titlePanel = new JPanel();
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        titlePanel.setBackground(Color.lightGray);
        titlePanel.setBounds(20, 10, 250,30);
        titlePanel.setLayout(new BorderLayout());
        titlePanel.add(titleLabel);
        appFrame.add(titlePanel);

        //Panel for option buttons 
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonPanel.setBackground(Color.lightGray);
        buttonPanel.setBounds(20, 210, 250,250);
        appFrame.add(buttonPanel);

        //Buttons for modifying habit tracker
        JButton recordButton = new JButton("Record Habit");
        recordButton.setPreferredSize(new Dimension(200,70));
        buttonPanel.add(recordButton);
        JButton viewAllButton = new JButton("View All Habits");
        viewAllButton.setPreferredSize(new Dimension(200,70));
        buttonPanel.add(viewAllButton);
        JButton newHabButton = new JButton("Create New Habit");
        newHabButton.setPreferredSize(new Dimension(200,70));
        buttonPanel.add(newHabButton);

        //Habit panel for information and stats
        JLabel nameLabel = new JLabel("Habit:");
        nameLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);

        JLabel progressLabel = new JLabel("Progress:");
        progressLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);

        JLabel goalLabel = new JLabel("Goal:");
        progressLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        infoPanel.setBackground(Color.lightGray);
        infoPanel.setBounds(280, 10, 200,250);
        infoPanel.add(nameLabel);
        infoPanel.add(Box.createRigidArea(new Dimension(0,55)));
        infoPanel.add(progressLabel);
        infoPanel.add(Box.createRigidArea(new Dimension(0,55)));
        infoPanel.add(goalLabel);
        infoPanel.add(Box.createRigidArea(new Dimension(0,55)));
        appFrame.add(infoPanel);

        //Panel for visual component
        ImageIcon linkGif = new ImageIcon("src/main/ui/link.gif");
        Image scaledImage = linkGif.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT); // Adjust width and height
        ImageIcon scaledGif = new ImageIcon(scaledImage);
        JLabel linkLabel = new JLabel(scaledGif); 

        JPanel graphicPanel = new JPanel();
        graphicPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        graphicPanel.setBackground(Color.lightGray);
        graphicPanel.setBounds(280, 270, 200,190);
        graphicPanel.setLayout(new BorderLayout());
        graphicPanel.add(linkLabel, BorderLayout.CENTER);
        appFrame.add(graphicPanel);


        

        // //Example Habit Buttons
        // JButton exampleButton = new JButton("Hab1");
        // exampleButton.setPreferredSize(new Dimension(100,100));
        // JButton exampleButton2 = new JButton("Hab2");
        // exampleButton2.setPreferredSize(new Dimension(100,100));
        // JButton exampleButton3 = new JButton("Hab3");
        // exampleButton3.setPreferredSize(new Dimension(100,100));
        // JButton exampleButton4 = new JButton("Hab4");
        // exampleButton4.setPreferredSize(new Dimension(100,100));

        // JButton exampleButton5 = new JButton("Hab5");
        // exampleButton5.setPreferredSize(new Dimension(100,100));

        // JButton exampleButton6 = new JButton("Hab6");
        // exampleButton6.setPreferredSize(new Dimension(100,100));

        // JButton exampleButton7 = new JButton("Hab7");
        // exampleButton7.setPreferredSize(new Dimension(100,100));

        // JButton exampleButton8 = new JButton("Hab8");
        // exampleButton8.setPreferredSize(new Dimension(100,100));
        // habitsPanel.add(exampleButton);
        // habitsPanel.add(exampleButton2);
        // habitsPanel.add(exampleButton3);
        // habitsPanel.add(exampleButton4);
        // habitsPanel.add(exampleButton5);
        // habitsPanel.add(exampleButton6);
        // habitsPanel.add(exampleButton7);
        // habitsPanel.add(exampleButton8);

        appFrame.add(scrollPane, BorderLayout.NORTH);
        appFrame.setVisible(true);
    }

}
