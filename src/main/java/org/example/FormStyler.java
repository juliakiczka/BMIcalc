package org.example;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;

public class FormStyler {

    public static final Color INITIAL_BACKGROUND_COLOR = new Color(238, 174, 202, 100);
    public static final Color TARGET_BACKGROUND_COLOR = new Color(148, 187, 233, 100);


    public static void applyStyle(JFrame frame) {
        frame.setSize(500, 500);
        centerWindow(frame);
        frame.setLayout(new BorderLayout());

        JComponent contentPane = (JComponent) frame.getContentPane();
        contentPane.setBackground(INITIAL_BACKGROUND_COLOR);
        contentPane.setOpaque(true);

        animateBackgroundColor(contentPane);

        for (Component component : contentPane.getComponents()) {
            if (component instanceof JTable) {
                JTable table = (JTable) component;
                applyTableStyle(table);
            } else if (component instanceof JScrollPane) {
                JScrollPane scrollPane = (JScrollPane) component;
                scrollPane.getViewport().setBackground(INITIAL_BACKGROUND_COLOR);
                scrollPane.setBackground(INITIAL_BACKGROUND_COLOR);
                animateBackgroundColor(scrollPane);
            } else if (component instanceof JComponent) {
                component.setBackground(INITIAL_BACKGROUND_COLOR);
                animateBackgroundColor((JComponent) component);
            }
        }
    }

    public static void styleButton(JButton button) {
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(0, 0, 0, 0));
        button.setOpaque(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
    }

    public static void styleLabel(JLabel label) {
        label.setForeground(Color.WHITE);
    }

    public static void center(GridBagConstraints gbc, int gridx, int gridy, int anchor) {
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.anchor = anchor;
    }

    public static void arrangeComponent(GridBagConstraints gbc, int gridx, int gridy, int anchor, int gridwidth, int gridheight, int fill) {
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.anchor = anchor;
        gbc.gridwidth = gridwidth;
        gbc.gridheight = gridheight;
        gbc.fill = fill;
    }

    public static void applyTableStyle(JTable table) {
        table.setForeground(Color.DARK_GRAY);
        table.setGridColor(Color.LIGHT_GRAY);
        table.setRowHeight(25);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setOpaque(false);

        JTableHeader header = table.getTableHeader();
        header.setBackground(INITIAL_BACKGROUND_COLOR);
        header.setForeground(Color.DARK_GRAY);
        header.setFont(new Font("Arial", Font.BOLD, 14));
        animateBackgroundColor(header);

        Container parent = table.getParent();
        if (parent instanceof JViewport) {
            JViewport viewport = (JViewport) parent;
            viewport.setOpaque(false);
            viewport.setBackground(INITIAL_BACKGROUND_COLOR);

            JScrollPane scrollPane = (JScrollPane) viewport.getParent();
            if (scrollPane != null) {
                scrollPane.setOpaque(false);
                scrollPane.getViewport().setOpaque(false);
                scrollPane.setBackground(INITIAL_BACKGROUND_COLOR);
                animateBackgroundColor(scrollPane);
            }
        }
        animateBackgroundColor(table);
    }

    private static void centerWindow(JFrame frame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - frame.getWidth()) / 2;
        int y = (screenSize.height - frame.getHeight()) / 2;
        frame.setLocation(x, y);
    }

    private static void animateBackgroundColor(JComponent component) {
        Timer timer = new Timer(10, new AbstractAction() {
            private float progress = 0.0f;
            private Color initialColor = INITIAL_BACKGROUND_COLOR;
            private Color targetColor = TARGET_BACKGROUND_COLOR;

            @Override
            public void actionPerformed(ActionEvent e) {
                int red = (int) (initialColor.getRed() + (targetColor.getRed() - initialColor.getRed()) * progress);
                int green = (int) (initialColor.getGreen() + (targetColor.getGreen() - initialColor.getGreen()) * progress);
                int blue = (int) (initialColor.getBlue() + (targetColor.getBlue() - initialColor.getBlue()) * progress);

                component.setBackground(new Color(red, green, blue));
                progress += 0.01f;

                if (progress >= 1.0f) {
                    progress = 0.0f;
                    Color temp = initialColor;
                    initialColor = targetColor;
                    targetColor = temp;
                }
                component.repaint();
            }
        });
        timer.start();
    }
}