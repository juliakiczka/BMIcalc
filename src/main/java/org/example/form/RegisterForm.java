package org.example.form;

import org.example.DatabaseManager;
import org.example.FormStyler;

import javax.swing.*;
import java.awt.*;

public class RegisterForm extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton registerButton;

    public RegisterForm() {
        setTitle("Rejestracja");
        FormStyler.applyStyle(this);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel emailLabel = new JLabel("Email:");
        FormStyler.arrangeComponent(gbc, 0, 0, GridBagConstraints.CENTER, 1, 1, GridBagConstraints.HORIZONTAL);
        FormStyler.styleLabel(emailLabel);
        add(emailLabel, gbc);

        emailField = new JTextField(20);
        FormStyler.arrangeComponent(gbc, 1, 0, GridBagConstraints.CENTER, 1, 1, GridBagConstraints.HORIZONTAL);
        add(emailField, gbc);

        JLabel passwordLabel = new JLabel("Hasło:");
        FormStyler.arrangeComponent(gbc, 0, 1, GridBagConstraints.CENTER, 1, 1, GridBagConstraints.HORIZONTAL);
        FormStyler.styleLabel(passwordLabel);
        add(passwordLabel, gbc);

        passwordField = new JPasswordField(20);
        FormStyler.arrangeComponent(gbc, 1, 1, GridBagConstraints.CENTER, 1, 1, GridBagConstraints.HORIZONTAL);
        add(passwordField, gbc);

        registerButton = new JButton("Zarejestruj");
        FormStyler.arrangeComponent(gbc, 0, 2, GridBagConstraints.CENTER, 2, 1, GridBagConstraints.HORIZONTAL);
        FormStyler.styleButton(registerButton);
        add(registerButton, gbc);

        registerButton.addActionListener(e -> register());
    }

    private void register() {
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());

        if (DatabaseManager.registerUser(email, password)) {
            JOptionPane.showMessageDialog(this, "Rejestracja zakończona sukcesem!");
            new LoginForm().setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Błąd rejestracji.");
        }
    }
}