package za.co.sjpret.virtualpet.gui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import za.co.sjpret.virtualpet.controller.MainController;
import za.co.sjpret.virtualpet.controller.PetController;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

//TODO Picture of a kitten
//TODO Windows Notification if window is closed or option for save and exit or minimise.
//TODO View on Desktop
//TODO Clean this up

public class MainGui {
    private JFrame frame;
    private JPanel mainPanel;
    private JButton feed;
    private JButton exit;
    private JPanel menuBarPanel;
    private JLabel name;
    private JLabel age;
    private JLabel health;
    private JLabel food;
    private JLabel animalStatus;
    private final PetController petController;

    public MainGui(PetController petController) {
        this.petController = petController;
        setupGUI();
    }

    private void setupGUI() {
        JMenuBar menuBar = new JMenuBar();
        JMenu about = new JMenu("About");
        JMenu manage = new JMenu("Manage");
        JMenu data = new JMenu("Data");
        JMenu view = new JMenu("View");
        JMenuItem viewOnDesktop = new JMenuItem("View On Desktop");
        JMenuItem saveData = new JMenuItem("Save Data");
        JMenuItem newPet = new JMenuItem("Add Pet");
        JMenuItem deletePet = new JMenuItem("Kill Pet");
        JMenuItem credits = new JMenuItem("Credits");
        data.add(saveData);
        about.add(credits);
        manage.add(newPet);
        manage.add(deletePet);
        view.add(viewOnDesktop);
        menuBar.add(manage);
        menuBar.add(view);
        menuBar.add(data);
        menuBar.add(about);
        menuBarPanel.add(menuBar);
        name.setText("Name: " + petController.accessPet().getName());
        frame = new JFrame();
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setResizable(false);
        frame.setTitle("Virtual Pet: " + petController.accessPet().getName());
        frame.setLocationRelativeTo(null);
        update();
        frame.setVisible(true);

        //ActionListeners
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                windowCloseMessage();
            }
        });
        credits.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Created by SJ Pretorius", "Credits", JOptionPane.PLAIN_MESSAGE));
        feed.addActionListener(e -> {
            petController.accessPet().incrementFood((byte) 10);
            update();
        });
        exit.addActionListener(e -> {
            MainController.saveData();
            System.exit(0);
        });
        newPet.addActionListener(e -> MainController.createNewPet());
        deletePet.addActionListener(e -> {
            petController.accessPet().kill();
            update();
        });
        saveData.addActionListener(e -> MainController.saveData());
    }

    public void update() {
        age.setText("Age: " + String.valueOf(petController.accessPet().getAge()));
        health.setText("Health: " + String.valueOf(petController.accessPet().getHealth()));
        food.setText("Food: " + String.valueOf(petController.accessPet().getFood()));
        animalStatus.setText("Status: " + petController.accessPet().getAnimalStatus());
    }

    public void showGui() {
        frame.setVisible(true);
    }

    //TODO Frame disposeGUI?
    public void disposeGUI() {
        frame.dispose();
    }

    private void windowCloseMessage() {
        Object[] options = {"Minimise", "Save and Exit"};

        int option = JOptionPane.showOptionDialog(
                frame,
                "Would you like to save and exit or minimise?",
                "Virtual Pet",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]  // Default selection
        );

        if (option == JOptionPane.YES_OPTION) {
            frame.setVisible(false);
            petController.accessWindowsNotification().showNotification("Use the tray icon to access your pet at any time.");
        } else if (option == JOptionPane.NO_OPTION) {
            MainController.saveData();
            System.exit(0);
        }
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(0, 0));
        mainPanel.setEnabled(true);
        menuBarPanel = new JPanel();
        menuBarPanel.setLayout(new BorderLayout(0, 0));
        mainPanel.add(menuBarPanel, BorderLayout.NORTH);
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new BorderLayout(0, 0));
        mainPanel.add(panel1, BorderLayout.CENTER);
        panel1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 4, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel2, BorderLayout.NORTH);
        name = new JLabel();
        name.setText("Label");
        panel2.add(name, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        age = new JLabel();
        age.setText("Label");
        panel2.add(age, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        health = new JLabel();
        health.setText("Label");
        panel2.add(health, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        food = new JLabel();
        food.setText("Label");
        panel2.add(food, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel3, BorderLayout.CENTER);
        final JLabel label1 = new JLabel();
        label1.setText("<Picture of Virtual Pet>");
        panel3.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new BorderLayout(0, 0));
        panel1.add(panel4, BorderLayout.SOUTH);
        feed = new JButton();
        feed.setText("Feed");
        panel4.add(feed, BorderLayout.WEST);
        exit = new JButton();
        exit.setText("Save and Exit");
        panel4.add(exit, BorderLayout.EAST);
        animalStatus = new JLabel();
        animalStatus.setHorizontalAlignment(0);
        animalStatus.setText("Status: <Animal Status>");
        panel4.add(animalStatus, BorderLayout.CENTER);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

}
