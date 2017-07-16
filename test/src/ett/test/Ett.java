package ett.test;

import java.util.ArrayList;
import java.util.List;

import ett.dbms.DbmsInfo;
import ett.schema.TableSchemaVo;

public interface Ett {

	public void process();

	public void processTable(String tableNm);

	public void processExtract(String tableNm);

	public void processExtractLoad(String tableNm);

	public void processLoad(String rows, ArrayList<String> rowList);

	public List<TableSchemaVo> makeTableSchema(DbmsInfo dbmsInfo, String owner,
			String tableNm);

	public void isTableSchema();

	public void fromDBConn(DbmsInfo dbmsInfo);

	public void fromDBClose();

	public void toDBConn(DbmsInfo dbmsInfo);

	public void toDBClose();
}
