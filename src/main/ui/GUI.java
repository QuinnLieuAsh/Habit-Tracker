package ui;

import javax.swing.*;

import model.Habit;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// A GUI for the HabitTracker
public class GUI {
    private Controller controller;
    private DefaultListModel<Habit> todoListModel;
    private JPanel habitsPanel;
    private JPanel namePanel;
    private JPanel progressPanel;
    private JPanel goalPanel;
    private JLabel nameLabel;
    private JLabel progressLabel;
    private JLabel goalLabel;


    public GUI(Controller controller) {
        this.controller = controller;
    

        //BackGround
        JFrame appFrame = new JFrame("Habits");
        appFrame.setSize(500, 500);
        appFrame.setResizable(false);
        appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        appFrame.setLayout(null);

        //Scrollable List of Habits
        habitsPanel = new JPanel();
        
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
        nameLabel = new JLabel("Habit:");
        nameLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);

        progressLabel = new JLabel("Progress:");
        progressLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);

        goalLabel = new JLabel("Goal:");
        goalLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        infoPanel.setBackground(Color.lightGray);
        infoPanel.setBounds(280, 10, 200,250);
        infoPanel.add(nameLabel);
        namePanel = new JPanel();
        namePanel.setPreferredSize(new Dimension(180, 55));
        namePanel.setBackground(Color.gray);
        infoPanel.add(namePanel);
        
        infoPanel.add(progressLabel);
        progressPanel = new JPanel();
        progressPanel.setPreferredSize(new Dimension(180, 55));
        progressPanel.setBackground(Color.gray);
        infoPanel.add(progressPanel);
    
        infoPanel.add(goalLabel);
        goalPanel = new JPanel();
        goalPanel.setPreferredSize(new Dimension(180, 55));
        goalPanel.setBackground(Color.gray);
        infoPanel.add(goalPanel);
       
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

        appFrame.add(scrollPane, BorderLayout.NORTH);

        newHabButton.addActionListener(e -> createHabit());
        recordButton.addActionListener(e -> recordHab());

        updateTodoList();

        appFrame.setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: Updates the list of todo habits displayed.
    public void updateTodoList() {
        habitsPanel.removeAll();

        ArrayList<Habit> habs = controller.getTodo();

        for (Habit h : habs) {
            JButton habitButton = new JButton(h.getTitle());
            habitButton.setPreferredSize(new Dimension(100,100));
            habitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    controller.select(h);
                    JLabel name = new JLabel(h.getTitle());
                    JLabel prog = new JLabel(String.valueOf(h.getProgress()));
                    JLabel goal = new JLabel(String.valueOf(h.getLongGoal()));

                    namePanel.removeAll();
                    progressPanel.removeAll();
                    goalPanel.removeAll();  

                    namePanel.add(name);
                    progressPanel.add(prog);
                    goalPanel.add(goal); 

                    nameLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
                    progressLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
                    goalLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);  

                    namePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
                    progressPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
                    goalPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

                    namePanel.revalidate();
                    namePanel.repaint();
                    progressPanel.revalidate();
                    progressPanel.repaint();
                    goalPanel.revalidate();
                    goalPanel.repaint();
                }
                
            });

            habitsPanel.add(habitButton);
        }
        habitsPanel.revalidate();
        habitsPanel.repaint();

    }


    private void createHabit() {
        String name = JOptionPane.showInputDialog("Enter habit name:");
        if (name != null && !name.trim().isEmpty()) {
            String goalStr = JOptionPane.showInputDialog("Enter goal count:");
            String reward = JOptionPane.showInputDialog("Enter reward message:");
            try {
                int goal = Integer.parseInt(goalStr);
                controller.addHabit(name, goal, reward);
                updateTodoList();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid goal number.");
            }
        }
    }

    //EFFECTS: increments progress by one
    private void recordHab() {
        if (controller.getSelected() != null) {
            controller.recordHab();
            updateTodoList();
        }
    }

}
