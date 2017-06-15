package githubusersearch;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.*;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

public class CombinedLayout extends JFrame{
        
    public CombinedLayout(ArrayList<User> userList){
        Container paneOut = getContentPane();

        JPanel paneIn = new JPanel();
        paneIn.setBorder(BorderFactory.createLineBorder(new Color(36, 41, 46)));
        paneIn.setLayout(new BorderLayout());

        // ListPanel
        ListPanel listPanelTemp = new ListPanel(); // Panel pembungkus semua list user
        listPanelTemp.addPanel(new UserPanel(userList.get(0)).getUserPanel());
        JScrollPane listPanel = listPanelTemp.getListPane(); // Berisi informasi user
        listPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));

        paneIn.add(listPanel, BorderLayout.CENTER);

        // Header
        Header header = new Header();
        JPanel headerPanel = header.getHeaderPanel();

        paneIn.add(headerPanel, BorderLayout.NORTH);

        // Footer
        Footer footer = new Footer();
        JPanel footerPanel = footer.getFooterPanel();

        paneIn.add(footerPanel, BorderLayout.SOUTH);

        /*
        // Gutter
        Gutter gutterEast = new Gutter();
        JPanel gutterPanelEast = gutterEast.getGutterPanel();

        Gutter gutterWest = new Gutter();
        JPanel gutterPanelWest = gutterWest.getGutterPanel();

        paneIn.add(gutterPanelEast, BorderLayout.EAST);
        paneIn.add(gutterPanelWest, BorderLayout.WEST);
        */
        paneOut.add(paneIn);
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.setVisible(true);
    }
}