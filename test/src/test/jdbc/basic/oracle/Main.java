package test.jdbc.basic.oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
	
    public static void main(String[] args) {
    	
    	TestJdbcBasicOracle oracleBasicJdbc = new TestJdbcBasicOracle();
    	oracleBasicJdbc.test01();
    }
}

class TestJdbcBasicOracle {

    public Connection conn = null;
    public Statement stmt = null;
    public ResultSet rs = null;

    TestJdbcBasicOracle() { DBConn(); }

    public void test01(){

        try {
            stmt = conn.createStatement();
            
            String query = "SELECT * FROM emp";        	
            rs = stmt.executeQuery(query);
            while (rs.next()) { 
                String empno = rs.getString(1);
                String ename = rs.getString(2);
                String job = rs.getString(3);
                String mgr = rs.getString(4);
                String hiredate = rs.getString(5);
                String sal = rs.getString(6);
                String comm = rs.getString(7);
                String depno = rs.getString(8);

                System.out.println( 
                    empno + " : " + ename + " : " + job + " : " + mgr
                    + " : " + hiredate + " : " + sal + " : " + comm + " : "
                + depno); 
            }
        } catch ( Exception e ) { e.printStackTrace(); } finally { DBClose(); }
    	
    }
    
    void DBConn(){

        String DB_URL = "jdbc:oracle:thin:@localhost:1521/ORCL";
        String DB_USER = "scott";
        String DB_PASSWORD = "tiger";
       
        try {
        	Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch ( Exception e ) { e.printStackTrace(); } finally {}
    }
    
    void DBClose(){ rs = null; stmt = null; conn = null; }
}

