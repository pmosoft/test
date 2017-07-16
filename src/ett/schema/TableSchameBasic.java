//package ett.schema;
//
//import java.util.List;
//
//import ett.dbms.DbmsInfo;
//
//public class TableSchameBasic implements TableSchema {
//
//	@Override
//	public List<TableSchemaVo> makeTableSchema(DbmsInfo dbmsInfo, String owner,
//			String tableNm) {
//
//		String query = "";
//
//		// List<TableSchemaVo> tableSchemaVoList = new
//		// ArrayList<TableSchemaVo>();
//
//		try {
//			fromDBConn(dbmsInfo);
//			fromStmt = fromConn.createStatement();
//
//			query = dbmsInfo.qryTableSchema(owner, tableNm, owner + "."
//					+ tableNm);
//			System.out.println(query);
//			fromRs = fromStmt.executeQuery(query);
//			while (fromRs.next()) {
//				TableSchemaVo tableSchemaVo = new TableSchemaVo();
//				tableSchemaVo.setTableNm(fromRs.getString(1));
//				tableSchemaVo.setTableHanNm(fromRs.getString(2));
//				tableSchemaVo.setColumnId(fromRs.getInt(3));
//				tableSchemaVo.setColumnNm(fromRs.getString(4));
//				tableSchemaVo.setColumnHanNm(fromRs.getString(5));
//				tableSchemaVo.setDataType(fromRs.getString(6));
//				tableSchemaVo.setDataPrecision(fromRs.getInt(7));
//				tableSchemaVo.setDataScale(fromRs.getInt(8));
//				tableSchemaVo.setNullable(fromRs.getString(9));
//				tableSchemaVo.setPk(fromRs.getString(10));
//
//				tableSchemaVo.print();
//
//				this.tableSchemaVoList.add(tableSchemaVo);
//
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			fromDBClose();
//		}
//
//		return tableSchemaVoList;
//	}
//
//	@Override
//	public void makeTableSchemaOracleWithOracle() {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void makeTableSchemaHsqldbWithOracle() {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void makeTableSchemaOracleWithHsqldb() {
//		// TODO Auto-generated method stub
//
//	}
//
//}
