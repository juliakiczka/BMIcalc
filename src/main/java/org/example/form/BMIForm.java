package org.example.form;

import org.example.DatabaseManager;
import org.example.FormStyler;

import javax.swing.*;
import java.awt.*;

public class BMIForm extends JFrame {
    private JTextField weightField;
    private JTextField heightField;
    private JButton calculateButton;
    private JButton logoutButton;
    private JLabel resultLabel;
    private String userEmail;

    public BMIForm(String email) {
        this.userEmail = email;

        setTitle("Kalkulator BMI");
        FormStyler.applyStyle(this);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel welcomeLabel = new JLabel("Witaj, " + email);
        FormStyler.arrangeComponent(gbc, 0, 0, GridBagConstraints.CENTER, 2, 1, GridBagConstraints.HORIZONTAL);
        FormStyler.styleLabel(welcomeLabel);
        add(welcomeLabel, gbc);

        JLabel weightLabel = new JLabel("Waga (kg):");
        FormStyler.arrangeComponent(gbc, 0, 1, GridBagConstraints.CENTER, 1, 1, GridBagConstraints.HORIZONTAL);
        FormStyler.styleLabel(weightLabel);
        add(weightLabel, gbc);

        weightField = new JTextField(20);
        FormStyler.arrangeComponent(gbc, 1, 1, GridBagConstraints.CENTER, 1, 1, GridBagConstraints.HORIZONTAL);
        add(weightField, gbc);

        JLabel heightLabel = new JLabel("Wzrost (cm):");
        FormStyler.arrangeComponent(gbc, 0, 2, GridBagConstraints.CENTER, 1, 1, GridBagConstraints.HORIZONTAL);
        FormStyler.styleLabel(heightLabel);
        add(heightLabel, gbc);

        heightField = new JTextField(20);
        FormStyler.arrangeComponent(gbc, 1, 2, GridBagConstraints.CENTER, 1, 1, GridBagConstraints.HORIZONTAL);
        add(heightField, gbc);

        calculateButton = new JButton("Oblicz BMI");
        FormStyler.arrangeComponent(gbc, 0, 3, GridBagConstraints.CENTER, 1, 1, GridBagConstraints.HORIZONTAL);
        FormStyler.styleButton(calculateButton);
        add(calculateButton, gbc);

        JButton historyButton = new JButton("Historia BMI");
        FormStyler.styleButton(historyButton);
        historyButton.setBounds(20, 230, 120, 30);
        add(historyButton);

        historyButton.addActionListener(e -> openBMIHistoryForm());

        logoutButton = new JButton("Wyloguj");
        FormStyler.arrangeComponent(gbc, 1, 3, GridBagConstraints.CENTER, 1, 1, GridBagConstraints.HORIZONTAL);
        FormStyler.styleButton(logoutButton);
        add(logoutButton, gbc);

        resultLabel = new JLabel("");
        FormStyler.arrangeComponent(gbc, 0, 4, GridBagConstraints.CENTER, 2, 1, GridBagConstraints.HORIZONTAL);
        FormStyler.styleLabel(resultLabel);
        add(resultLabel, gbc);

        calculateButton.addActionListener(e -> calculateBMI());
        logoutButton.addActionListener(e -> logout());
    }

    private void calculateBMI() {
        try {
            double weight = Double.parseDouble(weightField.getText());
            double height = Double.parseDouble(heightField.getText()) / 100;
            double bmi = weight / (height * height);

            resultLabel.setText("Twoje BMI: " + String.format("%.2f", bmi) + " (" + getBMICategory(bmi) + ")");

            DatabaseManager.saveBMIResult(userEmail, weight, height * 100, bmi);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Proszę wprowadzić prawidłowe dane.");
        }
    }

    private String getBMICategory(double bmi) {
        if (bmi < 18.5) return "Niedowaga";
        else if (bmi < 24.9) return "Waga prawidłowa";
        else if (bmi < 29.9) return "Nadwaga";
        else return "Otyłość";
    }

    private void logout() {
        LoginForm loginForm = new LoginForm();
        loginForm.setVisible(true);
        dispose();
    }

    private void openBMIHistoryForm() {
        BMIHistoryForm historyForm = new BMIHistoryForm(userEmail);
        historyForm.setVisible(true);
        dispose();
    }

}