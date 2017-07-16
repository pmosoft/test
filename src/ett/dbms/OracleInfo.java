package ett.dbms;

public class OracleInfo implements DbmsInfo {

	@Override
	public String dbConn() {
		return "jdbc:oracle:thin:@localhost:1521/ORCL";
	}

	@Override
	public String dbUser() {
		return "AMLS";
	}

	@Override
	public String dbPassword() {
		return "AMLS";
	}

	@Override
	public String dbDriver() {
		return "oracle.jdbc.driver.OracleDriver";
	}

	@Override
	public String qryTableSchema(String owner, String tableNm,
			String ownerTableNm) {
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
		query += "        WHERE  TABLE_NAME ='" + tableNm
				+ "'                         ";
		query += "        AND    INDEX_NAME IN (SELECT INDEX_NAME FROM ALL_INDEXES ";
		query += "                             WHERE TABLE_NAME ='" + tableNm
				+ "'     ";
		query += "                             AND   UNIQUENESS = 'UNIQUE')) C     ";
		query += " WHERE  A.OWNER = B.OWNER                                        ";
		query += " AND    A.TABLE_NAME  = B.TABLE_NAME                             ";
		query += " AND    A.COLUMN_NAME = B.COLUMN_NAME                            ";
		query += " AND    A.TABLE_NAME  = C.TABLE_NAME(+)                          ";
		query += " AND    A.COLUMN_NAME = C.COLUMN_NAME(+)                         ";
		query += " AND    A.OWNER       ='" + owner
				+ "'                               ";
		query += " AND    A.TABLE_NAME  ='" + tableNm
				+ "'                             ";
		query += " ORDER BY A.TABLE_NAME,A.COLUMN_ID                               ";

		return query;
	}

	public boolean isChar(String dataType) {
		boolean TF = true;
		if (dataType.equals("CHAR") || dataType.equals("VARCHAR")
				|| dataType.equals("VARCHAR2"))
			TF = true;
		else
			TF = false;
		//System.out.println("isChar="+TF+" dataType="+dataType);
		return TF;
	}

	public boolean isDate(String dataType) {
		boolean TF = true;
		if (dataType.equals("DATE") || dataType.equals("TIMESTAMP"))
			TF = true;
		else
			TF = false;
		
		//System.out.println("isDate="+TF+" dataType="+dataType);
		
		return TF;
	}
	
}
