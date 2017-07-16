package ett.schema;

public class TableSchemaVo {

	String tableNm;
	String tableHanNm;
	int columnId;
	String columnNm;
	String columnHanNm;
	String dataType;
	int dataPrecision;
	int dataScale;
	String nullable;
	String pk;

	public String getTableNm() {
		return tableNm;
	}

	public void setTableNm(String tableNm) {
		this.tableNm = tableNm;
	}

	public String getTableHanNm() {
		return tableHanNm;
	}

	public void setTableHanNm(String tableHanNm) {
		this.tableHanNm = tableHanNm;
	}

	public int getColumnId() {
		return columnId;
	}

	public void setColumnId(int columnId) {
		this.columnId = columnId;
	}

	public String getColumnNm() {
		return columnNm;
	}

	public void setColumnNm(String columnNm) {
		this.columnNm = columnNm;
	}

	public String getColumnHanNm() {
		return columnHanNm;
	}

	public void setColumnHanNm(String columnHanNm) {
		this.columnHanNm = columnHanNm;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public int getDataPrecision() {
		return dataPrecision;
	}

	public void setDataPrecision(int dataPrecision) {
		this.dataPrecision = dataPrecision;
	}

	public int getDataScale() {
		return dataScale;
	}

	public void setDataScale(int dataScale) {
		this.dataScale = dataScale;
	}

	public String getNullable() {
		return nullable;
	}

	public void setNullable(String nullable) {
		this.nullable = nullable;
	}

	public String getPk() {
		return pk;
	}

	public void setPk(String pk) {
		this.pk = pk;
	}

	public void print() {

		String printRow = "";
		printRow += tableNm + "|";
		printRow += tableHanNm + "|";
		printRow += columnId + "|";
		printRow += columnNm + "|";
		printRow += columnHanNm + "|";
		printRow += dataType + "|";
		printRow += dataPrecision + "|";
		printRow += dataScale + "|";
		printRow += nullable + "|";
		printRow += pk + "|";

		System.out.println("printRow=" + printRow);

		
//		System.out.println("tableNm=" + tableNm);
//		System.out.println("tableHanNm=" + tableHanNm);
//		System.out.println("columnId=" + columnId);
//		System.out.println("columnNm=" + columnNm);
//		System.out.println("columnHanNm=" + columnHanNm);
//		System.out.println("dataType=" + dataType);
//		System.out.println("dataPrecision=" + dataPrecision);
//		System.out.println("dataScale=" + dataScale);
//		System.out.println("nullable=" + nullable);
//		System.out.println("pk=" + pk);
//		System.out.println("");
	}

}
