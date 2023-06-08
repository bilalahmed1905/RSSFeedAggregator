package cs20models;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class FeedItem {

    String url;
    String title;
    String description;
    String author;
    String guid;
    String rssLink;
    String itemHash;
    long itemEpoch;
    int itemCount = 0;
    ArrayList<FeedItem> articles = new ArrayList<>(1000);
    public String date;
    public int itemReadStatus;

    public FeedItem() {

    }

    public void setURL(String url) {
        this.url = url;
    }

    public String getURL() {
        return this.url;
    }

    public void setItemEpoch(long e) {
        this.itemEpoch = e;
    }

    public long getItemEpoch() {
        return itemEpoch;
    }

    public void setHash(String h) {
        this.itemHash = h;
    }

    public String getHash() {
        return this.itemHash;
    }

    public String getRSSLink() {
        return this.rssLink;
    }

    public void setRSSLink(String l) {
        this.rssLink = l;
    }

    public int getItemReadStatus() {
        return this.itemReadStatus;
    }

    public void setItemReadStatus(int s) {
        this.itemReadStatus = s;
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

    public void fetchAndStoreFeed(String rssURL) throws SQLException, NoSuchAlgorithmException, ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.ENGLISH);
        try {
            Document doc = Jsoup.connect(rssURL).get();
            Elements items = doc.select("item");
            for (Element item : items) {
                articles.add(itemCount, new FeedItem());
                String rawDescHTML = item.children().select("description").html();
                Document descHTMLCdata = Jsoup.parse(rawDescHTML);
                String descHTML = descHTMLCdata.text();
                String descText = Jsoup.parse(descHTML).text();
                articles.get(itemCount).setDesc(descText);
                articles.get(itemCount).setGuid(item.children().select("guid").text());
                articles.get(itemCount).setAuthor(item.children().select("author").text());
                articles.get(itemCount).setTitle(item.children().select("title").text());
                articles.get(itemCount).setURL(item.children().select("link").text());
                articles.get(itemCount).setDate(item.children().select("pubDate").text());
                articles.get(itemCount).setRSSLink(rssURL);
                Date d = formatter.parse(articles.get(itemCount).getDate());
                articles.get(itemCount).setItemEpoch(d.getTime());
                articles.get(itemCount).setHash(Utilities.MD5(articles.get(itemCount).guid + articles.get(itemCount).rssLink));
                articles.get(itemCount).setItemReadStatus(0);
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
