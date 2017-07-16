package springjsptable.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



import springjsptable.Constant;

public class SqlMapParser extends CommonParser {

	public static void main(String[] args) {

		// -----------------------------------------------------------
		// 소스 정보를 파일과 폴더에서 읽어와 리스트에 저장
		// -----------------------------------------------------------
		List<HashMap<String, String>> srcInfoList = new ArrayList<HashMap<String, String>>();
		SourceFileInfo trace = new SourceFileInfo();
		srcInfoList = trace.readSrcList(Constant.srcPath);

		// -----------------------------------------------------------
		// sqlMap 내에 서비스를 호출하는 정보를 추출하여 리스트에 저장
		// -----------------------------------------------------------
		SqlMapParser sqlMapParser = new SqlMapParser();
		sqlMapParser.setSrcInfoList(srcInfoList);
		sqlMapParser.process();
		
		// DB에 저장
		//sqlMapParser.insertParseResult();
		//sqlMapParser.insertSemanticAnalysis();
		
		// 기타 DB에 저장
		sqlMapParser.insertAsafeSqlMapTableInfo01();

		// table 정보 저장
		sqlMapParser.insertAsafeSqlMapTableInfo02();

		
		// table 정보 조회
		//sqlMapParser.selectAsafeSqlMapTableInfo03();
		
	}

	public SqlMapParser() {
		System.out.println("Start sqlMapParser");
	}

	String fileName = "";
	String filePathName = "";
	String src = "";
	String src2 = "";
	String pos = "";
	String parseResult = "";
	
	
	@Override
	public void process() {
		System.out.println("srcInfoList.size=" + srcInfoList.size());
		
		for (int i = 0; i < srcInfoList.size(); i++) {
		//for (int i = 0; i < 247; i++) {

			fileName = srcInfoList.get(i).get("fileName");
			filePathName = srcInfoList.get(i).get("filePathName");
			
			if (fileName.matches("(?i).*SQL.xml")) {
				//System.out.println(i+"="+fileName);
				src = srcInfoList.get(i).get("src");
				src = elimateComment(src,"java");
				parseBasic(src);
			}	
		}
	}

	public void parseBasic(String src) {

		Pattern p; Matcher m;
		String parseRule; boolean a;

		//parseRule = "(?i)( )id( )?=( )?(\")[a-zA-Z0-9]+(.)[a-zA-Z0-9]+(\") ";
		parseRule = "(?i)(<)(select|insert|update|delete)( )+id( )*=( )*(\")[^\"]+(\") ";
		//parseRule = "(?i)( )id( )?=( )?(\")[a-zA-Z0-9]+(.)+[a-zA-Z0-9_\\.]+(\") ";
		//parseRule = "(?i)( )id( )?=( )?.*";
		p = Pattern.compile(parseRule); m = p.matcher(src); a = false;
		while (a = m.find()) { 
			pos = String.valueOf(m.start()); parseResult = m.group();
			parseResult = parseResult.replace("<select","").replace("<insert","").replace("<update","").replace("<delete","");
			parseResult = parseResult.replace("id","").replace("=","").replace(" ","").replace("\"","");
			setParseResultList();
		}		
		
		parseRule = "(?i)[a-zA-Z]{3}[0-9]{3}T";
		p = Pattern.compile(parseRule); m = p.matcher(src); a = false;
		while (a = m.find()) { 
			pos = String.valueOf(m.start()); parseResult = m.group();
			setParseResultList();
		}		
		
	}

	public void setParseResultList() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("fileName"    , fileName);
		map.put("filePathName", filePathName);
		map.put("kind", "SQL1");
		map.put("pos", pos );
		map.put("parseResult", parseResult);
		
		//System.out.println(parseResult);
		
		System.out.println("map="+map);
		
		this.parseResultList.add(map);
	}
	
	public void insertParseResult() {
		dao.deleteParseResult("SQL1");
		dao.insertParseResult(parseResultList);
	}

	public void insertSemanticAnalysis() {
		dao.deleteParseResult("SQL2");
		dao.insertSqlMapSemanticAnalysis();
	}
 
	public void insertAsafeSqlMapTableInfo01() {
		dao.deleteParseResult("TABLE1");
		dao.insertAsafeSqlMapTableInfo01();
	}

	public void insertAsafeSqlMapTableInfo02() {
		dao.deleteParseResult("TABLE2");
		dao.insertAsafeSqlMapTableInfo02();
	}	
	
}
