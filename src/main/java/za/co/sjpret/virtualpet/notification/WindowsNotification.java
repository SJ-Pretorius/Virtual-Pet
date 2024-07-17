package za.co.sjpret.virtualpet.notification;

import za.co.sjpret.virtualpet.gui.MainGui;

import java.awt.*;
import java.awt.TrayIcon.MessageType;

public class WindowsNotification {
    private TrayIcon trayIcon;
    private final MainGui gui;

    public WindowsNotification(MainGui gui, String petName) {
        this.gui = gui;
        if (SystemTray.isSupported()) {
            setupTray(petName);
        } else {
            System.err.println("System tray not supported!");
        }
    }
    private void setupTray(String petName) {
        try {
            SystemTray tray = SystemTray.getSystemTray();
            Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
            trayIcon = new TrayIcon(image, "Java Notification");
            trayIcon.setImageAutoSize(true);
            trayIcon.setToolTip("Virtual Pet: " + petName);
            tray.add(trayIcon);
            trayIcon.addActionListener(e -> gui.showGui());
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public void showNotification(String message) {
        // Display the notification
        trayIcon.displayMessage("Virtual Pet", message, MessageType.INFO);
    }
}
