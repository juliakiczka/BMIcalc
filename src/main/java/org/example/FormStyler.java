package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class FormStyler {

    public static final Color INITIAL_BACKGROUND_COLOR = new Color(238, 174, 202, 100);
    private static final Color TARGET_BACKGROUND_COLOR = new Color(148, 187, 233, 100);


    public static void applyStyle(JFrame frame) {
        frame.setSize(500, 500);
        centerWindow(frame);
        frame.setLayout(null);
        frame.getContentPane().setBackground(INITIAL_BACKGROUND_COLOR);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        animateBackgroundColor(frame);
    }

    public static void applyColor(JTable frame) {
//        frame.setBackground(INITIAL_BACKGROUND_COLOR);
//        animateBackgroundColor(frame);
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

    private static void centerWindow(JFrame frame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - frame.getWidth()) / 2;
        int y = (screenSize.height - frame.getHeight()) / 2;
        frame.setLocation(x, y);
    }

    private static void animateBackgroundColor(JFrame frame) {
        Timer timer = new Timer(10, new AbstractAction() {
            private float progress = 0.0f;
            private Color initialColor = INITIAL_BACKGROUND_COLOR;
            private Color targetColor = TARGET_BACKGROUND_COLOR;

            @Override
            public void actionPerformed(ActionEvent e) {
                int red = (int) (initialColor.getRed() + (targetColor.getRed() - initialColor.getRed()) * progress);
                int green = (int) (initialColor.getGreen() + (targetColor.getGreen() - initialColor.getGreen()) * progress);
                int blue = (int) (initialColor.getBlue() + (targetColor.getBlue() - initialColor.getBlue()) * progress);

                frame.getContentPane().setBackground(new Color(red, green, blue));
                progress += 0.01f;

                if (progress >= 1.0f) {
                    progress = 0.0f;
                    Color temp = initialColor;
                    initialColor = targetColor;
                    targetColor = temp;
                }
            }
        });
        timer.start();
    }
}