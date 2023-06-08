package cs20models;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utilities {

    // this method returns MD5 hash of a string
    public static String MD5(String input) throws IOException, NoSuchAlgorithmException {
        MessageDigest md = null;
        String hashtext = null;
        try {
            md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException ex) {

        }
        return hashtext;
    }

    // this method returns domain name from any URL
    public static String getDomainName(String url) throws URISyntaxException {
        URI uri = new URI(url);
        String host = uri.getHost();
        return host;
    }

}
