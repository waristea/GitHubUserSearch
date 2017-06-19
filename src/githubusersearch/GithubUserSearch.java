package githubusersearch;

import java.awt.Frame;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFrame;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

/**
 *
 * @author William
 */
public class GithubUserSearch {

    /**
     *
     * @param args
     * @throws IOException
     * @throws JSONException
     */
    public static void main(String[] args) throws IOException, JSONException {
        //String url = "https://api.github.com/search/users?q=tom+repos:%3E42+followers:%3E1000&per_page=5&sort=stars&order=desc";
        
        Controller.combinedLayout = new CombinedLayout();
        
        Controller.combinedLayout.pack();
        Controller.combinedLayout.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Controller.combinedLayout.setSize(500, 400);
        Controller.combinedLayout.setVisible(true);
      }
}