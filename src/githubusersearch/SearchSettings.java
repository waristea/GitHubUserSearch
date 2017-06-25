package githubusersearch;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Element;

public class SearchSettings extends JFrame implements ActionListener{
    Container paneOut;
    JPanel searchSettingsPanel = new JPanel();
    static String parameter = "";
    
    // Search on [username = 0, email = 1, name = 2]
    static int searchedField = 0;
    
    // Filter       
    // Number of Repository (Default = none)
    static int repoComparator = 0;//[Off=0, '='=1, '>'=2, '<'=3, '>='=4, '<='=5, range=6']
    final static String[] repoOption = {"-","Equals To", "More Than", "Less Than", "More or Equal To", "Less or Equal To", "Range"};
    static String repoFilterNum = "0";
    static String repoFilterNumRangeMax = "*";
    
    // Number of Followers (Default  = none)
    static int folComparator = 0;//[Off=0, '='=1, '>'=2, '<'=3, '>='=4, '<='=5, range=6]
    final static String[] folOption = {"-","Equals To", "More Than", "Less Than", "More or Equal To", "Less or Equal To", "Range"};
    static String folFilterNum = "0";
    static String folFilterNumRangeMax = "*";
    
    // GUI
    // Search Field
    JRadioButton usernameButton = new JRadioButton("username");
    JRadioButton emailButton = new JRadioButton("email");
    JRadioButton nameButton = new JRadioButton("name");
    
    // Repository                
    JComboBox repoComboBox = new JComboBox(repoOption);
    
    JTextField repoRangeField = new JTextField(repoFilterNum);
    JTextField repoRangeFieldMax = new JTextField(repoFilterNumRangeMax);
    
    // Follower
    JComboBox folComboBox = new JComboBox(folOption);
    
    JTextField folRangeField = new JTextField();
    JTextField folRangeFieldMax = new JTextField();
        

    private MyVerifier verifier = new MyVerifier();    
    
