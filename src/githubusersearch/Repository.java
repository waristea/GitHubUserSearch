package githubusersearch;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

/**
 *
 * @author William
 */
public class Repository {
    int id;
    String name;
    String fullName;
    String description;
    String htmlURL;
    
    /**
     *
     * @param jo
     */
    public Repository(JSONObject jo){
        try{
            id = jo.getInt("id");
            name = jo.getString("name");
            fullName = jo.getString("full_name");
            description = jo.getString("description");
            htmlURL = jo.getString("url");
        }catch(JSONException je){
            System.err.println(je.getMessage());
        }
    }
    
    // Setter
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setHtmlURL(String htmlURL) {
        this.htmlURL = htmlURL;
    }
    
    // Getter
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFullName() {
        return fullName;
    }

    public String getDescription() {
        return description;
    }

    public String getHtmlURL() {
        return htmlURL;
    }
    
    
    
    /**
     *
     */
    public void printRepo(){
        System.out.println("ID : " + id);
        System.out.println("Name : " + name);
        System.out.println("Full Name : " + fullName);
        System.out.println("Description : " + description);
        System.out.println("URL : " + htmlURL);
        
    }
}
