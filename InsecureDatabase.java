import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.security.MessageDigest;

public class SecurityTest {
    public static void main(String[] args) throws Exception {
        // 🚨 Vulnerability: Hardcoded Credentials
        String url = "jdbc:mysql://localhost:3306/mydb";
        String username = "admin";
        String password = "password123";

        Connection conn = DriverManager.getConnection(url, username, password);
        Statement stmt = conn.createStatement();

        // 🚨 Vulnerability: SQL Injection
        String userInput = args[0];
        String query = "SELECT * FROM users WHERE name = '" + userInput + "'";
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            System.out.println("User: " + rs.getString("name"));
        }

        // 🚨 Vulnerability: Weak Hashing (MD5)
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hash = md.digest("password123".getBytes());
        System.out.println("Hashed Password: " + new String(hash));

        conn.close();
    }
}