    public SearchSettings(){
        paneOut = getContentPane();
        
        // Radio Panel
        // Create new radio buttons
        usernameButton.setActionCommand("username");
        usernameButton.setSelected(true);
        usernameButton.addActionListener(this);
 
        emailButton.setActionCommand("email");
        emailButton.addActionListener(this);
        
        nameButton.setActionCommand("name");
        nameButton.addActionListener(this);
        
        ButtonGroup radioGroup = new ButtonGroup();
        radioGroup.add(usernameButton);
        radioGroup.add(emailButton);
        radioGroup.add(nameButton);
        
        JPanel radioPanel = new JPanel(new GridLayout(0, 1));
        radioPanel.add(usernameButton);
        radioPanel.add(emailButton);
        radioPanel.add(nameButton);
        
        searchSettingsPanel.add(radioPanel);
        
        // Repo Filter
        repoRangeField.setInputVerifier(verifier);
        //repoRangeField.setActionCommand("repoRangeField");
        repoRangeField.addActionListener(this);
        repoRangeFieldMax.setInputVerifier(verifier);
        //repoRangeFieldMax.setActionCommand("repoRangeFieldMax");
        repoRangeFieldMax.addActionListener(this);
        
        repoRangeField.setEditable(false);
        repoRangeFieldMax.setEditable(false);
        
        repoComboBox.setSelectedIndex(0);
        repoComboBox.setActionCommand("repo");
        repoComboBox.addActionListener(this);

        searchSettingsPanel.add(repoComboBox);
        searchSettingsPanel.add(repoRangeField);
        searchSettingsPanel.add(repoRangeFieldMax);
        
        // Follower Filter
        folRangeField.setInputVerifier(verifier);
        //folRangeField.setActionCommand("folRangeField");
        folRangeField.addActionListener(this);
        folRangeFieldMax.setInputVerifier(verifier);
        //folRangeFieldMax.setActionCommand("folRangeFieldMax");
        folRangeFieldMax.addActionListener(this);
        
        folRangeField.setEditable(false);
        folRangeFieldMax.setEditable(false);
        
        folComboBox.setSelectedIndex(0);
        folComboBox.setActionCommand("fol");
        folComboBox.addActionListener(this);
        
        searchSettingsPanel.add(folComboBox);
        searchSettingsPanel.add(folRangeField);
        searchSettingsPanel.add(folRangeFieldMax);
        
        searchSettingsPanel.setLayout(new BoxLayout(searchSettingsPanel, BoxLayout.PAGE_AXIS));
        searchSettingsPanel.setBackground(new Color(238, 238, 238));
        searchSettingsPanel.setPreferredSize(new Dimension(400, 500));
        
        paneOut.add(searchSettingsPanel);
        
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "username" : {
                searchedField = 0;
                break;
            }
            case "email" : {
                searchedField = 1;
                break;
            }
            case "name" : {
                searchedField = 2;
                break;
            }
            case "repo" : {
                JComboBox comboBox = (JComboBox) e.getSource();
                String newSelection = (String) comboBox.getSelectedItem();
                update("repo", newSelection, repoRangeField, repoRangeFieldMax);
                break;
            }
            case "fol" : {
                JComboBox comboBox = (JComboBox) e.getSource();
                String newSelection = (String) comboBox.getSelectedItem();
                update("fol", newSelection, repoRangeField, repoRangeFieldMax);
                break;
            }
            default : {
                break;
            }
        }
    }
    
    // Updates comparator values based on the member of the comboBox
    public void update(String parameter, String comparator, JTextField range1,JTextField range2){
        switch(parameter){
            case "repo":{
                switch(comparator){
                    case "0":
                    case "-":{
                        repoComparator = 0;
                        range1.setEditable(false);
                        range2.setEditable(false);
                        break;
                    }
                    case "1":
                    case "=":
                    case "Equals to":{
                        repoComparator = 1;
                        range1.setEditable(true);
                        range2.setEditable(false);
                        break;
                    }
                    case "2":
                    case ">":
                    case "More Than":{
                        repoComparator = 2;
                        range1.setEditable(true);
                        range2.setEditable(false);
                        break;
                    }
                    case "3":
                    case "<":
                    case "Less Than":{
                        repoComparator = 3;
                        range1.setEditable(true);
                        range2.setEditable(false);
                        break;
                    }
                    case "4":
                    case ">=":
                    case "More or Equal To":{
                        repoComparator = 4;
                        range1.setEditable(true);
                        range2.setEditable(false);
                        break;
                    }
                    case "5":
                    case "<=":
                    case "Less or Equal To":{
                        repoComparator = 5;
                        range1.setEditable(true);
                        range2.setEditable(false);
                        break;
                    }
                    case "6":
                    case "~":
                    case "Range":{
                        repoComparator = 6;
                        range1.setEditable(true);
                        range2.setEditable(true);
                        break;
                    }
                    
                    default:{
                        break;
                    }
                }
                break;
            }
            case "fol":{
                switch(comparator){
                    case "0":
                    case "-":{
                        folComparator = 0;
                        range1.setEditable(false);
                        range2.setEditable(false);
                        break;
                    }
                    case "1":
                    case "=":
                    case "Equals to":{
                        folComparator = 1;
                        range1.setEditable(true);
                        range2.setEditable(false);
                        break;
                    }
                    case "2":
                    case ">":
                    case "More Than":{
                        folComparator = 2;
                        range1.setEditable(true);
                        range2.setEditable(false);
                        break;
                    }
                    case "3":
                    case "<":
                    case "Less Than":{
                        folComparator = 3;
                        range1.setEditable(true);
                        range2.setEditable(false);
                        break;
                    }
                    case "4":
                    case ">=":
                    case "More or Equal To":{
                        folComparator = 4;
                        range1.setEditable(true);
                        range2.setEditable(false);
                        break;
                    }
                    case "5":
                    case "<=":
                    case "Less or Equal To":{
                        folComparator = 5;
                        range1.setEditable(true);
                        range2.setEditable(false);
                        break;
                    }
                    case "6":
                    case "~":
                    case "Range":{
                        folComparator = 6;
                        range1.setEditable(true);
                        range2.setEditable(true);
                        break;
                    }
                    
                    default:{
                        break;
                    }
                }
                break;
            }
            default:{
                break;
            }
        }
    }
    
    public static String getParameter(){
        String field, paramA, paramB;
        
        field = "%20in:";
        switch (searchedField){
            case 0:{
                field += "login";
                break;
            }
            case 1:{
                field += "email";
                break;
            }
            case 2:{
                field += "fullname";
                break;
            }
            default:{
                field = "";
            }
        }
        
        paramA = "%20repos:";
        switch(repoComparator){
            case 1 :{
                paramA += repoFilterNum;
                break;
            }
            case 2 :{
                paramA += ">"+repoFilterNum;
                break;
            }
            case 3 :{
                paramA += "<"+repoFilterNum;
                break;
            }
            case 4 :{
                paramA += ">="+repoFilterNum;
                break;
            }
            case 5 :{
                paramA += "<="+repoFilterNum;
                break;
            }
            case 6 :{
                paramA += repoFilterNum+".."+repoFilterNumRangeMax;
                break;
            }
            default :{
                paramA = "";
                break;
            }
        }
        
        paramB = "%20followers:";
        switch(folComparator){
            case 1 :{
                paramB += folFilterNum;
                break;
            }
            case 2 :{
                paramB += ">"+folFilterNum;
                break;
            }
            case 3 :{
                paramB += "<"+folFilterNum;
                break;
            }
            case 4 :{
                paramB += ">="+folFilterNum;
                break;
            }
            case 5 :{
                paramB += "<="+folFilterNum;
                break;
            }
            case 6 :{
                paramB += folFilterNum+".."+folFilterNumRangeMax;
                break;
            }
            default :{
                paramB = "";
                break;
            }
        }
        return (field+paramA+paramB);
    }
    
    class MyVerifier extends InputVerifier
            implements ActionListener {
        int DEF = 0;
        int MIN = 0;
        int MAX = 10000;
        
        @Override
        public void actionPerformed(ActionEvent e) {
            JTextField source = (JTextField)e.getSource();
            shouldYieldFocus(source); //ignore return value
            source.selectAll();
        }
        
        public boolean shouldYieldFocus(JComponent input) {
            boolean inputOK = verify(input);
            makeItPretty(input);
            
            if (inputOK) {
                return true;
            } else {
                return false;
            }
        }
        
        //This method checks input, but should cause no side effects.
        public boolean verify(JComponent input) {
            return checkField(input, false);
        }
        
        protected void makeItPretty(JComponent input) {
            checkField(input, true);
        }
        
        protected boolean checkField(JComponent input, boolean changeIt) {
            if (input == repoRangeField) {
                return checkRepoField(changeIt);
            } else if (input == repoRangeFieldMax) {
                return checkRepoFieldMax(changeIt);
            } else if (input == folRangeField) {
                return checkFolField(changeIt);
            } else if (input == folRangeFieldMax) {
                return checkFolFieldMax(changeIt);
            } else {
                return true; //shouldn't happen
            }
        }
        
        protected boolean checkRepoField(boolean change) {
            boolean wasValid = true;
            int num = DEF;
            
            //Parse the value.
            try{
                num = Integer.parseInt(repoRangeField.getText());
                repoFilterNum = String.valueOf(num);
            }catch(NumberFormatException e){
                if(repoRangeField.getText().equals("*")) 
                    wasValid = true;
                else {
                    wasValid = false;
                    System.out.println(e);
                }
                return wasValid;
            }
            
            //Value was invalid.
            if ((num < MIN) || (num > MAX)) {
                wasValid = false;
                if (change) {
                    if (num < MIN) {
                        repoRangeField.setText(String.valueOf(MIN));
                        repoFilterNum = String.valueOf(MIN);
                    } else { // numPeriods is greater than MAX_PERIOD
                        repoRangeField.setText(String.valueOf(MAX));
                        repoFilterNum = String.valueOf(MAX);
                    }
                }
            }
            
            return wasValid;
        }
        protected boolean checkRepoFieldMax(boolean change) {
            boolean wasValid = true;
            int num = DEF;
            
            //Parse the value.
            try{
                num = Integer.parseInt(repoRangeFieldMax.getText());
                repoFilterNumRangeMax = String.valueOf(num);
            }catch(NumberFormatException e){
                if(repoRangeFieldMax.getText().equals("*")) 
                    wasValid = true;
                else {
                    wasValid = false;
                    System.out.println(e);
                }
                return wasValid;
            }
            
            //Value was invalid.
            if ((num < MIN) || (num > MAX)) {
                wasValid = false;
                if (change) {
                    if (num < MIN) {
                        repoRangeFieldMax.setText(String.valueOf(MIN));
                        repoFilterNumRangeMax = String.valueOf(MIN);
                    } else {
                        repoRangeFieldMax.setText(String.valueOf(MAX));
                        repoFilterNumRangeMax = String.valueOf(MAX);
                    }
                }
            }
            return wasValid;
        }
        protected boolean checkFolField(boolean change) {
            boolean wasValid = true;
            int num = DEF;
            
            //Parse the value.
            try{
                num = Integer.parseInt(folRangeField.getText());
                folFilterNum = String.valueOf(num);
            }catch(NumberFormatException e){
                if(folRangeField.getText().equals("*")) 
                    wasValid = true;
                else {
                    wasValid = false;
                    System.out.println(e);
                }
                return wasValid;
            }
            
            //Value was invalid.
            if ((num < MIN) || (num > MAX)) {
                wasValid = false;
                if (change) {
                    if (num < MIN) {
                        folRangeField.setText(String.valueOf(MIN));
                        folFilterNum = String.valueOf(MIN);
                    } else {
                        folRangeField.setText(String.valueOf(MAX));
                        folFilterNum = String.valueOf(MAX);
                    }
                }
            }
            return wasValid;
        }
        protected boolean checkFolFieldMax(boolean change) {
            boolean wasValid = true;
            int num = DEF;
            
            //Parse the value.
            try{
                num = Integer.parseInt(folRangeFieldMax.getText());
                folFilterNumRangeMax = String.valueOf(num);
            }catch(NumberFormatException e){
                if(folRangeFieldMax.getText().equals("*")) 
                    wasValid = true;
                else {
                    wasValid = false;
                    System.out.println(e);
                }
                return wasValid;
            }
            
            //Value was invalid.
            if ((num < MIN) || (num > MAX)) {
                wasValid = false;
                if (change) {
                    if (num < MIN) {
                        folRangeFieldMax.setText(String.valueOf(MIN));
                        folFilterNumRangeMax = String.valueOf(MIN);
                    } else {
                        folRangeFieldMax.setText(String.valueOf(MAX));
                        folFilterNumRangeMax = String.valueOf(MAX);
                    }
                }
            }
            return wasValid;
        }

    }
}
