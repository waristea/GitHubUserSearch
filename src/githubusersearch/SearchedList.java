package githubusersearch;

import java.awt.Color;
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
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.PAGE_AXIS));
        
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
        /*
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(0,0,0,0);

        gbc.gridwidth = 1;
        gbc.gridheight = 2;
        
        gbc.gridx = 0;
        System.out.println(listPanel.getComponentCount());
        gbc.gridy = listPanel.getComponentCount();
        */
        listPanel.add(addedPanel);
        scrollPane = new JScrollPane(listPanel);
    }

    public void actionPerformed(ActionEvent e) {

    }

}
