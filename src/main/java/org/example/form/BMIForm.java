package org.example.form;

import org.example.form.common.DatabaseManager;
import org.example.form.common.BaseForm;

import javax.swing.*;
import java.awt.*;

public class BMIForm extends BaseForm {
    private JTextField weightField;
    private JTextField heightField;
    private JButton calculateButton;
    private JButton logoutButton;
    private JLabel resultLabel;
    private String userEmail;

    public BMIForm(String email) {
        super("Kalkulator BMI");

        this.userEmail = email;
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel welcomeLabel = new JLabel("Witaj, " + email + "! Miło Cię widzieć.");
        applyLabelStyle(welcomeLabel);
        arrangeComponent(gbc, welcomeLabel, 0, 0, GridBagConstraints.CENTER, 2, 1, GridBagConstraints.HORIZONTAL);
        Font defaultFont = welcomeLabel.getFont();
        welcomeLabel.setFont(defaultFont.deriveFont(defaultFont.getStyle(), 20));
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(welcomeLabel, gbc);

        JLabel weightLabel = new JLabel("Waga (kg):");
        applyLabelStyle(weightLabel);
        arrangeComponent(gbc, weightLabel, 0, 1, GridBagConstraints.CENTER, 1, 1, GridBagConstraints.HORIZONTAL);
        add(weightLabel, gbc);

        weightField = new JTextField(20);
        arrangeComponent(gbc, weightField, 1, 1, GridBagConstraints.CENTER, 1, 1, GridBagConstraints.HORIZONTAL);
        add(weightField, gbc);

        JLabel heightLabel = new JLabel("Wzrost (cm):");
        applyLabelStyle(heightLabel);
        arrangeComponent(gbc, heightLabel, 0, 2, GridBagConstraints.CENTER, 1, 1, GridBagConstraints.HORIZONTAL);
        add(heightLabel, gbc);

        heightField = new JTextField(20);
        arrangeComponent(gbc, heightField, 1, 2, GridBagConstraints.CENTER, 1, 1, GridBagConstraints.HORIZONTAL);
        add(heightField, gbc);

        calculateButton = new JButton("Oblicz BMI");
        applyButtonStyle(calculateButton);
        arrangeComponent(gbc, calculateButton, 0, 3, GridBagConstraints.CENTER, 2, 1, GridBagConstraints.HORIZONTAL);
        add(calculateButton, gbc);

        JButton historyButton = new JButton("Historia BMI");
        applyButtonStyle(historyButton);
        arrangeComponent(gbc, historyButton, 0, 4, GridBagConstraints.CENTER, 2, 1, GridBagConstraints.HORIZONTAL);
        add(historyButton, gbc);

        logoutButton = new JButton("Wyloguj");
        applyButtonStyle(logoutButton);
        arrangeComponent(gbc, logoutButton, 0, 5, GridBagConstraints.CENTER, 2, 1, GridBagConstraints.HORIZONTAL);
        add(logoutButton, gbc);

        resultLabel = new JLabel("");
        applyLabelStyle(resultLabel);
        arrangeComponent(gbc, resultLabel, 0, 6, GridBagConstraints.CENTER, 2, 1, GridBagConstraints.HORIZONTAL);
        add(resultLabel, gbc);

        calculateButton.addActionListener(e -> calculateBMI());
        logoutButton.addActionListener(e -> logout());
        historyButton.addActionListener(e -> openBMIHistoryForm());
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