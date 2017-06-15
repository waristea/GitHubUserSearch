package githubusersearch;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

public class Controller {
    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
          sb.append((char) cp);
        }
        return sb.toString();
    }

    private static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
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
  
    private static JSONArray readJsonArrayFromUrl(String url) throws IOException, JSONException {
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
    
    private static BufferedImage resizeImage(BufferedImage originalImage, int type){
	int imgWidth = 50;
        int imgHeight = 50;
        
        BufferedImage resizedImage = new BufferedImage(imgWidth, imgHeight, type);
	Graphics2D g = resizedImage.createGraphics();
	g.drawImage(originalImage, 0, 0, imgWidth, imgHeight, null);
	g.dispose();

	return resizedImage;
    }
    
    public static ImageIcon readImageIconFromURL(String url) throws MalformedURLException, IOException{
        BufferedImage image = ImageIO.read(new URL(url));
        int type = image.getType();
        image = resizeImage(image, type);
        
        ImageIcon imageIcon = new ImageIcon(image);
        
        return imageIcon;
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
                
                JSONArray jsonRepoArray = Controller.readJsonArrayFromUrl(reposURL.concat("?per_page=100"));
                // Handle repos>100
                // Reminder : Make if statement for empty string 

                for(int j = jsonRepoArray.length(); j>0; --j){
                    JSONObject jsonRepoObject = (JSONObject) jsonRepoArray.get(j-1);
                    Repository repo = new Repository(jsonRepoObject);

                    repos.add(repo);
                }
                
                User user = new User(id, login, avatarURL, gravatarId, apiURL, htmlURL, reposURL, followersURL, repos);
                if (user.getAvatarURL()!=""&&user.getAvatarURL()!=null){
                    user.setUserImageIcon(readImageIconFromURL(user.getAvatarURL()));
                }
                else{
                    user.setUserImageIcon(readImageIconFromURL(user.getGravatarId()));
                }
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
