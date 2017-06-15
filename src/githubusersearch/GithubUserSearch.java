package githubusersearch;

import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFrame;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

public class GithubUserSearch {
    public static void main(String[] args) throws IOException, JSONException {
        String url = "https://api.github.com/search/users?q=tom+repos:%3E42+followers:%3E1000&per_page=5&sort=stars&order=desc";
        ArrayList<User> userList = Controller.getUserList(url);

        CombinedLayout frame = new CombinedLayout(userList);

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setVisible(true);
      }
}