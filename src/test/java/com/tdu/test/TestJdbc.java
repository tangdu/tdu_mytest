package com.tdu.test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

public class TestJdbc {
	public static void main(String[] args) {
		DbUtils.loadDriver("oracle.jdbc.driver.OracleDriver");
		Connection connection =null;
		try {
			connection = DriverManager.getConnection(
					"jdbc:oracle:thin:@10.10.0.138:1521:ggsundb1",
					"credit",
					"cdxcredit");
			 QueryRunner queryRunner = new QueryRunner(); 
			 BigDecimal a1=queryRunner.query(connection, "select count(1) from ap_pbc_credit_card_detail", new ScalarHandler());
			 System.out.println(a1.toBigInteger());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
