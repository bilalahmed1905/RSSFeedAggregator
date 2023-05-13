package cs20models;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import org.jsoup.nodes.Element;

public class SQL {

    static final String tableName = "Channel";

    private static Connection connect() {
        // SQLite connection string
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
        String sql = "SELECT * FROM " + tableName;

        Connection conn = SQL.connect();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            System.out.println(rs.getInt("id") + rs.getString("title"));
        }

    }

    public static ArrayList<Feed> getAllChannels() throws SQLException {
        String sql = "SELECT * FROM " + tableName;
        Connection conn = SQL.connect();
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
        String sql = "select from " + tableName + " where id=" + id;
        Connection conn = SQL.connect();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        Feed channel = new Feed(rs.getString("channelTitle"), rs.getString("channelURL"), rs.getString("channelDesc"), rs.getString("channelLang"), rs.getString("channelLastPubDate"));
        return channel;
    }

    public static Feed getChannelInfo(String title) throws SQLException {
        String sql = "select from " + tableName + " where title='" + title + "'";
        Connection conn = SQL.connect();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        Feed channel = new Feed(rs.getString("channelTitle"), rs.getString("channelURL"), rs.getString("channelDesc"), rs.getString("channelLang"), rs.getString("channelLastPubDate"));
        return channel;
    }

    public static void addChannel(Feed f) throws SQLException {
        String sql = "insert into " + tableName + " (channelTitle, channelCategory, channelLastPubDate, channelDesc, channelURL, channelLang)"
                + " values (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = SQL.connect().prepareStatement(sql);
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
}
