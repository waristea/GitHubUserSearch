
package githubusersearch;

import static githubusersearch.GithubUserSearch.readJsonArrayFromUrl;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
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
    
    ArrayList<Repository> repos = new ArrayList<>();
   
    public User(JSONObject jo){
        try{
            this.id = jo.getInt("id");
            this.login = jo.getString("login");
            this.avatarURL = jo.getString("avatar_url");
            this.gravatarId = jo.getString("gravatar_id");
            this.apiURL = jo.getString("url");
            this.htmlURL = jo.getString("html_url");
            this.reposURL = jo.getString("repos_url");
            this.followersURL = jo.getString("followers_url");
            //this.followingURL = jo.getString("following_url");
            //this.gistsURL = jo.getString("gists_url");
            //this.starredURL = jo.getString("starred_url");
            //this.subscriptionsURL = jo.getString("subscriptions_url");
            //this.organizationsURL = jo.getString("organizations_url");
            //this.eventsURL = jo.getString("events_url");
            //this.receivedEventsURL = jo.getString("received_events_url");
            //this.score = jo.getInt("score");
            //this.siteAdmin = jo.getBoolean("site_admin");
            
            JSONArray jsonRepoArray = readJsonArrayFromUrl(reposURL.concat("?per_page=100"));
            // Handle repos>100
            // Reminder : Make if statement for empty string 
        
            for(int i = jsonRepoArray.length(); i>0; --i){
                JSONObject jsonRepoObject = (JSONObject) jsonRepoArray.get(i-1);
                Repository repo = new Repository(jsonRepoObject);
                
                repos.add(repo);
            }
            
        }catch(JSONException je){
            System.err.println(je.getMessage());
        }catch(IOException ioe){
            System.err.println(ioe.getMessage());
        }
    }
    
    public void printUser(){
        System.out.println("Login : " + login);
        System.out.println("Avatar URL : " + avatarURL);
        System.out.println("API URL : " + apiURL);
        System.out.println("HTML URL : " + htmlURL);
        System.out.println("Repos URL : " + reposURL);
        System.out.println("----------------------------------------------");
        for(int i = repos.size(); i>0; --i){
            Repository repo = repos.get(i-1);
            System.out.println(repo.name);
        }
        System.out.println("_______________________________________________");
    
    }
    
}
