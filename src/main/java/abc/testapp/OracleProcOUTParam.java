package abc.testapp;

import abc.java.sql.NamedParameterCallableStatement;
import oracle.jdbc.OracleTypes;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * create or replace procedure SelectTestTypes3
 *   (vUserID         in      integer,
 *    vUserStr        in out  varchar2)
 * is
 * begin
 *    vUserStr := vUserStr || ' ' || TO_CHAR(vUserID);
 * end;
 *
 * @autor abc
 * @modified abc on 20.03.2016.
 */
public class OracleProcOUTParam
{
   public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException
   {
      DbUtilsOra db = new DbUtilsOra();
      Connection conn = db.getConnection();

      String sql = "{ call SelectTestTypes3(:vUserID, :vUserStr) }";

      NamedParameterCallableStatement n = NamedParameterCallableStatement.create(conn, sql);
      n.setInt("vUserID", 1);
      n.setString("vUserStr", "fdfgdffddfgdgdgd");
      n.registerOutParameter("vUserStr", OracleTypes.VARCHAR);

      n.execute();

      String result = n.getString("vUserStr");

      n.close();
   }
}
