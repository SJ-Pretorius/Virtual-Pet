package za.co.sjpret.virtualpet.gui;

import za.co.sjpret.virtualpet.controller.Controller;
import za.co.sjpret.virtualpet.pet.Pet;

import javax.swing.*;

public class Gui {
    private final Pet pet;
    private JPanel mainPanel;
    private JButton feed;
    private JButton exit;
    private JPanel menuBarPanel;
    private JLabel name;
    private JLabel age;
    private JLabel health;
    private JLabel food;

    public Gui(Pet pet, Controller controller) {
        this.pet = pet;
        JMenuBar menuBar = new JMenuBar();
        JMenu about = new JMenu("About");
        JMenu add = new JMenu("Add");
        JMenu data = new JMenu("Data");
        JMenuItem saveData = new JMenuItem("Save Data");
        JMenuItem loadData = new JMenuItem("Load Data");
        JMenuItem addPet = new JMenuItem("Add Pet");
        JMenuItem credits = new JMenuItem("Credits");
        data.add(saveData);
        data.add(loadData);
        about.add(credits);
        add.add(addPet);
        menuBar.add(add);
        menuBar.add(data);
        menuBar.add(about);
        menuBarPanel.add(menuBar);
        name.setText("Name: " + pet.getName());
        JFrame frame = new JFrame();
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,300);
        frame.setResizable(false);
        frame.setTitle("Virtual Pet");
        update();
        frame.setVisible(true);
        credits.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Created by SJ Pretorius", "Credits", JOptionPane.PLAIN_MESSAGE));
        feed.addActionListener(e -> pet.incrementFood((byte)10));
        exit.addActionListener(e -> System.exit(0));
        addPet.addActionListener(e -> new Controller());
        saveData.addActionListener(e -> controller.saveData());
        loadData.addActionListener(e -> controller.loadData());
    }

    public void update() {
        age.setText("Age: " + String.valueOf(pet.getAge()));
        health.setText("Health: " + String.valueOf(pet.getHealth()));
        food.setText("Food: " + String.valueOf(pet.getFood()));
    }
}
