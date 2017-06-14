package githubusersearch;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.Box;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class UserPanel {
    JPanel userPanel = new JPanel();

    JButton userImage = new JButton("userImage");
    JButton username = new JButton("username");
    JButton description = new JButton("description");

    public UserPanel(User user){
        username.setText(user.getLogin());
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
    
    protected JPanel getUserPanel(){
            return userPanel;
    }
}
