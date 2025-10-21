package SingletonConnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingletonConnection {
    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                // Détection du mode test
                String isTest = System.getProperty("TEST_ENV");
                if ("true".equalsIgnoreCase(isTest)) {
                    // ✅ Base de test H2 en mémoire
                    Class.forName("org.h2.Driver");
                    connection = DriverManager.getConnection("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1", "sa", "");
                    System.out.println("✅ Connexion H2 (mémoire) réussie !");
                } else {
                    // ✅ Base de production MySQL
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    connection = DriverManager.getConnection(
                        "jdbc:mysql://mysql:3306/ecommerce",
                        "root",
                        "root"
                    );
                    System.out.println("✅ Connexion MySQL réussie !");
                }
            } catch (Exception e) {
                System.out.println("❌ Erreur de connexion à la BD : " + e.getMessage());
                e.printStackTrace();
            }
        }
        return connection;
    }
}
