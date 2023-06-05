package cs20models;

public class Channel {

    final String title;
    final String link;
    final String description;
    final String language;
    final String copyright;
    final String pubDate;
    String rssLink;

    public Channel(String title, String link, String description, String language,
            String copyright, String pubDate, String rssLink) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.language = language;
        this.copyright = copyright;
        this.pubDate = pubDate;
        this.rssLink = rssLink;
    }

    public Channel(String title, String link, String description, String language, String pubDate, String rssLink) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.language = language;
        this.copyright = "";
        this.pubDate = pubDate;
        this.rssLink = rssLink;
    }

    public String getRSSLink() {
        return this.rssLink;
    }

    public void setRSSLink(String link) {
        this.rssLink = link;
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

    @Override
    public String toString() {
        return "Feed [copyright=" + copyright + ", description=" + description
                + ", language=" + language + ", link=" + link + ", pubDate="
                + pubDate + ", title=" + title + "]";
    }
    
}
