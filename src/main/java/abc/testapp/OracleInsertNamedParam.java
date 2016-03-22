package abc.testapp;

import abc.java.sql.NamedParameterPreparedStatement;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 *
 * @autor abc
 * @modified abc on 20.03.2016.
 */
public class OracleInsertNamedParam
{
   /**
    *
    * create table TMP_TEST_TYPES (
    *    F_N1           NUMBER(1),
    *    F_N9           NUMBER(9),
    *    F_N18          NUMBER(18),
    *    F_D            DATE,
    *    F_C1           CHAR(1),
    *    F_VC32         VARCHAR2(32),
    *    F_VC48	   VARCHAR2(48),
    *    F_VC128        VARCHAR2(128),
    *    F_VC250        VARCHAR2(250),
    *    F_VC1024       VARCHAR2(1024),
    *    F_VC4000       VARCHAR2(4000),
    *    F_M            NUMBER(20,6),
    *    F_F            NUMBER(20,10),
    *    F_C            NUMBER(18,15))
    *
    * @param args
    * @throws SQLException
    * @throws IOException
    * @throws ClassNotFoundException
    */
   public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException
   {
      DbUtilsOra db = new DbUtilsOra();
      Connection conn = db.getConnection();

      String sql = "insert into TMP_TEST_TYPES " +
            "              ( F_N1,  F_N9,  F_N18,  F_D,  F_C1,  F_VC32,  F_VC48,  F_VC128,  F_VC250,  F_VC1024, " +
            "                F_VC4000,  F_M,  F_F,  F_C) " +
            "       values (:F_N1, :F_N9, :F_N18, :F_D, :F_C1, :F_VC32, :F_VC48, :F_VC128, :F_VC250, :F_VC1024, " +
            "               :F_VC4000, :F_M, :F_F, :F_C)";

      NamedParameterPreparedStatement n = NamedParameterPreparedStatement.create(conn, sql);
      n.setInt("F_N1", (int) 1);
      n.setInt("F_N9", (int) 2);
      n.setLong("F_N18", (long) 2334);
      n.setLocalDateTime("F_D", LocalDateTime.now());
      n.setString("F_C1", "F");
      n.setString("F_VC32", "dfdsfsfs");
      n.setString("F_VC48", "dfgdhfghfghfgh");
      n.setString("F_VC128", "fgdgdgdfgdfgdfgdfgdfgd");
      n.setString("F_VC250", "dfgfgghfghfghdfgfgbfgbbgbfgbfgbfgbfgbf");
      n.setString("F_VC1024", "dfgdfgdfdd bd dvscascascacasdqwedqweqweqweqweqweqwe");
      n.setString("F_VC4000", "dfvdfbsdbfbdfbergtretgerwefwefwedwedqeqw e  qwe   w  qw qw qwq   wq wq ");
      n.setBigDecimal("F_M", new BigDecimal("125565655.325656"));
      n.setBigDecimal("F_F", new BigDecimal("567456.3562"));
      n.setBigDecimal("F_C", new BigDecimal("846.24565645456"));

      n.executeAndClose();
   }
}
