package githubusersearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import javax.swing.JFrame;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

public class FrontController {
    
  private static String readAll(Reader rd) throws IOException {
    StringBuilder sb = new StringBuilder();
    int cp;
    while ((cp = rd.read()) != -1) {
      sb.append((char) cp);
    }
    return sb.toString();
  }

  public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
    InputStream is = new URL(url).openStream();
    try {
      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
      String jsonText = readAll(rd);
      JSONObject json = new JSONObject(jsonText);
      return json;
    } finally {
      is.close();
    }
  }
  
  public static JSONArray readJsonArrayFromUrl(String url) throws IOException, JSONException {
    InputStream is = new URL(url).openStream();
    try {
      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
      String jsonText = readAll(rd);
      JSONArray json = new JSONArray(jsonText);
      return json;
    } finally {
      is.close();
    }
  }
    
    public static ArrayList<User> getUserList(String url) throws IOException, JSONException{
        JSONObject json = readJsonFromUrl(url);
        JSONArray jsonUserArray = json.getJSONArray("items");
        ArrayList<User> userList = new ArrayList<>();
        
        for(int i=0; i<jsonUserArray.length(); ++i){
            try{
                JSONObject jo = (JSONObject) jsonUserArray.get(i);
                ArrayList<Repository> repos = new ArrayList<>();

                int id = jo.getInt("id");
                String login = jo.getString("login");
                String avatarURL = jo.getString("avatar_url");
                String gravatarId = jo.getString("gravatar_id");
                String apiURL = jo.getString("url");
                String htmlURL = jo.getString("html_url");
                String reposURL = jo.getString("repos_url");
                String followersURL = jo.getString("followers_url");
                
                JSONArray jsonRepoArray = FrontController.readJsonArrayFromUrl(reposURL.concat("?per_page=100"));
                // Handle repos>100
                // Reminder : Make if statement for empty string 

                for(int j = jsonRepoArray.length(); j>0; --j){
                    JSONObject jsonRepoObject = (JSONObject) jsonRepoArray.get(j-1);
                    Repository repo = new Repository(jsonRepoObject);

                    repos.add(repo);
                }
                
                User user = new User(id, login, avatarURL, gravatarId, apiURL, htmlURL, reposURL, followersURL, repos);
                userList.add(user);

            }catch(JSONException je){
                System.err.println(je.getMessage());
            }catch(IOException ioe){
                System.err.println(ioe.getMessage());
            }
        }
        
        return userList;
    }
}
