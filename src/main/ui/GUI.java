package ui;

import javax.swing.*;

import model.Habit;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
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
    private JLabel xpGifLabel;
    private JPanel graphicPanel;
    private JLabel xpStaticLabel;
    private JPanel buttonPanel;
    private JButton recordButton;
    private JButton viewAllButton;
    private JButton newHabButton;
    private JButton returnButton;
    private JButton removeButton;
    private JLabel todoLabel;
    private JLabel allHabLabel;
    private JPanel titlePanel;
    private JLabel rewardLabel;

    public GUI(Controller controller) {
        this.controller = controller;

        controller.dailyReset();
        loadDataOption();

        // BackGround
        JFrame appFrame = new JFrame("Habits");
        appFrame.setSize(500, 500);
        appFrame.setResizable(false);
        appFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        appFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                saveDataOption();
                System.exit(0);
            }
        });
        appFrame.setLayout(null);

        // Scrollable List of Habits
        habitsPanel = new JPanel();

        habitsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        habitsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JScrollPane scrollPane = new JScrollPane(habitsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(250, 100));
        scrollPane.setBounds(20, 50, 250, 150);

        // Panel for Habit title
        todoLabel = new JLabel("TODO:");
        todoLabel.setVerticalAlignment(JLabel.CENTER);
        todoLabel.setHorizontalAlignment(JLabel.CENTER);

        allHabLabel = new JLabel("All Habits:");
        allHabLabel.setVerticalAlignment(JLabel.CENTER);
        allHabLabel.setHorizontalAlignment(JLabel.CENTER);

        titlePanel = new JPanel();
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        titlePanel.setBackground(Color.lightGray);
        titlePanel.setBounds(20, 10, 250, 30);
        titlePanel.setLayout(new BorderLayout());
        titlePanel.add(todoLabel);
        appFrame.add(titlePanel);

        // Panel for option buttons
        buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonPanel.setBackground(Color.lightGray);
        buttonPanel.setBounds(20, 210, 250, 250);
        appFrame.add(buttonPanel);

        // Buttons for modifying habit tracker
        recordButton = new JButton("Record Habit");
        recordButton.setPreferredSize(new Dimension(200, 70));
        buttonPanel.add(recordButton);
        viewAllButton = new JButton("View All Habits");
        viewAllButton.setPreferredSize(new Dimension(200, 70));
        buttonPanel.add(viewAllButton);
        newHabButton = new JButton("Create New Habit");
        newHabButton.setPreferredSize(new Dimension(200, 70));
        buttonPanel.add(newHabButton);

        returnButton = new JButton("Return");
        returnButton.setPreferredSize(new Dimension(200, 70));
        removeButton = new JButton("Remove Habit");
        removeButton.setPreferredSize(new Dimension(200, 70));

        // Habit panel for information and stats
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
        infoPanel.setBounds(280, 10, 200, 250);
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

        // LINK IMAGE
        ImageIcon linkGif = new ImageIcon("src/main/ui/link.gif");
        Image scaledImage = linkGif.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon scaledGif = new ImageIcon(scaledImage);
        JLabel linkLabel = new JLabel(scaledGif);

        // TREASURE IMAGE
        ImageIcon xpGif = new ImageIcon("src/main/ui/xp-plus.gif");
        Image scaledXP = xpGif.getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT);
        ImageIcon smallXP = new ImageIcon(scaledXP);
        xpGifLabel = new JLabel(smallXP);

        ImageIcon xpStatic = new ImageIcon("src/main/ui/xp_static.png");
        Image scaledStatic = xpStatic.getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT);
        ImageIcon smallStatic = new ImageIcon(scaledStatic);
        xpStaticLabel = new JLabel(smallStatic);

        // Panel for visual components
        graphicPanel = new JPanel();
        graphicPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        graphicPanel.setBackground(Color.WHITE);
        graphicPanel.setBounds(280, 270, 200, 190);
        graphicPanel.setLayout(new BorderLayout());
        graphicPanel.add(linkLabel, BorderLayout.EAST);
        graphicPanel.add(xpStaticLabel, BorderLayout.WEST);
        appFrame.add(graphicPanel);
        appFrame.add(scrollPane, BorderLayout.NORTH);

        newHabButton.addActionListener(e -> createHabit());
        viewAllButton.addActionListener(e -> viewAllHabits());
        recordButton.addActionListener(e -> recordHab());
        returnButton.addActionListener(e -> viewTodo());
        removeButton.addActionListener(e -> removeHabit());

        updateTodoList();

        appFrame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: Updates the list to all habits, and shows new button panel options.
    public void viewAllHabits() {
        refreshButtonsForViewAll();
        updateAllHabits();
    }

    // MODIFIES: this
    // EFFECTS: Updates the list to all habits, and shows new button panel options.
    public void viewTodo() {
        refreshButtonsForTodo();
        updateTodoList();
    }

    // MODIFIES: this
    // EFFECTS: shows new button panel options for allhabits list.
    public void refreshButtonsForViewAll() {
        buttonPanel.removeAll();
        buttonPanel.add(returnButton);
        buttonPanel.add(removeButton);
        buttonPanel.revalidate();
        buttonPanel.repaint();
    }

    // MODIFIES: this
    // EFFECTS: shows new button panel options for allhabits list.
    public void refreshButtonsForTodo() {
        buttonPanel.removeAll();
        buttonPanel.add(recordButton);
        buttonPanel.add(viewAllButton);
        buttonPanel.add(newHabButton);
        buttonPanel.revalidate();
        buttonPanel.repaint();
    }

    // MODIFIES: this
    // EFFECTS: Updates the list to all habits displayed.
    public void updateAllHabits() {
        habitsPanel.removeAll();

        ArrayList<Habit> habs = controller.getAllHabits();

        for (Habit h : habs) {
            JButton habitButton = new JButton(h.getTitle());
            habitButton.setPreferredSize(new Dimension(100, 100));
            habitButton.addActionListener(e -> showStats(h));

            habitsPanel.add(habitButton);
        }
        habitsPanel.revalidate();
        habitsPanel.repaint();

        titlePanel.removeAll();
        titlePanel.add(allHabLabel);
        titlePanel.revalidate();
        titlePanel.repaint();
    }

    // MODIFIES: this
    // EFFECTS: Updates the list of todo habits displayed.
    public void updateTodoList() {
        habitsPanel.removeAll();

        ArrayList<Habit> habs = controller.getTodo();

        for (Habit h : habs) {
            JButton habitButton = new JButton(h.getTitle());
            habitButton.setPreferredSize(new Dimension(100, 100));
            habitButton.addActionListener(e -> showStats(h));

            habitsPanel.add(habitButton);
        }
        habitsPanel.revalidate();
        habitsPanel.repaint();

        titlePanel.removeAll();
        titlePanel.add(todoLabel);
        titlePanel.revalidate();
        titlePanel.repaint();

    }

    // MODIFIES: this
    // EFFECTS: user creates a new habit
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

    // MODIFIES: this
    // EFFECTS: user removes selected habit
    public void removeHabit() {
        controller.removeHabit();
        updateAllHabits();
    }

    // MODIFIES: this
    // EFFECT:Swaps images after animation
    public void swap() {
        graphicPanel.remove(xpGifLabel);
        graphicPanel.add(xpStaticLabel, BorderLayout.WEST);
        graphicPanel.revalidate();
        graphicPanel.repaint();
    }

    // MODIFIES: this
    // EFFECTS: increments progress by one
    private void recordHab() {
        if (controller.getSelected() != null) {
            controller.recordHab();
            JLabel prog = new JLabel(String.valueOf(controller.getSelected().getProgress()));
            progressPanel.removeAll();
            progressPanel.add(prog);
            progressPanel.revalidate();
            progressPanel.repaint();

            graphicPanel.remove(xpStaticLabel);
            graphicPanel.add(xpGifLabel, BorderLayout.WEST);
            graphicPanel.revalidate();
            graphicPanel.repaint();

            Timer timer = new Timer(5000, event -> swap());
            timer.setRepeats(false);
            timer.start();
            graphicPanel.revalidate();
            graphicPanel.repaint();
            checkGoalMet();

            updateTodoList();
        }
    }

    //MODIFIES: this
    //EFFECTS: Displays reward if goal is met
    public void checkGoalMet() {
        if (controller.reward() != null) {
            rewardLabel = new JLabel(controller.reward() + "!");
            graphicPanel.add(rewardLabel, BorderLayout.NORTH);
            graphicPanel.revalidate();
            graphicPanel.repaint();

            Timer timer = new Timer(7000, event -> clearReward());
            timer.setRepeats(false);
            timer.start();
            graphicPanel.revalidate();
            graphicPanel.repaint();
        }
    }

    // MODIFIES: this
    // EFFECTS: removes the reward message from graphics panel
    public void clearReward() {
        graphicPanel.remove(rewardLabel);
        graphicPanel.revalidate();
        graphicPanel.repaint();
    }

    // MODIFIES: this
    // EFFECTS: refreshes the habit stats on the stat panel
    public void showStats(Habit h) {
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

    // MODIFIES: this
    // EFFECTS: allows user to load saved data
    public void loadDataOption() {
        int response = JOptionPane.showConfirmDialog(null,
                "Load previous progress?",
                "Load progress",
                JOptionPane.YES_NO_OPTION);

        if (response == 0) {
            controller.loadProgress();
        }
    }

    // MODIFIES: this
    // EFFECTS: allows user to save current data
    public void saveDataOption() {
        int response = JOptionPane.showConfirmDialog(null,
                "Save you progress?",
                "Save progress",
                JOptionPane.YES_NO_OPTION);

        if (response == JOptionPane.YES_NO_OPTION) {
            controller.saveProgress();
        }
        System.exit(0);
    }

}
