package abc.testapp;

import abc.java.sql.NamedParameterPreparedStatement;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 * create or replace function LoadRecordTestTypes (
 *    vF_N1           in int4,
 *    vF_N9           in int4,
 *    vF_N18          in int8,
 *    vF_D            in timestamptz,
 *    vF_C1           in char(1),
 *    vF_VC32         in varchar(32),
 *    vF_VC48         in varchar(48),
 *    vF_VC128        in varchar(128),
 *    vF_VC250        in varchar(250),
 *    vF_VC1024       in varchar(1024),
 *    vF_VC4000       in varchar(4000),
 *    vF_T            in text,
 *    vF_M            in numeric(20,6),
 *    vF_F            in numeric(20,10),
 *    vF_C            in numeric(18,15))
 * returns void as
 * $$ declare
 * begin
 *    insert into TMP_TEST_TYPES
 *           (F_N1, F_N9, F_N18, F_D, F_C1, F_VC32, F_VC48, F_VC128, F_VC250, F_VC1024, F_VC4000, F_T, F_M, F_F, F_C)
 *    values (vF_N1, vF_N9, vF_N18, vF_D, vF_C1, vF_VC32, vF_VC48, vF_VC128, vF_VC250, vF_VC1024, vF_VC4000, vF_T, vF_M, vF_F, vF_C);
 * end;
 * $$ language plpgsql security definer;
 *
 * @autor abc
 * @modified abc on 20.03.2016.
 */
public class PostgresProcNamedParam
{
   public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException
   {
      DbUtilsPg db = new DbUtilsPg();
      Connection conn = db.getConnection();

      String sql = "select * from LoadRecordTestTypes(" +
            ":vF_N1, :vF_N9, :vF_N18, :vF_D, :vF_C1, :vF_VC32, :vF_VC48, :vF_VC128," +
            ":vF_VC250, :vF_VC1024, :vF_VC4000, :vF_T, :vF_M, :vF_F, :vF_C)";

      NamedParameterPreparedStatement n = NamedParameterPreparedStatement.create(conn, sql);

      n.setBigDecimal("vF_M", new BigDecimal("125565655.325656"));
      n.setBigDecimal("vF_F", new BigDecimal("567456.3562"));
      n.setBigDecimal("vF_C", new BigDecimal("846.24565645456"));

      n.setInt("vF_N1", (int) 1);
      n.setInt("vF_N9", (int) 2);
      n.setLong("vF_N18", (long) 2334);
      n.setLocalDateTime("vF_D", LocalDateTime.now());
      n.setString("vF_C1", "F");
      n.setString("vF_VC32", "dfdsfsfs");
      n.setString("vF_VC48", "dfgdhfghfghfgh");
      n.setString("vF_VC128", "fgdgdgdfgdfgdfgdfgdfgd");
      n.setString("vF_VC250", "dfgfgghfghfghdfgfgbfgbbgbfgbfgbfgbfgbf");
      n.setString("vF_VC1024", "dfgdfgdfdd bd dvscascascacasdqwedqweqweqweqweqweqwe");
      n.setString("vF_VC4000", "dfvdfbsdbfbdfbergtretgerwefwefwedwedqeqwsklfjs;kfjsd;lfjskjf;lsdkjs");
      n.setString("vF_T", "wrtupertupeiruteupteupteriuptoeruiptoieurptioeruptoiuerpotiueprotuieprotuieportueporiu");

      n.executeAndClose();
   }
}
