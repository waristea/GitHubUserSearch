package githubusersearch;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.Box;
import javax.swing.ImageIcon;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author William
 */
public class UserPanel {
    JPanel userPanel = new JPanel();

    JButton userImage = new JButton("");
    JButton username = new JButton("");
    JButton description = new JButton("");

    /**
     *
     * @param user
     */
    public UserPanel(User user){
        username.setText(user.getLogin());
        userImage.setIcon(user.getUserImageIcon());
        //description.setText();
        
        
        userPanel.setLayout(new GridBagLayout());
        //userPanel.setMaximumSize(new Dimension(300,300));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5,5,5,5);
        //userPanel.add(Box.createVerticalGlue(),gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 2;
        userPanel.add(userImage, gbc);

        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 1;
        gbc.gridy = 0;
        userPanel.add(username, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        userPanel.add(description, gbc);
        userPanel.setBackground(Color.WHITE);
    }
    
    /**
     *
     * @return
     */
    protected JPanel getUserPanel(){
            return userPanel;
    }
    
    // Setter

    /**
     *
     * @param userImageIcon
     */
    protected void setUserImage(ImageIcon userImageIcon){
        userImage.setIcon(userImageIcon);
    }
    
    // Getter

    /**
     *
     * @return
     */
    protected ImageIcon getUserImage(){
        return (ImageIcon) userImage.getIcon();
    }
    
}
