package githubusersearch;

import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author William
 */
public class Gutter {
    JPanel gutterPanel = new JPanel();
    JLabel testing = new JLabel();
    
    /**
     *
     */
    public Gutter(){
        //gutterPanel.setBackground(Color.WHITE);
        gutterPanel.setMaximumSize(new Dimension(80,150));
        gutterPanel.add(testing);
        gutterPanel.setBackground(new Color(233, 235, 238));
        gutterPanel.setVisible(true);
    }

    /**
     *
     * @return
     */
    protected JPanel getGutterPanel(){
        return gutterPanel;
    }
    
}
