package githubusersearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

public class GithubUserSearch {

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

  public static void main(String[] args) throws IOException, JSONException {
    String url = "https://api.github.com/search/users?q=tom+repos:%3E42+followers:%3E1000&per_page=5&sort=stars&order=desc";
    JSONObject json = readJsonFromUrl(url);
    JSONArray userArray = json.getJSONArray("items");
    
    for(int i=0; i<userArray.length(); ++i){
        JSONObject user = (JSONObject) userArray.get(i);
        
        String username = user.getString("login");
        
        String reposUrl = user.getString("repos_url").concat("?per_page=100");
        System.out.println(reposUrl);
        JSONArray repoArray = readJsonArrayFromUrl(reposUrl);
        
        System.out.println((i+1)+". "+username);
        // Handle repos>100
        // Reminder : Make if statement for empty string 
        System.out.println("Num o' Repo : " + repoArray.length());
        
    }
  }
}