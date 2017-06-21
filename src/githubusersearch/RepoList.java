package githubusersearch;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author William
 */
public class RepoList extends JFrame{
    Container paneOut;
    JPanel repoListPanel = new JPanel();
    JScrollPane scrollPane;
    
    public RepoList(ArrayList<Repository> repos){
        paneOut = getContentPane();
        
        repoListPanel.setLayout(new BoxLayout(repoListPanel, BoxLayout.PAGE_AXIS));
        repoListPanel.setBackground(new Color(238, 238, 238));
        repoListPanel.setPreferredSize(new Dimension(400, 500));
        
        for(int i=0; i<repos.size();++i){
            System.out.println(repos.get(i).getName());
            
            JLabel repoName = new JLabel(repos.get(i).getName());
            JLabel repoHtmlUrl = new JLabel(repos.get(i).getHtmlURL());
            JLabel repoDescription = new JLabel(repos.get(i).getDescription());
            
            repoName.setFont(new Font("Arial", Font.BOLD, 20));
            repoName.setPreferredSize(new Dimension(200, 200));
            
            repoListPanel.add(repoName);
            repoListPanel.add(repoHtmlUrl);
            repoListPanel.add(repoDescription);
        }
        
        scrollPane = new JScrollPane(repoListPanel);
        
        paneOut.add(scrollPane);
        
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.setVisible(true);
    }
}
