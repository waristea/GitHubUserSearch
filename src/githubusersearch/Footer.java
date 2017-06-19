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

    /**
     *
     */
    public Footer(){

        footerPanel.setBackground(new Color(36, 41, 46));
        footerPanel.setVisible(true);
    }

    /**
     *
     * @return
     */
    protected JPanel getFooterPanel(){
        return footerPanel;
    }


    public void actionPerformed(ActionEvent e) {

    }	
}
