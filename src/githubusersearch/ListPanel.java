/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package githubusersearch;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author William
 */
public class ListPanel implements ActionListener{	
    JFrame listFrame = new JFrame();
    JPanel listPanel = new JPanel();
    //ArrayList<JPanel> panelList = new ArrayList<>();
    JScrollPane scrollPane;

    public ListPanel(){
        listPanel.setLayout(new GridBagLayout());
        listPanel.setBackground(new Color(238, 238, 238));
        scrollPane = new JScrollPane(listPanel);

    }

    protected JScrollPane getListPane(){
        return scrollPane;
    }

    protected void addPanel(JPanel addedPanel){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(0,0,0,0);

        gbc.gridwidth = 1;
        gbc.gridheight = 2;
        listPanel.add(addedPanel,gbc);
    }

    public void actionPerformed(ActionEvent e) {

    }

}
