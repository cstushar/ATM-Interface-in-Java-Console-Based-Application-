package util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class JdbcUtil {

	// Loading and register the Driver in a static way
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	// private static Connection connection = null;
	private JdbcUtil() {
	}

	// returning the connection object , provided by the HikariCP (connection pool)
	public static Connection get_connection() throws SQLException, IOException {
		String configFile = "src\\properties\\application.properties";
		HikariConfig hikariConfig = new HikariConfig(configFile);
		HikariDataSource dataSource = new HikariDataSource(hikariConfig);

		return dataSource.getConnection();
	}
}
