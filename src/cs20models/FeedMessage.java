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
    ArrayList<String> headlines = new ArrayList<>();
    ArrayList<String> subArticleLinks = new ArrayList<>();
    ArrayList<String> descriptions = new ArrayList<>();
    Elements items[];

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

    public void getItems() {
        try {
            // parse the HTML document
            Document doc = Jsoup.connect(url).get();
            // select item
            Elements items = doc.select("item");

            System.out.println(items.size());
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
}
