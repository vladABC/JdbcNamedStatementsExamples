package abc.testapp;

import abc.java.sql.NamedParameterCallableStatement;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

/**
 * create or replace function SelectTestTypes3
 *   (vUserID         in     int4,
 *    vUserStr        in out varchar(250))
 * as $$ declare
 * begin
 *    vUserStr := vUserStr || ' ' || vUserID;
 * end;
 * $$ language plpgsql;
 *
 *
 * @autor abc
 * @modified abc on 20.03.2016.
 */
public class PostgresProcOUTParam
{
   public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException
   {
      DbUtilsPg db = new DbUtilsPg();
      Connection conn = db.getConnection();

      String sql = "{ call SelectTestTypes3(:vUserID, :vUserStr) }";

      NamedParameterCallableStatement n = NamedParameterCallableStatement.create(conn, sql);
      n.setInt("vUserID", 1);
      n.setString("vUserStr", "fdfgdffddfgdgdgd");
      n.registerOutParameter("vUserStr", Types.VARCHAR);

      n.execute();

      String result = n.getString("vUserStr");

      n.close();
   }
}
