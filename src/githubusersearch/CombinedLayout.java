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

/**
 *
 * @author William
 */
public class CombinedLayout extends JFrame{
    Container paneOut;
    JPanel paneIn;
    Header header;
    
    /**
     *
     */
    public CombinedLayout(){
        paneOut = getContentPane();
        paneIn = new JPanel();
        header = new Header();
        
        JPanel headerPanel = header.getHeaderPanel();
        paneIn.add(headerPanel, BorderLayout.NORTH);
        
        paneOut.add(paneIn);
        
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.setVisible(true);
    }
    
    // Setter

    /**
     *
     * @param userList
     */
    public void setSearchedList(ArrayList<User> userList){
        SearchedList searchResult = new SearchedList(); // Panel pembungkus semua list user
        for(int i=0; i<=userList.size()-1; ++i){
            searchResult.addPanel(new UserPanel(userList.get(i)).getUserPanel());
        }
        
        JScrollPane listPanel = searchResult.getListPane(); // Berisi informasi user
        listPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        
        resetPaneOut();
        resetPaneIn();
        paneIn.add(listPanel, BorderLayout.CENTER);
        paneOut.add(paneIn);
    }
    
    /**
     *
     */
    public void resetPaneIn(){
        paneIn = new JPanel();
        
        paneIn.setBorder(BorderFactory.createLineBorder(new Color(36, 41, 46)));
        paneIn.setLayout(new BorderLayout());
        
        JPanel headerPanel = header.getHeaderPanel();
        paneIn.add(headerPanel, BorderLayout.NORTH);
        
    }
    
    /**
     *
     */
    public void resetPaneOut(){
        paneOut.removeAll();
    }
    
    /**
     *
     * @param addedPanel
     * @param layout
     */
    public void addPaneIn(JPanel addedPanel, int layout){
        resetPaneOut();
        paneIn.add(addedPanel, layout);
        paneOut.add(paneIn);
    }
    
    // Getter

    /**
     *
     * @return
     */
    public Container getPaneOut(){
        return paneOut;
    }
    
    /**
     *
     * @return
     */
    protected String getSearched(){
        return header.getSearched();
    }
}