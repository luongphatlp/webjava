
package DATABASE;

import java.sql.Connection;
import java.sql.DriverManager;
public class MyConnection {
    public static Connection getConnection() {
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection conn = DriverManager.getConnection(
            "jdbc:mysql://localhost:3309/store",
            "root",
            ""
        );

        System.out.println("✅ Kết nối OK");
        return conn;

    } catch (Exception e) {
        System.out.println("❌ LỖI THẬT Ở ĐÂY:");
        e.printStackTrace(); // ← bắt buộc
    }
    return null;
}
}
