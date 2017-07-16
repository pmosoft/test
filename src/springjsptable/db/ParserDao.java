package springjsptable.db;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ParserDao {

	public void insertElinateJspReduction();
	
	public void deleteParseResult(String kind);
	public void insertParseResult(List<HashMap<String, Object>> parseResultList);

	public void insertControllerSemanticAnalysis();
	public void insertServiceSemanticAnalysis();
	public void insertSqlMapSemanticAnalysis();

	public void insertAsafeSqlMapTableInfo01();
	public void insertAsafeSqlMapTableInfo02();
	public void selectAsafeSqlMapTableInfo03();

	
	public void insertWebSrcFileInfo(final List<HashMap<String, String>> fileInfoList);
	public void insertFileTraceInfo(final List<HashMap<String, String>> fileTraceInfoList);
	
	public void createTable();
	public void deleteTest();
	
}
