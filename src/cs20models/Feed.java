package cs20models;
import java.util.List;
import java.util.ArrayList;

public class Feed {

    final String title;
    final String link;
    final String description;
    final String language;
    final String copyright;
    final String pubDate;
    final String articleSnippet;

    final List<FeedMessage> entries = new ArrayList<>();

    public Feed(String title, String link, String description, String language,
            String copyright, String pubDate, String snippet) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.language = language;
        this.copyright = copyright;
        this.pubDate = pubDate;
        this.articleSnippet = snippet;
    }

    public List<FeedMessage> getMessages() {
        return entries;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getDescription() {
        return description;
    }

    public String getLanguage() {
        return language;
    }

    public String getCopyright() {
        return copyright;
    }

    public String getPubDate() {
        return pubDate;
    }

    public String getSnippet() {
        return articleSnippet;
    }

    @Override
    public String toString() {
        return "Feed [copyright=" + copyright + ", description=" + description
                + ", language=" + language + ", link=" + link + ", pubDate="
                + pubDate + ", title=" + title + "]";
    }

}
