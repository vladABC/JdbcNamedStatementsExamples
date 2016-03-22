package abc.testapp;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Oracle DB utils
 * @autor abc
 * @modified abc on 20.03.2016.
 */
public class DbUtilsOra extends DbUtils
{
   @Override
   public Connection getConnection() throws SQLException, ClassNotFoundException, IOException
   {
      Connection result = super.getConnection();
      if (result == null)
      {
         String destinationDriver = "oracle.jdbc.OracleDriver";
         String connectionString = "jdbc:oracle:oci:@DBNAME";
         String login = "db_schema";
         String password = "password";

         Properties properties = new Properties();
         properties.load(new FileInputStream("src/main/resources/config.properties"));

         destinationDriver = properties.getProperty("oracle.connection.driver_class");
         connectionString = properties.getProperty("oracle.connection.url");
         login = properties.getProperty("oracle.connection.username");
         password = properties.getProperty("oracle.connection.password");

         result = connect(destinationDriver, connectionString, login, password);
      }
      return result;
   }
}
