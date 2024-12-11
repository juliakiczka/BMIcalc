package org.example.form;

import org.example.BMIHistoryEntry;
import org.example.DatabaseManager;
import org.example.FormStyler;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class BMIHistoryForm extends JFrame {
    private String userEmail;

    public BMIHistoryForm(String email) {
        this.userEmail = email;
        setTitle("Historia BMI");
        FormStyler.applyStyle(this);
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
        FormStyler.applyTableStyle(historyTable);

        JScrollPane scrollPane = new JScrollPane(historyTable);
        add(scrollPane, BorderLayout.CENTER);

        JButton closeButton = new JButton("Zamknij");
        FormStyler.styleButton(closeButton);
        closeButton.addActionListener(e -> dispose());
        add(closeButton, BorderLayout.SOUTH);

        setSize(500, 400);
        setLocationRelativeTo(null);
    }
}
