
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

/**
 *
 * @author William
 */
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
   
    /**
     *
     * @param id
     * @param login
     * @param avatarURL
     * @param gravatarId
     * @param apiURL
     * @param htmlURL
     * @param reposURL
     * @param followersURL
     * @param repoList
     */
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
    
    /**
     *
     */
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

    /**
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @param login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     *
     * @param avatarURL
     */
    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }

    /**
     *
     * @param gravatarId
     */
    public void setGravatarId(String gravatarId) {
        this.gravatarId = gravatarId;
    }

    /**
     *
     * @param apiURL
     */
    public void setApiURL(String apiURL) {
        this.apiURL = apiURL;
    }

    /**
     *
     * @param htmlURL
     */
    public void setHtmlURL(String htmlURL) {
        this.htmlURL = htmlURL;
    }

    /**
     *
     * @param reposURL
     */
    public void setReposURL(String reposURL) {
        this.reposURL = reposURL;
    }

    /**
     *
     * @param followersURL
     */
    public void setFollowersURL(String followersURL) {
        this.followersURL = followersURL;
    }

    /**
     *
     * @param repos
     */
    public void setRepoList(ArrayList<Repository> repos) {
        this.repoList = repos;
    }
    
    /**
     *
     * @param userImageIcon
     */
    public void setUserImageIcon(ImageIcon userImageIcon){
        this.userImageIcon = userImageIcon;
    }
    
    // Getter 

    /**
     *
     * @return
     */

    public int getId() {
        return id;
    }

    /**
     *
     * @return
     */
    public String getLogin() {
        return login;
    }

    /**
     *
     * @return
     */
    public String getAvatarURL() {
        return avatarURL;
    }

    /**
     *
     * @return
     */
    public String getGravatarId() {
        return gravatarId;
    }

    /**
     *
     * @return
     */
    public String getApiURL() {
        return apiURL;
    }

    /**
     *
     * @return
     */
    public String getHtmlURL() {
        return htmlURL;
    }

    /**
     *
     * @return
     */
    public String getReposURL() {
        return reposURL;
    }

    /**
     *
     * @return
     */
    public String getFollowersURL() {
        return followersURL;
    }

    /**
     *
     * @return
     */
    public ArrayList<Repository> getRepoList() {
        return repoList;
    }
    
    /**
     *
     * @return
     */
    public ImageIcon getUserImageIcon(){
        return userImageIcon;
    }
}
