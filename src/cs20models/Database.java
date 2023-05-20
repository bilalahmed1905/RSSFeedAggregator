package cs20models;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
public class Database {
    public static int resultSize = 0;
    static final String CHANNELTABLE = "Channel";
    static final String FEEDTABLE = "Feed";
    private static Connection connect() {
        String url = "jdbc:sqlite:rss.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static void selectAll() throws SQLException {
        String sql = "SELECT * FROM " + CHANNELTABLE;
        Connection conn = Database.connect();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            System.out.println(rs.getInt("id") + rs.getString("title"));
        }
    }
   public static int getResultSize() {
    return resultSize;
   }
    public static ArrayList<Feed> getAllChannels() throws SQLException {
        String sql = "SELECT * FROM " + CHANNELTABLE;
        Connection conn = Database.connect();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        ArrayList<Feed> channels = new ArrayList<>();
        while (rs.next()) {
            Feed f = new Feed(rs.getString("channelTitle"), rs.getString("channelURL"), rs.getString("channelDesc"), rs.getString("channelLang"), rs.getString("channelLastPubDate"));
            channels.add(f);
        }
        return channels;
    }
    
    public static Feed getChannelInfo(int id) throws SQLException {
        String sql = "select from " + CHANNELTABLE + " where id=" + id;
        Connection conn = Database.connect();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        Feed channel = new Feed(rs.getString("channelTitle"), rs.getString("channelURL"), rs.getString("channelDesc"), rs.getString("channelLang"), rs.getString("channelLastPubDate"));
        return channel;
    }
    public static Feed getChannelInfo(String title) throws SQLException {
        String sql = "select from " + CHANNELTABLE + " where channelTitle='" + title + "'";
        Connection conn = Database.connect();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        Feed channel = new Feed(rs.getString("channelTitle"), rs.getString("channelURL"), rs.getString("channelDesc"), rs.getString("channelLang"), rs.getString("channelLastPubDate"));
        return channel;
    }

    public static void addChannel(Feed f) throws SQLException {
        String sql = "insert into " + CHANNELTABLE + " (channelTitle, channelCategory, channelLastPubDate, channelDesc, channelURL, channelLang)"
                + " values (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = Database.connect().prepareStatement(sql);
        try {
            ps.setString(1, f.title);
            ps.setString(2, "News");
            ps.setString(3, f.pubDate);
            ps.setString(4, f.description);
            ps.setString(5, f.link);
            ps.setString(6, f.language);
            ps.executeUpdate();

        } catch (SQLException e) {
        } finally {
            System.out.println("Successfully Stored into database!!!");
        }
    }
    
    public static void addArticle(FeedItem f) throws SQLException {
     String sql = "insert into " + FEEDTABLE  + "( itemTitle, itemDesc, itemImage, itemLink, itemDate, itemReadStatus, itemCategory, itemAuthor) values (?,?,?,?,?,?,?,?)";
     PreparedStatement ps = Database.connect().prepareStatement(sql);
        try {
            ps.setString(1, f.title);
            ps.setString(2, f.description);
            ps.setString(3, "");
            ps.setString(4, f.url);
            ps.setString(5, f.date);
            ps.setString(6, "unread");
            ps.setString(7, "News");
            ps.setString(8, f.author);
            ps.executeUpdate();
        } catch (SQLException e) {
        } finally {
            System.out.println("Successfully Stored into database!!!");
        }
    }

    public static ArrayList<FeedItem> getAllArticles() throws SQLException {
     String sql = "select * from " + FEEDTABLE;
     Connection conn = Database.connect();
     Statement stmt = conn.createStatement();
     ResultSet rs = stmt.executeQuery(sql);
     ArrayList<FeedItem> articles = new ArrayList<>();
        while (rs.next()) {
            FeedItem f = new FeedItem();
            f.setURL(rs.getString("itemLink"));
            f.setAuthor(rs.getString("itemAuthor"));
            f.setDesc(rs.getString("itemDesc"));
            f.setTitle(rs.getString("itemTitle"));
            articles.add(f);
            System.out.println(f.getTitle());
            resultSize++;
        }
        System.out.println("Fetched from the database");
        return articles;
    }
}