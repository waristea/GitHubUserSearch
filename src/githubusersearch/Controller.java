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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

/**
 *
 * @author William
 */
public class Controller {

    /**
     *
     */
    public static CombinedLayout combinedLayout;
    public static RepoList repoList = null;
    public static SearchSettings searchSettingsFrame = new SearchSettings();
    
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
    
    public static void showUserRepos(String reposURL) throws IOException, JSONException{
        JSONArray jsonRepoArray = Controller.readJsonArrayFromUrl(reposURL.concat("?per_page=100"));
        // Handle repos>100
        // Reminder : Make if statement for empty string 
        System.out.println("Repo size : "+jsonRepoArray.length());
        ArrayList<Repository> repos = new ArrayList<>();
        
        for(int j = 0; j<jsonRepoArray.length(); ++j){
            JSONObject jsonRepoObject = (JSONObject) jsonRepoArray.get(j);
            Repository repo = new Repository(jsonRepoObject);

            repos.add(repo);
        }
        
        if (repoList!=null) 
            repoList.getContentPane().removeAll();
        
        repoList = new RepoList(repos);
        
        repoList.pack();
        //repoList.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        repoList.setSize(500, 400);
        repoList.setVisible(true);
        repoList.setTitle("Repos");
    }
    
    public static void showSearchSettings(){
        /*
        if (searchSettingsFrame!=null) 
            searchSettingsFrame.getContentPane().removeAll();
        
        searchSettingsFrame = new SearchSettings();
        */
        searchSettingsFrame.pack();
        //repoList.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        searchSettingsFrame.setSize(500, 400);
        searchSettingsFrame.setVisible(true);
        searchSettingsFrame.setTitle("Settings");
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
    
    /**
     *
     * @param url
     * @return
     * @throws MalformedURLException
     * @throws IOException
     */
    public static ImageIcon readImageIconFromURL(String url) throws MalformedURLException, IOException{
        BufferedImage image = ImageIO.read(new URL(url));
        int type = image.getType();
        image = resizeImage(image, type);
        
        ImageIcon imageIcon = new ImageIcon(image);
        
        return imageIcon;
    }
    
    /**
     *
     * @param url
     * @return
     * @throws IOException
     * @throws JSONException
     */
    public static ArrayList<User> getUserList(String url) throws IOException, JSONException{
        JSONObject json = readJsonFromUrl(url);
        JSONArray jsonUserArray = json.getJSONArray("items");
        ArrayList<User> userList = new ArrayList<>();
        System.out.println(jsonUserArray.length());
        int fetchedAmt = jsonUserArray.length();
        fetchedAmt = (fetchedAmt>5) ? 5 : fetchedAmt;
        
        for(int i=0; i<fetchedAmt; ++i){
            try{
                System.out.println("Trying : "+(i+1));
                JSONObject jo = (JSONObject) jsonUserArray.get(i);
                
                int id = jo.getInt("id");
                String login = jo.getString("login");
                String avatarURL = jo.getString("avatar_url");
                String gravatarId = jo.getString("gravatar_id");
                String apiURL = jo.getString("url");
                String htmlURL = jo.getString("html_url");
                String reposURL = jo.getString("repos_url");
                String followersURL = jo.getString("followers_url");
                
                User user = new User(id, login, avatarURL, gravatarId, apiURL, htmlURL, reposURL, followersURL, null);
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

    static void search(String searched) throws IOException{
        try {
            // Get UserList
            
            // Tanpa autentifikasi [Tidak dipakai] 
            // String keyword = "https://api.github.com/search/users?q="+searched+SearchSettings.getParameter();
            // String authString = "";
            
            // Dengan Authentifikasi
            // Menggunakan clientSecret [Dipakai]
            String clientId = "919135325e47a148f207";
            String clientSecret = "e1d85be38fdcc8e3b41bfa95310a44bb9fefd351";
            String authStringApp = "?client_id="+clientId+"&client_secret="+clientSecret;
            String authString = "";
            readJsonFromUrl("https://api.github.com/rate_limit"+authStringApp);
            
            // Menggunakan Personal Access Token [Tidak dipakai] 
            // String personalAccessToken = "";
            // String authString = "?access_token="+personalAccessToken;
            
            String keyword = "https://api.github.com/search/users?q="+searched+SearchSettings.getParameter()+authString;
            System.out.println(keyword);
            ArrayList<User> userList = getUserList(keyword);
            
            // Refresh JPanel 
            combinedLayout.getContentPane().removeAll();
            combinedLayout.setSearchedList(userList);
            
            Controller.combinedLayout.pack();
            Controller.combinedLayout.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Controller.combinedLayout.setSize(500, 400);
            Controller.combinedLayout.setTitle("GitHub User Searcher");
            Controller.combinedLayout.setVisible(true);
            combinedLayout.repaint();
                
        } catch (JSONException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
