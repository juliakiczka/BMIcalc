package org.example.form;

import org.example.form.common.BMIHistoryEntry;
import org.example.form.common.BaseForm;
import org.example.form.common.DatabaseManager;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class BMIHistoryForm extends BaseForm {
    private String userEmail;

    public BMIHistoryForm(String email) {
        super("Historia BMI");

        this.userEmail = email;
        setLayout(new BorderLayout());

        List<BMIHistoryEntry> history = DatabaseManager.getBMIHistory(userEmail);

        String[] columnNames = {"Waga (kg)", "Wzrost (cm)", "BMI", "Data"};
        Object[][] data = new Object[history.size()][4];

        for (int i = 0; i < history.size(); i++) {
            BMIHistoryEntry entry = history.get(i);
            data[i][0] = entry.getWeight();
            data[i][1] = entry.getHeight();
            data[i][2] = entry.getBmi();
            data[i][3] = entry.getDate();
        }

        JTable historyTable = new JTable(data, columnNames);
        applyTableStyle(historyTable);

        JScrollPane scrollPane = new JScrollPane(historyTable);
        add(scrollPane, BorderLayout.CENTER);

        JButton backButton = new JButton("Powrót");
        applyButtonStyle(backButton);
        backButton.addActionListener(e -> goBackToBMIForm());
        add(backButton, BorderLayout.SOUTH);

        setSize(500, 400);
        setLocationRelativeTo(null);
    }

    private void goBackToBMIForm() {
        BMIForm bmiForm = new BMIForm(userEmail);
        bmiForm.setVisible(true);
        dispose();
    }
}