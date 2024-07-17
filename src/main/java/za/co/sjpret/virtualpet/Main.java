package za.co.sjpret.virtualpet;

import za.co.sjpret.virtualpet.controller.Controller;

import java.io.IOException;
import java.net.ServerSocket;

public class Main {
    public static void main(String[] args) {
        try (ServerSocket socket = new ServerSocket(9999)) {
            System.out.println("Application is running.");
            new Controller();
        } catch (IOException e) {
            System.out.println("Another instance is running.");
            System.exit(1);
        }
    }
}