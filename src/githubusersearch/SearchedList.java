package githubusersearch;

import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author William
 */
public class SearchedList implements ActionListener{	
    JFrame listFrame = new JFrame();
    JPanel listPanel = new JPanel();
    JScrollPane scrollPane;

    /**
     *
     */
    public SearchedList(){
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        
        listPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        listPanel.setBackground(new Color(238, 238, 238));
        scrollPane = new JScrollPane(listPanel);
        listPanel.setPreferredSize(new Dimension(400, 500));
    }
    
    /**
     *
     * @return
     */
    protected JScrollPane getListPane(){
        return scrollPane;
    }

    /**
     *
     * @param addedPanel
     */
    protected void addPanel(JPanel addedPanel){
        listPanel.add(addedPanel);
        scrollPane = new JScrollPane(listPanel);
    }

    public void actionPerformed(ActionEvent e) {

    }

}
