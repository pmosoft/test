package ett.dbms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbConn {

	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;

	public Connection conn(DbmsInfo dbmsInfo) {
		try {
			Class.forName(dbmsInfo.dbDriver());
			conn = DriverManager.getConnection(dbmsInfo.dbConn(),
					dbmsInfo.dbUser(), dbmsInfo.dbPassword());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		
		return conn;
	}

	public void close() {
		rs = null;
		stmt = null;
		conn = null;
	}

}
