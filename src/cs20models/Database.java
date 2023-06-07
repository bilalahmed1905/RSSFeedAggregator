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

    public static int getResultSize() {
        return resultSize;
    }

    public static void resetResultSize() {
        resultSize = 0;
    }

    public static ArrayList<Channel> getAllChannels() throws SQLException {
        String sql = "SELECT * FROM " + CHANNELTABLE;
        Connection conn = Database.connect();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        ArrayList<Channel> channels = new ArrayList<>();
        while (rs.next()) {
            Channel f = new Channel(rs.getString("channelTitle"), rs.getString("channelURL"), rs.getString("channelDesc"), rs.getString("channelLang"), rs.getString("channelLastPubDate"), rs.getString("channelRSSLink"));
            channels.add(f);
        }
        return channels;
    }

    public static Channel getChannelInfo(String title) throws SQLException {
        String sql = "select from " + CHANNELTABLE + " where channelTitle='" + title + "'";
        Connection conn = Database.connect();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        Channel channel = new Channel(rs.getString("channelTitle"), rs.getString("channelURL"), rs.getString("channelDesc"), rs.getString("channelLang"), rs.getString("channelLastPubDate"), rs.getString("channelRSSLink"));
        return channel;
    }

    public static void addChannel(Channel f) throws SQLException {
        String sql = "insert into " + CHANNELTABLE + " (channelTitle, channelCategory, channelLastPubDate, channelDesc, channelURL, channelLang, channelRSSLink)"
                + " values (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = Database.connect().prepareStatement(sql);
        try {
            ps.setString(1, f.title);
            ps.setString(2, "News");
            ps.setString(3, f.pubDate);
            ps.setString(4, f.description);
            ps.setString(5, f.link);
            ps.setString(6, f.language);
            ps.setString(7, f.rssLink);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("Successfully Stored into database!!!");
        }
    }

    public static void addArticle(FeedItem f) throws SQLException {
        String sql = "insert into " + FEEDTABLE + "( itemTitle, itemDesc, itemImage, itemLink, itemDate, itemReadStatus, itemCategory, itemAuthor, channelRSSLink, itemHashString, itemGUID, itemEpoch) values (?,?,?,?,?,?,?,?,?,?,?, ?)";

        //   String sql = "insert into " + FEEDTABLE + "( itemTitle, itemDesc, itemImage, itemLink, itemDate, itemReadStatus, itemCategory, itemAuthor, channelRSSLink, itemHashString, itemGUID, itemEpoch) values (?,?,?,?,?,?,?,?,?,?,?, ?)";
        // sql += " ON CONFLICT(itemHashString) DO UPDATE SET ";
        PreparedStatement ps = Database.connect().prepareStatement(sql);

        try {
            ps.setString(1, f.title);
            ps.setString(2, f.description);
            ps.setString(3, "");
            ps.setString(4, f.url);
            ps.setString(5, f.date);
            ps.setInt(6, f.itemReadStatus); // 0 is unread, 1 is read 
            ps.setString(7, "News");
            ps.setString(8, f.author);
            ps.setString(9, f.rssLink);
            ps.setString(10, f.itemHash);
            ps.setString(11, f.guid);
            ps.setLong(12, f.itemEpoch);
            ps.executeUpdate();
        } catch (SQLException ex) {

            // SQLITE_CONSTRAINT_UNIQUE = 19
            if (ex.getErrorCode() == 19) {

            }
        } finally {
            System.out.println("Successfully Stored into database!!!");
        }
    }

    public static ArrayList<FeedItem> fetchArticles() throws SQLException {
        String sql = "select * from " + FEEDTABLE;
        Connection conn = Database.connect();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        ArrayList<FeedItem> articles = new ArrayList<>();
        while (rs.next()) {
            FeedItem f = new FeedItem();
            f.setURL(rs.getString("itemLink"));
            f.setAuthor(rs.getString("itemAuthor"));
            f.setGuid(rs.getString("itemGUID"));
            f.setDesc(rs.getString("itemDesc"));
            f.setTitle(rs.getString("itemTitle"));
            f.setItemReadStatus(rs.getInt("itemReadStatus"));
            f.setRSSLink(rs.getString("channelRSSLink"));
            f.setDate(rs.getString("itemDate"));
            f.setItemEpoch(rs.getLong("itemEpoch"));
            articles.add(f);
            System.out.println(f.getTitle());
            resultSize++;
        }
        System.out.println("Fetched from the database");
        return articles;
    }

    public static void updateReadStatus(int status, String title) throws SQLException {
        String sql = "UPDATE " + FEEDTABLE + " SET itemReadStatus = ? WHERE itemTitle = ?";
        PreparedStatement ps = Database.connect().prepareStatement(sql);
        try {
            ps.setInt(1, status);
            ps.setString(2, title);
            ps.executeUpdate();
        } catch (SQLException e) {
        } finally {
            System.out.println("Successfully Stored into database!!!");
        }
    }

    public static void removeChannel(String title) throws SQLException {
        String sql = "DELETE FROM " + FEEDTABLE + " WHERE channelTitle=?";
        try ( Connection conn = Database.connect();  PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, title);
            pstmt.executeUpdate();
        } catch (SQLException e) {

        }

    }

    public static int getReadStatus(String title) throws SQLException {
        String sql = "select itemReadStatus from " + FEEDTABLE + " where itemTitle ='" + title + "'";
        Connection conn = Database.connect();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        int readStatus = 0;
        while (rs.next()) {
            readStatus = rs.getInt("itemReadStatus");
        }
        return readStatus;
    }
}
