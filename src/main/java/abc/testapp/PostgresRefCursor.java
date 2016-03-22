package abc.testapp;

import abc.java.sql.NamedParameterCallableStatement;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * create or replace function SelectTestTypes
 *   (vUserID        in numeric(10))
 * returns refcursor as
 * $$ declare
 *    result_cursor refcursor;
 * begin
 *    open result_cursor for
 *    select * from TMP_TEST_TYPES;
 *    return result_cursor;
 * end;
 * $$ language plpgsql security definer;
 *
 * @autor abc
 * @modified abc on 20.03.2016.
 */
public class PostgresRefCursor
{
   public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException
   {
      DbUtilsPg db = new DbUtilsPg();
      Connection conn = db.getConnection();

      // We must be inside a transaction for cursors to work.
      conn.setAutoCommit(false);

      String sql = "{ :result_cursor = call SelectTestTypes(:vUserID) }";

      NamedParameterCallableStatement n = NamedParameterCallableStatement.create(conn, sql);
      n.registerOutParameter("result_cursor", Types.OTHER);
      n.setInt("vUserID", 1);
      n.execute();

      ResultSet r = (ResultSet) n.getObject("result_cursor");
      while (r.next())
      {
         int f1 = r.getInt("F_N1");
         int f2 = r.getInt("F_N9");
         long f3 = r.getLong("F_N18");

         // ...

      }
   }
}
