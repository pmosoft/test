package ett.dbms;

public class HsqldbInfo implements DbmsInfo {

	@Override
	public String dbConn() {
		return "jdbc:hsqldb:hsql://localhost/testdb";
	}

	@Override
	public String dbUser() {
		return "sa";
	}

	@Override
	public String dbPassword() {
		return "";
	}

	@Override
	public String dbDriver() {
		return "org.hsqldb.jdbcDriver";
	}

	@Override
	public String qryTableSchema(String owner, String tableNm, String ownerTableNm) {
		String query = "";
		query += " SELECT  A.TABLE_NAME                           AS tableNm       ";
		query += "        ,(SELECT MAX(COMMENTS) FROM ALL_TAB_COMMENTS WHERE TABLE_NAME = A.TABLE_NAME) AS tableHanNm ";
		query += "        ,A.COLUMN_ID                            AS columnId      ";
		query += "        ,A.COLUMN_NAME                          AS columnNm      ";
		query += "        ,B.COMMENTS                             AS columnHanNm   ";
		query += "        ,A.DATA_TYPE                            AS dataType      ";
		query += "        ,A.DATA_PRECISION                       AS dataPrecision ";
		query += "        ,A.DATA_PRECISION                       AS dataScale     ";
		query += "        ,A.NULLABLE                             AS NULLABLE      ";
		query += "        ,DECODE(C.COLUMN_NAME,NULL,NULL,'PK')   AS PK            ";
		query += " FROM   ALL_TAB_COLUMNS  A,                                      ";
		query += "        ALL_COL_COMMENTS B,                                      ";
		query += "       (SELECT DISTINCT TABLE_NAME,COLUMN_NAME                   ";
		query += "        FROM   ALL_IND_COLUMNS                                   ";
		query += "        WHERE  TABLE_NAME ='"+tableNm+"'                         ";
		query += "        AND    INDEX_NAME IN (SELECT INDEX_NAME FROM ALL_INDEXES ";
		query += "                             WHERE TABLE_NAME ='"+tableNm+"'     ";
		query += "                             AND   UNIQUENESS = 'UNIQUE')) C     ";
		query += " WHERE  A.OWNER = B.OWNER                                        ";
		query += " AND    A.TABLE_NAME  = B.TABLE_NAME                             ";
		query += " AND    A.COLUMN_NAME = B.COLUMN_NAME                            ";
		query += " AND    A.TABLE_NAME  = C.TABLE_NAME(+)                          ";
		query += " AND    A.COLUMN_NAME = C.COLUMN_NAME(+)                         ";
		query += " AND    A.OWNER       ='"+owner+"'                               ";
		query += " AND    A.TABLE_NAME  ='"+tableNm+"'                             ";
		query += " ORDER BY A.TABLE_NAME,A.COLUMN_ID                               ";

		return query;
	}


	@Override
	public boolean isChar(String dataType) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isDate(String dataType) {
		// TODO Auto-generated method stub
		return false;
	}
}
