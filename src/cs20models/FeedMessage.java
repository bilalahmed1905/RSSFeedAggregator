package cs20models;

import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class FeedMessage {

    String url;
    ArrayList<String> urls = new ArrayList<>();
    String title;
    String description;
    String link;
    String author;
    String guid;
    int itemCount = 0;
    ArrayList<Feed> articles = new ArrayList<>(1000);
    ArrayList<String> headlines = new ArrayList<>(1000);
    ArrayList<String> subArticleLinks = new ArrayList<>(1000);
    ArrayList<String> descriptions = new ArrayList<>(1000);
    public FeedMessage() {

    }

    public void setURL(String url) {
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }

    public void setURLatIndex(String url, int index) {
        urls.add(url);
    }

    public String getURLatIndex(int index) {
        return urls.get(index);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGuid() {
        return this.guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }
   public ArrayList<String> removeDuplicates(ArrayList<String> arr) {
    for (int i = 0; i < arr.size(); i++) {
      for (int y = 0; y < arr.size(); y++) {
       if (arr.get(i).equals(arr.get(y)) && i != y) {
         arr.remove(y);
       }
    }
    }
    return arr;
   }
    public void getItems(){
        try {
            Document doc = Jsoup.connect(url).get();

            Elements items = doc.select("item");
            for (Element item : items) {
                this.subArticleLinks.add(item.children().select("link").text());
                this.headlines.add(item.children().select("title").text());
                if (item.children().select("description").text().contains("img")) {
                    this.descriptions.add(" ");
                } else {
                    this.descriptions.add(item.children().select("description").text());
                }
                this.itemCount++;
             
            }
            this.itemCount = this.headlines.size();
//            removeDuplicates(this.subArticleLinks);
//                removeDuplicates(this.headlines);
//            removeDuplicates(this.descriptions);
            
        } catch (IOException ex) {

        }
    }

    public String getHeadline(int index) {
        return this.headlines.get(index);
    }

    public String getDesc(int index) {
        return this.descriptions.get(index);
    }

    public String getLink(int index) {
        return this.subArticleLinks.get(index);
    }

    public int getItemCount() {
        return this.itemCount;
    }
    public void clearLists() {
     this.subArticleLinks.clear();
     this.headlines.clear();  
     this.descriptions.clear();
     
     this.subArticleLinks = new ArrayList<>();
     this.descriptions = new ArrayList<>();
     this.headlines = new ArrayList<>();
    }
}
