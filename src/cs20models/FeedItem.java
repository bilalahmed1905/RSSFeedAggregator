package cs20models;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class FeedItem {

    String url;
    String title;
    String description;
    String link;
    String author;
    String guid;
    int itemCount = 0;
    ArrayList<FeedItem> articles = new ArrayList<>(1000);
    public String date;

    public FeedItem() {

    }

    public void setURL(String url) {
        this.url = url;
    }

    public String getURL() {
        return this.url;
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

    public void setDesc(String desc) {
        this.description = desc;
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

    public void sendItemsToDatabase() throws SQLException {
        try {
            Document doc = Jsoup.connect(this.url).get();
            Elements items = doc.select("item");
            for (Element item : items) {
                articles.add(itemCount, new FeedItem());
                if (item.children().select("description").text().contains("img")) {
                 articles.get(itemCount).setDesc(" ");
                } else {
                 articles.get(itemCount).setDesc(item.children().select("description").text());
                }
                articles.get(itemCount).setGuid(item.children().select("guid").text());
                articles.get(itemCount).setAuthor(item.children().select("author").text());
                articles.get(itemCount).setTitle(item.children().select("title").text());
                articles.get(itemCount).setURL(item.children().select("link").text());
                articles.get(itemCount).setDate(item.children().select("pubDate").text());
                Database.addArticle(this.articles.get(itemCount));
                this.itemCount++;
            }

        } catch (IOException ex) {

        }
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String d) {
        this.date = d;
    }

    public int getItemCount() {
        return this.itemCount;
    }
}
