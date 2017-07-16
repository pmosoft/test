package ett.dbms;

public interface DbmsInfo {
	
	public String dbConn();
	public String dbUser();
	public String dbPassword();
	public String dbDriver();
	
	public String qryTableSchema(String owner, String tableNm, String ownerTableNm);
    public boolean isChar(String dataType);
    public boolean isDate(String dataType);

	
}	
