package org.example.form;

import org.example.DatabaseManager;
import org.example.FormStyler;

import javax.swing.*;
import java.awt.*;

public class LoginForm extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton, registerButton;

    public LoginForm() {
        setTitle("Logowanie");
        FormStyler.applyStyle(this);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel emailLabel = new JLabel("Nazwa użytkownika:");
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

        loginButton = new JButton("Zaloguj");
        FormStyler.arrangeComponent(gbc, 0, 2, GridBagConstraints.CENTER, 2, 1, GridBagConstraints.HORIZONTAL);
        FormStyler.styleButton(loginButton);
        add(loginButton, gbc);

        registerButton = new JButton("Rejestracja");
        FormStyler.arrangeComponent(gbc, 0, 3, GridBagConstraints.CENTER, 2, 1, GridBagConstraints.HORIZONTAL);
        FormStyler.styleButton(registerButton);
        add(registerButton, gbc);

        loginButton.addActionListener(e -> login());
        registerButton.addActionListener(e -> openRegisterForm());
    }

    private void login() {
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());

        if (DatabaseManager.verifyUser(email, password)) {
            BMIForm bmiForm = new BMIForm(email);
            bmiForm.setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Nieprawidłowy email lub hasło");
        }
    }

    private void openRegisterForm() {
        RegisterForm registerForm = new RegisterForm();
        registerForm.setVisible(true);
        dispose();
    }
}