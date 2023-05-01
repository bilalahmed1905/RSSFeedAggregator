/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cs20models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQL {

    String url;

    public SQL(String url) {
        this.url = url;
    }

    public String getTableURL() {
        return this.url;
    }

    public boolean createTable() {
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS feedInfo (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	channelName text NOT NULL,\n"
                + "	articleLink text NOT NULL,\n"
                + "	headlines text NOT NULL,\n"
                + "	desc text NOT NULL,\n"
                + ");";

        try ( Connection conn = DriverManager.getConnection(this.url);  Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public void store(int id, String channelName, String url, String headline, String desc) {
        String sql = "INSERT into feedInfo (id, channelName, articleLink, headlines, desc) \n VALUES (" + id + " test, " + url + ", " + headline +", " + desc + ")";
        try ( Connection conn = DriverManager.getConnection(this.url);  Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void store(int id, String channelName, String url, String headline) {
        String sql = "INSERT into feedInfo (id, channelName, articleLink, headlines, desc) \n VALUES (" + id + " test, " + url + ", " + headline + ")";
        try ( Connection conn = DriverManager.getConnection(this.url);  Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
}
