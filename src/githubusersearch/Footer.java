/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package githubusersearch;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author William
 */
public class Footer implements ActionListener{	
    JPanel footerPanel = new JPanel();
    JFrame footerFrame = new JFrame();

    public Footer(){

        footerPanel.setBackground(new Color(36, 41, 46));
        footerPanel.setVisible(true);
    }

    protected JPanel getFooterPanel(){
        return footerPanel;
    }


    public void actionPerformed(ActionEvent e) {

    }	
}
