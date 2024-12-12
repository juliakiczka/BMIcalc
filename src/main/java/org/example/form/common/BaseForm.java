package org.example.form.common;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;

public abstract class BaseForm extends JFrame {

    public static final Color INITIAL_BACKGROUND_COLOR = new Color(238, 174, 202, 100);
    public static final Color TARGET_BACKGROUND_COLOR = new Color(148, 187, 233, 100);

    public BaseForm(String title) {
        setTitle(title);
        setSize(500, 500);
        centerWindow();
        setLayout(new BorderLayout());
        JComponent contentPane = (JComponent) getContentPane();
        contentPane.setBackground(INITIAL_BACKGROUND_COLOR);
        contentPane.setOpaque(true);
        animateBackgroundColor(contentPane);
    }

    private void centerWindow() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - getWidth()) / 2;
        int y = (screenSize.height - getHeight()) / 2;
        setLocation(x, y);
    }

    private void animateBackgroundColor(JComponent component) {
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

    public void applyButtonStyle(JButton button) {
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(0, 0, 0, 0));
        button.setOpaque(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
    }

    public void applyLabelStyle(JLabel label) {
        label.setForeground(Color.WHITE);
    }

    public void applyTableStyle(JTable table) {
        Font defaultFont = UIManager.getFont("Label.font");
        Font font = defaultFont.deriveFont(Font.BOLD);
        table.setFont(font);
        table.setForeground(Color.WHITE);
        table.setGridColor(Color.WHITE);
        table.setRowHeight(25);
        table.setOpaque(false);

        JTableHeader header = table.getTableHeader();
        header.setFont(font);
        header.setBackground(INITIAL_BACKGROUND_COLOR);
        header.setForeground(Color.WHITE);
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

    public void arrangeComponent(GridBagConstraints gbc, JComponent component, int gridx, int gridy,
                                 int anchor, int gridwidth, int gridheight, int fill) {
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.anchor = anchor;
        gbc.gridwidth = gridwidth;
        gbc.gridheight = gridheight;
        gbc.fill = fill;
        component.setLayout(null);
    }
}