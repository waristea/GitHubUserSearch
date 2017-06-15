
package githubusersearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

public class User extends JSONObject{
    int id;
    String login;
    String avatarURL;
    String gravatarId;
    String apiURL;
    String htmlURL;
    String reposURL;
    String followersURL;
    //String followingURL;
    //String gistsURL;
    //String starredURL;
    //String subscriptionsURL;
    //String organizationsURL;
    //String eventsURL;
    //String receivedEventsURL;
    //int score;
    //boolean siteAdmin;
    
    ArrayList<Repository> repoList = new ArrayList<>();
    ImageIcon userImageIcon;
   
    public User(int id, String login, String avatarURL, String gravatarId, String apiURL, 
                String htmlURL,String reposURL, String followersURL, ArrayList<Repository> repoList){
        this.id = id;
        this.login = login;
        this.avatarURL = avatarURL;
        this.gravatarId = gravatarId;
        this.apiURL = apiURL;
        this.htmlURL = htmlURL;
        this.reposURL = reposURL;
        this.followersURL = followersURL;
        this.repoList = repoList;
    }
    
    public void printUser(){
        System.out.println("Login : " + login);
        System.out.println("Avatar URL : " + avatarURL);
        System.out.println("API URL : " + apiURL);
        System.out.println("HTML URL : " + htmlURL);
        System.out.println("Repos URL : " + reposURL);
        System.out.println("----------------------------------------------");
        for(int i = repoList.size(); i>0; --i){
            Repository repo = repoList.get(i-1);
            System.out.println(repo.name);
        }
        System.out.println("_______________________________________________");
    
    }
    
    // Setter
    public void setId(int id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }

    public void setGravatarId(String gravatarId) {
        this.gravatarId = gravatarId;
    }

    public void setApiURL(String apiURL) {
        this.apiURL = apiURL;
    }

    public void setHtmlURL(String htmlURL) {
        this.htmlURL = htmlURL;
    }

    public void setReposURL(String reposURL) {
        this.reposURL = reposURL;
    }

    public void setFollowersURL(String followersURL) {
        this.followersURL = followersURL;
    }

    public void setRepoList(ArrayList<Repository> repos) {
        this.repoList = repos;
    }
    
    public void setUserImageIcon(ImageIcon userImageIcon){
        this.userImageIcon = userImageIcon;
    }
    
    // Getter 

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getAvatarURL() {
        return avatarURL;
    }

    public String getGravatarId() {
        return gravatarId;
    }

    public String getApiURL() {
        return apiURL;
    }

    public String getHtmlURL() {
        return htmlURL;
    }

    public String getReposURL() {
        return reposURL;
    }

    public String getFollowersURL() {
        return followersURL;
    }

    public ArrayList<Repository> getRepoList() {
        return repoList;
    }
    
    public ImageIcon getUserImageIcon(){
        return userImageIcon;
    }
}
