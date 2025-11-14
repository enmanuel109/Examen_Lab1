package examen_lab1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;

public class BaseGUI extends JFrame {

    //font default
    private final Font baseFont = new Font("SansSerif", Font.PLAIN, 14);

    public BaseGUI(String title, int width, int height) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);
        setLocationRelativeTo(null);

    }

    protected JButton createBtn(String text) {
        JButton b = new JButton(text);

        b.setFont(baseFont.deriveFont(Font.BOLD, 14F));
        b.setBackground(new Color(33, 150, 243));
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        b.setBorder(new EmptyBorder(10, 16, 10, 16));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.setOpaque(true);

        return b;
    }

    protected JTextField createTextField(int x, int y, int w, int h) {
        JTextField tf = new JTextField();
        tf.setFont(baseFont.deriveFont(14f));
        tf.setBounds(x, y, w, h);
        tf.setBorder(new CompoundBorder(
                new LineBorder(new Color(220, 220, 220), 1, true),
                new EmptyBorder(10, 12, 10, 12)
        ));
        return tf;
    }

    protected JLabel createLabel(String txt, int x, int y, int w, int h) {
        JLabel l = new JLabel(txt);
        l.setFont(l.getFont().deriveFont(Font.BOLD, 24f));
        l.setBounds(x, y, w, h);
        return l;
    }

    protected JLabel createLabelTitle(String txt, int x, int y, int w, int h) {
        JLabel l = new JLabel(txt);
        l.setFont(l.getFont().deriveFont(Font.BOLD, 32f));
        l.setBounds(x, y, w, h);
        return l;
    }

    protected JPanel createPanelPrincipal() {
        JPanel p = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();

                GradientPaint gp = new GradientPaint(0, 0, new Color(245, 248, 255), getWidth(), getHeight(),
                        new Color(225, 235, 250)
                );

                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());
                g2.dispose();
            }
        };
        return p;
    }

    /*
    padding: EmptyBorder(top, left, bottom, right);
    otra cosa:
    s = isSelected
    f = hasFocus
     */
    protected JScrollPane createTable(String[] columnas, Object[][] datos, int rowHeight) {
        JTable tbl = new JTable(datos, columnas);
        Color primary = new Color(33, 150, 243);

        tbl.setShowHorizontalLines(false);
        tbl.setShowVerticalLines(false);
        tbl.setRowHeight(rowHeight);

        tbl.getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable t, Object v, boolean s, boolean f, int r, int c) {
                super.getTableCellRendererComponent(t, v, s, f, r, c);
                setFont(baseFont.deriveFont(Font.BOLD, 14f));
                setForeground(Color.WHITE);
                setBackground(primary);
                setBorder(new EmptyBorder(10, 12, 10, 12));
                setHorizontalAlignment(SwingConstants.CENTER);
                setOpaque(true);
                return this;
            }
        });

        tbl.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable t, Object v, boolean s, boolean f, int r, int c) {
                super.getTableCellRendererComponent(t, v, s, f, r, c);
                setOpaque(false);
                setBorder(new EmptyBorder(8, 12, 8, 12));
                return this;
            }
        });

        tbl.setIntercellSpacing(new Dimension(0, 0));
        JScrollPane scroll = new JScrollPane(tbl);
        scroll.setBorder(null);
        scroll.setViewportBorder(null);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);

        return scroll;
    }

}
