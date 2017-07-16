package test.jdbc.basic.hsqldb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
	
    public static void main(String[] args) {
    	
    	TestJdbcBasicHsqldb oracleBasicJdbc = new TestJdbcBasicHsqldb();
    	oracleBasicJdbc.test01();
    }
}

class TestJdbcBasicHsqldb {

    public Connection conn = null;
    public Statement stmt = null;
    public ResultSet rs = null;

    TestJdbcBasicHsqldb() { DBConn(); }

    public void test01(){

        try {
            stmt = conn.createStatement();
            
            String query = "SELECT * FROM temp01";        	
            rs = stmt.executeQuery(query);
            while (rs.next()) { 

                System.out.println(rs.getString(1)); 
            }
        } catch ( Exception e ) { e.printStackTrace(); } finally { DBClose(); }
    	
    }
    
    void DBConn(){
    	
        String DB_URL = "jdbc:hsqldb:hsql://localhost/testdb";
        String DB_USER = "sa";
        String DB_PASSWORD = "";
       
        try {
        	Class.forName("org.hsqldb.jdbcDriver");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch ( Exception e ) { e.printStackTrace(); } finally {}
    }
    
    void DBClose(){ rs = null; stmt = null; conn = null; }
}

