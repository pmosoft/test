package ett.schema;

public interface TableSchema {

	public void makeTableSchemaVo();
	
	public void makeTableSchemaOracleWithOracle();

	public void makeTableSchemaHsqldbWithOracle();

	public void makeTableSchemaOracleWithHsqldb();
	
}
