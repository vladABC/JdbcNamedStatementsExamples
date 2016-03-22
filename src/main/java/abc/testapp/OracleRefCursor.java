package abc.testapp;

import abc.java.sql.NamedParameterCallableStatement;
import oracle.jdbc.OracleTypes;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * create or replace procedure SelectTestTypes
 *    (vUserID         in  number,
 *     result_cursor   out sys_refcursor)
 * is
 * begin
 *    open result_cursor for
 *    select * from TMP_TEST_TYPES;
 * end;
 *
 * @autor abc
 * @modified abc on 20.03.2016.
 */
public class OracleRefCursor
{
   public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException
   {
      DbUtilsOra db = new DbUtilsOra();
      Connection conn = db.getConnection();

      String sql = "{ call SelectTestTypes(:vUserID, :result_cursor) }";

      NamedParameterCallableStatement n = NamedParameterCallableStatement.create(conn, sql);
      n.setInt("vUserID", 1);
      n.registerOutParameter("result_cursor", OracleTypes.CURSOR);
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
