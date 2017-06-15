package githubusersearch;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Header implements ActionListener, KeyListener, MouseListener{	

    JPanel headerPanel = new JPanel();
    JFrame headerFrame = new JFrame();
    JButton advancedSearchButton;
    JTextField searchBox;
    Image gitHubImage;
    String searched = "";

    public Header(){
        // JButton - GitHubLogo
        JLabel gitHubImageLabel = new JLabel();

        try{
            String imageName = System.getProperty("user.dir")+"\\src\\githubusersearch\\github.png";
            gitHubImage = ImageIO.read(new File(imageName));
            gitHubImage = gitHubImage.getScaledInstance(115, 35, Image.SCALE_SMOOTH);
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }

        gitHubImageLabel.setIcon(new ImageIcon(gitHubImage));
        gitHubImageLabel.setSize(new Dimension(50, 10));

        // JTextField - Search Field
        searchBox = new JTextField(10);
        searchBox.setBackground(new Color(63, 68, 72));
        searchBox.setForeground(Color.WHITE);
        searchBox.setCaretColor(Color.WHITE);
        searchBox.addKeyListener(this);
        searchBox.setBorder(
                BorderFactory.createCompoundBorder(
                        new CustomBorder(), 
                        new EmptyBorder(
                                new Insets(10, 20, 10, 20))));

        // JButton - Search Button

        advancedSearchButton = new JButton("Advanced Search");
        advancedSearchButton.setForeground(Color.WHITE);
        advancedSearchButton.setOpaque(false);
        advancedSearchButton.setContentAreaFilled(false);
        advancedSearchButton.setBorderPainted(false);
        advancedSearchButton.addMouseListener(this);

        // Placement
        headerPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.insets = new Insets(1,1,1,5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        headerPanel.add(gitHubImageLabel, gbc);

        gbc.insets = new Insets(1,1,1,1);

        gbc.gridx = 1;
        gbc.gridy = 0;
        headerPanel.add(searchBox, gbc);


        gbc.gridx = 2;
        gbc.gridy = 0;
        headerPanel.add(advancedSearchButton, gbc);

        headerPanel.setBackground(new Color(36, 41, 46));
        headerPanel.setVisible(true);
    }

    protected JPanel getHeaderPanel(){
        return headerPanel;
    }

    // ActionListener - Advanced Search
    public void actionPerformed(ActionEvent e) {

    }

    // KeyListener - Enter as a submit method
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_ENTER){
            System.out.println(searchBox.getText());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    // MouseListener
    @Override
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        advancedSearchButton.setForeground(Color.GRAY);
        advancedSearchButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        advancedSearchButton.setForeground(Color.WHITE);
        advancedSearchButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }
}
