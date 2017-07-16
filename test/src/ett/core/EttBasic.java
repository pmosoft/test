package ett.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ett.App;
import ett.dbms.DbConn;
import ett.dbms.DbmsInfo;
import ett.dbms.HsqldbInfo;
import ett.dbms.OracleInfo;
import ett.schema.TableSchemaVo;

public class EttBasic implements Ett {

	Connection fromConn = null;
	Statement fromStmt = null;
	ResultSet fromRs = null;

	Connection toConn = null;
	Statement toStmt = null;
	ResultSet toRs = null;

	DbmsInfo fromDbmsInfo;
	DbmsInfo toDbmsInfo;

	String owner = "";
	String tableNm = "";
	String ownerTableNm = "";

	List<TableSchemaVo> tableSchemaVoList = new ArrayList<TableSchemaVo>();

    // main        --22
	public static void main(String[] args) {
		EttBasic ettBasic = new EttBasic();
		
		ettBasic.processFromOracleToHsqldb(new OracleInfo(),new HsqldbInfo());
		ettBasic.process();
	}

	@Override
	public void process() {

		//processLoad();
		
		owner = "AMLS";
		tableNm = "RBA230T";
		makeTableSchema(fromDbmsInfo, owner, tableNm);
		processExtractLoad(tableNm);

	}

	public void processFromOracleToHsqldb(DbmsInfo fromDbmsInfo,DbmsInfo toDbmsInfo) {
		this.fromDbmsInfo = fromDbmsInfo;
		this.toDbmsInfo = toDbmsInfo;
	}

	@Override
	public void processTable(String tableNm) {
		// TODO Auto-generated method stub

	}

	@Override
	public void processExtract(String tableNm) {
		// TODO Auto-generated method stub

	}

	@Override
	public void processExtractLoad(String tableNm) {

		String fromQuery = "";
		String toQuery = "";
		int rowCnt = 0;
		int totalRowCnt = 0;
		String ColumnValue = "";
		ArrayList<String> rowList = new ArrayList<String>();
		String rows = "";
		DbConn dbConn = new DbConn();

		
		try {
			fromConn = dbConn.conn(fromDbmsInfo);
			fromStmt = fromConn.createStatement();

			fromQuery = "SELECT * FROM " + tableNm;
			//fromQuery += " WHERE ROWNUM < 3 ";
			System.out.println(fromQuery);
			System.out.println(tableSchemaVoList.size());

			fromRs = fromStmt.executeQuery(fromQuery);
			while (fromRs.next()) {

				toQuery += "INSERT INTO " + tableNm + " VALUES (";
				for (int i = 0; i < tableSchemaVoList.size(); i++) {
					
					ColumnValue = fromRs.getString(i + 1);
					//System.out.println("ColumnValue111="+ColumnValue);
					//System.out.println("tableSchemaVoList111="+tableSchemaVoList.get(i).getColumnNm());
					//System.out.println("111="+tableSchemaVoList.get(i).getDataType());
					if (fromDbmsInfo.isChar(tableSchemaVoList.get(i)
							.getDataType())) {
						//ColumnValue = fromRs.getString(i + 1).replace("null", "").replace("NULL", "");
						ColumnValue = (ColumnValue != null)?ColumnValue:"";
						toQuery += "'" + ColumnValue;
						toQuery += (i < tableSchemaVoList.size() - 1) ? "',"
								: "');";
						//System.out.println("toQuery1="+toQuery);
					
					} else if (fromDbmsInfo.isDate(tableSchemaVoList.get(i)
							.getDataType())) {
						toQuery += "SYSDATE";
						toQuery += (i < tableSchemaVoList.size() - 1) ? ","
								: ");";
					} else {						
						ColumnValue = (ColumnValue.trim() != null)?ColumnValue:"0";
						//System.out.println("333="+ColumnValue);

						toQuery += ColumnValue;
						toQuery += (i < tableSchemaVoList.size() - 1) ? ","
								: ");";
						//System.out.println("toQuery3="+toQuery);
						
					}
					//System.out.println("");

				}

				System.out.println(toQuery);
				rowList.add(toQuery);
				rows += toQuery;
				toQuery = "";

				totalRowCnt++;
				rowCnt++;
				//----------------------------------
				// Load as much commitCnt
				//----------------------------------
				if (rowCnt % App.commitCnt == 0) {
					System.out.println("totalRowCnt=" + totalRowCnt + ":" + "rowCnt=" + rowCnt);
					processLoad(rows, rowList);
					rows = "";
					rowCnt = 0;
				}
			}

			//----------------------------------
			// Load rest rows after commitCnt
			//----------------------------------
			if (rowCnt < App.commitCnt) {
				System.out.println("totalRowCnt=" + totalRowCnt + ":" + "rowCnt=" + rowCnt);
				processLoad(rows, rowList);
				rows = "";
				rowCnt = 0;
			}

			//System.out.println("totalRowCnt=" + totalRowCnt + ":" + "rowCnt=" + rowCnt);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbConn.close();
		}
	}

