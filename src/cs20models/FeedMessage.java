package cs20models;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class FeedMessage {

    String url;
    String urls[] = new String[100];
    String title;
    String description;
    String link;
    String author;
    String guid;
    String snippet;
    String headline;
    String[] headlines;
    String[] subArticleLinks;
    int relHeadlineNum;

    public FeedMessage() {
        this.urls[0] = this.url;
    }

    public void setURL(String url) {
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }

    public void setURLatIndex(String url, int index) {
        urls[index] = url;
    }

    public String getURLatIndex(int index) {
        return urls[index];
    }

    public void resetURLs() {
        for (int i = 0; i < urls.length; i++) {
            if (!urls[i].equals("")) {
                urls[i] = "";
            }
        }
    }

    public void setSubArticleLinkatIndex(String url, int index) {
        subArticleLinks[index] = url;
    }

    public String getSubArticleLinkatIndex(int index) {
        return subArticleLinks[index];
    }

    public void resetSubArticleLinks() {
        for (int i = 0; i < subArticleLinks.length; i++) {
            if (!subArticleLinks[i].equals("")) {
                subArticleLinks[i] = "";
            }
        }
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

    public void setHeadline() {
        try {
            // parse the HTML document
            Document doc = Jsoup.connect(url).get();

            // select item
            Elements items = doc.select("item");
            System.out.println(items.size());
            int i = 1;
            for (Element item : items) {
                System.out.println(i + ". " + item.children().select("title").text());
                System.out.println(item.children().select("description").text());
                System.out.println(item.children().select("description").text());
                System.out.println("=======");
                i++;
            }

            // select all the links in the document
            Elements head = doc.select("title");
            this.headlines = new String[head.size()];
            Elements links = doc.select("link");
            this.subArticleLinks = new String[head.size()];
            // print out the link URLs and text
            i = 0;
            for (Element link : head) {
                this.headlines[i] = link.text();
                this.subArticleLinks[i] = link.text();
                i++;
            }
            i = 0;
//            for (Element link : links) {
//               
//                i++;
//            }
            int r = (int) (Math.random() * head.size());
            this.headline = this.headlines[r];
            this.link = this.subArticleLinks[r];
            System.out.println(this.headline);
            System.out.println(this.subArticleLinks[r]);
            this.relHeadlineNum = r;
        } catch (IOException ex) {
            System.err.println("IOException on line 135 in FeedMessage.java");
        }
    }

    public String getHeadline() {
        return this.headline;
    }

    public int getHeadlineNum() {
        return this.relHeadlineNum;
    }

    public void setSnippet() {

    }

    public String getSnippet() {
        return this.snippet;
    }

}
