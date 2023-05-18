package cs20models;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
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
    ArrayList<FeedMessage> articles = new ArrayList<>(1000);
    ArrayList<String> headlines = new ArrayList<>(1000);
    ArrayList<String> subArticleLinks = new ArrayList<>(1000);
    ArrayList<String> descriptions = new ArrayList<>(1000);
    public String date;

    public FeedMessage() {

    }

    public void setURL(String url) {
        this.url = url;
    }

    public String getURL() {
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
                this.subArticleLinks.add(item.children().select("link").text());
                this.headlines.add(item.children().select("title").text());
                if (item.children().select("description").text().contains("img")) {
                    this.descriptions.add(" ");
                } else {
                    this.descriptions.add(item.children().select("description").text());
                }
                articles.add(itemCount, new FeedMessage());
                articles.get(itemCount).setGuid(item.children().select("guid").text());
                articles.get(itemCount).setAuthor(item.children().select("author").text());
                articles.get(itemCount).setTitle(this.headlines.get(itemCount));
                articles.get(itemCount).setURL(this.subArticleLinks.get(itemCount));
                articles.get(itemCount).setDesc(this.descriptions.get(itemCount));
                articles.get(itemCount).setDate(item.children().select("pubDate").text());
                SQL.addArticle(this.articles.get(itemCount));
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

    public void clearLists() {
        this.subArticleLinks.clear();
        this.headlines.clear();
        this.descriptions.clear();
        this.subArticleLinks = new ArrayList<>();
        this.descriptions = new ArrayList<>();
        this.headlines = new ArrayList<>();
    }
}
