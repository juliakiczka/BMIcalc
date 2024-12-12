package org.example.form;

import org.example.form.common.DatabaseManager;
import org.example.form.common.BaseForm;

import javax.swing.*;
import java.awt.*;

public class LoginForm extends BaseForm {

    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton, registerButton;
    private JLabel welcomeLabel;

    public LoginForm() {
        super("Logowanie");

        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        welcomeLabel = new JLabel("Kalkulator BMI");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setForeground(Color.WHITE);

        Font defaultFont = welcomeLabel.getFont();
        welcomeLabel.setFont(defaultFont.deriveFont(Font.BOLD, 30));

        add(welcomeLabel, gbc);

        JLabel emailLabel = new JLabel("Nazwa użytkownika:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        applyLabelStyle(emailLabel);
        add(emailLabel, gbc);

        emailField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(emailField, gbc);

        JLabel passwordLabel = new JLabel("Hasło:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        applyLabelStyle(passwordLabel);
        add(passwordLabel, gbc);

        passwordField = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        add(passwordField, gbc);

        loginButton = new JButton("Zaloguj");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        applyButtonStyle(loginButton);
        add(loginButton, gbc);

        registerButton = new JButton("Rejestracja");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        applyButtonStyle(registerButton);
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