	@Override
	public void processLoad(String rows, ArrayList<String> rowList) {
		// TODO Auto-generated method stub
		try {
			toDBConn(toDbmsInfo);
			toStmt = toConn.createStatement();
			//System.out.println(rows);
			toStmt.executeQuery(rows);
		} catch (Exception e) {
			String errRow = "";
			try {				
				for(int i=0;i<rowList.size();i++){
					errRow = rowList.get(i);
					toStmt.executeQuery(rowList.get(i));
				}
			} catch (Exception e1) {
				System.out.println("err row:"+errRow);
				e1.printStackTrace();
			}			
			
		} finally {
			toDBClose();
		}
	}

	public void processLoad(ArrayList rows) {
	}
	
	public void processLoad() {

		String query = "";
		try {
			toDBConn(toDbmsInfo);
			toStmt = toConn.createStatement();
			
			query += "INSERT INTO RBA230T VALUES ('201705','0000031001','3 ','03','0000000035','1',358494714,'','','','','');";
			query += "INSERT INTO RBA230T VALUES ('201705','0000031001','3 ','03','0000000035','1',358494714,'','','','','');";
			System.out.println(query);
			toStmt.executeQuery(query);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			toDBClose();
		}


	}	
	
	
	@Override
	public List<TableSchemaVo> makeTableSchema(DbmsInfo dbmsInfo, String owner,
			String tableNm) {

		String query = "";

		// List<TableSchemaVo> tableSchemaVoList = new
		// ArrayList<TableSchemaVo>();

		try {
			fromDBConn(dbmsInfo);
			fromStmt = fromConn.createStatement();

			query = dbmsInfo.qryTableSchema(owner, tableNm, owner + "."
					+ tableNm);
			System.out.println(query);
			fromRs = fromStmt.executeQuery(query);
			while (fromRs.next()) {
				TableSchemaVo tableSchemaVo = new TableSchemaVo();
				tableSchemaVo.setTableNm(fromRs.getString(1));
				tableSchemaVo.setTableHanNm(fromRs.getString(2));
				tableSchemaVo.setColumnId(fromRs.getInt(3));
				tableSchemaVo.setColumnNm(fromRs.getString(4));
				tableSchemaVo.setColumnHanNm(fromRs.getString(5));
				tableSchemaVo.setDataType(fromRs.getString(6));
				tableSchemaVo.setDataPrecision(fromRs.getInt(7));
				tableSchemaVo.setDataScale(fromRs.getInt(8));
				tableSchemaVo.setNullable(fromRs.getString(9));
				tableSchemaVo.setPk(fromRs.getString(10));

				tableSchemaVo.print();

				this.tableSchemaVoList.add(tableSchemaVo);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			fromDBClose();
		}

		return tableSchemaVoList;
	}

	@Override
	public void isTableSchema() {
		// TODO Auto-generated method stub

	}

	@Override
	public void fromDBConn(DbmsInfo dbmsInfo) {
		try {
			Class.forName(dbmsInfo.dbDriver());
			fromConn = DriverManager.getConnection(dbmsInfo.dbConn(),
					dbmsInfo.dbUser(), dbmsInfo.dbPassword());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}

	@Override
	public void fromDBClose() {
		fromRs = null;
		fromStmt = null;
		fromConn = null;
	}

	@Override
	public void toDBConn(DbmsInfo dbmsInfo) {
		try {
			Class.forName("org.hsqldb.jdbcDriver");
			toConn = DriverManager.getConnection(dbmsInfo.dbConn(),
					dbmsInfo.dbUser(), dbmsInfo.dbPassword());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}

	@Override
	public void toDBClose() {
		toRs = null;
		toStmt = null;
		toConn = null;
	}

	public DbmsInfo getFromDbmsInfo() {
		return fromDbmsInfo;
	}

	public void setFromDbmsInfo(DbmsInfo fromDbmsInfo) {
		this.fromDbmsInfo = fromDbmsInfo;
	}

	public DbmsInfo getToDbmsInfo() {
		return toDbmsInfo;
	}

	public void setToDbmsInfo(DbmsInfo toDbmsInfo) {
		this.toDbmsInfo = toDbmsInfo;
	}

}
