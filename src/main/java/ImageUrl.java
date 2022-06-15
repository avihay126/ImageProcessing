import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;



import java.io.IOException;
import java.util.ArrayList;



public class ImageUrl {

    private String name;
    private Document profile;


    public ImageUrl(String name) {
        this.name=changeNameFormat(name);
        getProfile();
    }

    private String changeNameFormat(String name) {
        String[] temp = name.split(" ");
        String newName = "";
        for (String s : temp) {
            newName += s;
        }
        return newName;
    }

    private Document getProfile(){
        try {
            this.profile = Jsoup.connect("https://www.facebook.com/avihay.swissa/").get();





        } catch (IOException e) {

           this.profile=getProfile();
        }
        return this.profile;
    }




}
