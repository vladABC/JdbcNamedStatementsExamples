package abc.testapp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Base DB utils
 * @autor abc
 * @modified abc on 12.03.2016.
 */
public class DbUtils
{
   private Connection _connection = null;

   public Connection getConnection() throws SQLException, ClassNotFoundException, IOException
   {
      return _connection;
   }

   /**
    * Connect to DB
    * @param destinationDriver   DB driver name
    * @param connectionString    DB connectrion string
    * @param login               user login
    * @param password            user password
    * @return
    */
   protected Connection connect(String destinationDriver, String connectionString, String login, String password)
         throws SQLException, ClassNotFoundException
   {
      Class.forName(destinationDriver);
      return _connection = DriverManager.getConnection(connectionString, login, password);
   }

   public void disconnect() throws SQLException
   {
      if (_connection != null) _connection.close();
   }
}
