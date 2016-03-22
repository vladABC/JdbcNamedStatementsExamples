package abc.testapp;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Postgres DB utils
 * @autor abc
 * @modified abc on 20.03.2016.
 */
public class DbUtilsPg extends DbUtils
{
   @Override
   public Connection getConnection() throws SQLException, ClassNotFoundException, IOException
   {
      Connection result = super.getConnection();
      if (result == null)
      {
         String destinationDriver = "org.postgresql.Driver";
         String connectionString = "jdbc:postgresql://localhost:5432/dbname";
         String login = "db_role";
         String password = "password";

         Properties properties = new Properties();
         properties.load(new FileInputStream("src/main/resources/config.properties"));

         destinationDriver = properties.getProperty("postgres.connection.driver_class");
         connectionString = properties.getProperty("postgres.connection.url");
         login = properties.getProperty("postgres.connection.username");
         password = properties.getProperty("postgres.connection.password");

         result = connect(destinationDriver, connectionString, login, password);
      }
      return result;
   }
}
