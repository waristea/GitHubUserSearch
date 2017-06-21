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
        Controller.combinedLayout = new CombinedLayout();
        
        Controller.combinedLayout.setTitle("GitHub User Searcher");
        Controller.combinedLayout.pack();
        Controller.combinedLayout.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Controller.combinedLayout.setSize(500, 400);
        Controller.combinedLayout.setVisible(true);
      }
}