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

public class CombinedLayout extends JFrame{
        
	public CombinedLayout(ArrayList<User> userList){
		Container paneOut = getContentPane();
                        
		JPanel paneIn = new JPanel();
		paneIn.setBorder(BorderFactory.createLineBorder(new Color(36, 41, 46)));
		paneIn.setLayout(new BorderLayout());
		
                // ListPanel
		ListPanel listPanelTemp = new ListPanel(); // Panel pembungkus semua list user
		listPanelTemp.addPanel(new UserPanel(userList.get(0)).getUserPanel());
                JScrollPane listPanel = listPanelTemp.getListPane(); // Berisi informasi user
		listPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		
		paneIn.add(listPanel, BorderLayout.CENTER);
		    
		// Header
		Header header = new Header();
		JPanel headerPanel = header.getHeaderPanel();
		
                paneIn.add(headerPanel, BorderLayout.NORTH);
	
		// Footer
		Footer footer = new Footer();
		JPanel footerPanel = footer.getFooterPanel();
		
		paneIn.add(footerPanel, BorderLayout.SOUTH);
		
                /*
                // Gutter
                Gutter gutterEast = new Gutter();
                JPanel gutterPanelEast = gutterEast.getGutterPanel();
                
                Gutter gutterWest = new Gutter();
                JPanel gutterPanelWest = gutterWest.getGutterPanel();
                
                paneIn.add(gutterPanelEast, BorderLayout.EAST);
                paneIn.add(gutterPanelWest, BorderLayout.WEST);
                */
                paneOut.add(paneIn);
		this.setFocusable(true);
		this.requestFocusInWindow();
		this.setVisible(true);
	}
        
    // Header
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
    // Footer    
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

    // ListPanel
        
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
        
}