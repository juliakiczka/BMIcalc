package org.example.form;

import org.example.form.common.BaseForm;
import org.example.form.common.DatabaseManager;

import javax.swing.*;
import java.awt.*;

public class RegisterForm extends BaseForm {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton registerButton;

    public RegisterForm() {
        super("Rejestracja");

        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel emailLabel = new JLabel("Email:");
        applyLabelStyle(emailLabel);  // Stylizacja etykiety
        arrangeComponent(gbc, emailLabel, 0, 0, GridBagConstraints.CENTER, 1, 1, GridBagConstraints.HORIZONTAL);
        add(emailLabel, gbc);

        emailField = new JTextField(20);
        arrangeComponent(gbc, emailField, 1, 0, GridBagConstraints.CENTER, 1, 1, GridBagConstraints.HORIZONTAL);
        add(emailField, gbc);

        JLabel passwordLabel = new JLabel("Hasło:");
        applyLabelStyle(passwordLabel);  // Stylizacja etykiety
        arrangeComponent(gbc, passwordLabel, 0, 1, GridBagConstraints.CENTER, 1, 1, GridBagConstraints.HORIZONTAL);
        add(passwordLabel, gbc);

        passwordField = new JPasswordField(20);
        arrangeComponent(gbc, passwordField, 1, 1, GridBagConstraints.CENTER, 1, 1, GridBagConstraints.HORIZONTAL);
        add(passwordField, gbc);

        registerButton = new JButton("Zarejestruj");
        applyButtonStyle(registerButton);
        arrangeComponent(gbc, registerButton, 0, 2, GridBagConstraints.CENTER, 2, 1, GridBagConstraints.HORIZONTAL);
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

    private void arrangeComponent(GridBagConstraints gbc, Component component, int gridx, int gridy, int anchor, int gridwidth, int gridheight, int fill) {
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.anchor = anchor;
        gbc.gridwidth = gridwidth;
        gbc.gridheight = gridheight;
        gbc.fill = fill;
        add(component, gbc);
    }
}
