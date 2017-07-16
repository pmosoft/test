package springjsptable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import springjsptable.core.ControllerParser;
import springjsptable.core.SourceFileInfo;
import springjsptable.core.SourceParser;
import springjsptable.db.ParserDaoImpl;


public class Main {
	
	public static void main(String[] args) {
		test2();
	}

	static void test2(){
		
		String configLocation = "springjsptable/db/ParserMybatisOracle.xml"; // src/main/resources/springMybatisOracle.xml
		AbstractApplicationContext ctx = new GenericXmlApplicationContext(configLocation);
		ParserDaoImpl dao = ctx.getBean("ParserDaoImpl",ParserDaoImpl.class);
		 
		/* delete */		
		//dao.deleteTest();
		dao.deleteParseResult("jsp1");

		//ctx.close();
		
		
	}
	
	
	static void test1(){

		//-----------------------------------------------------------
		// 사용 객체 생성
		//-----------------------------------------------------------
		SourceFileInfo trace = new SourceFileInfo();
		List<HashMap<String, String>> fileInfoList = new ArrayList<HashMap<String, String>>();

		String configLocation = "classpath:crud/jdbcTemplateOracle.xml"; // src/test/resources/jdbcTempaleOracle.xml
		AbstractApplicationContext ctx = new GenericXmlApplicationContext(configLocation);
		
		
		List<HashMap<String, String>> fileTraceInfoList;
		//-----------------------------------------------------------
		// 1. 웹경로에서 화면소스 파일 및 폴더 정보를 추출하여 DB에 저장
		//-----------------------------------------------------------
		// 1.1 파일 및 폴더 정보 추출
		fileInfoList = trace.readWebSrcList(Constant.webPath);

		System.out.println("fileInfoList.size()1="+fileInfoList.size());
		
		//-----------------------------------------------------------
		// 2. jsp 소스내에  controller 호출하는  .do 정보 추출
		//-----------------------------------------------------------
//		SourceParser jspParser = new JspParser();
//		jspParser.setFileInfoList(fileInfoList);
//		jspParser.process();
//		
//		fileTraceInfoList = new ArrayList<HashMap<String, String>>(); 
//		fileTraceInfoList = jspParser.getFileParseResultList();
//		dbase.insertFileTraceInfo(fileTraceInfoList);
//		//fileTraceInfoList.clear();

		//-----------------------------------------------------------
		// 3. java,xml 소스
		//-----------------------------------------------------------
		List<HashMap<String, String>> srcInfoList = new ArrayList<HashMap<String, String>>(); 
		srcInfoList = trace.readSrcList(Constant.srcPath);
		
		//-----------------------------------------------------------
		// 4. jsp-controller 내에 서비스를 호출하는 정보보 추출
		//-----------------------------------------------------------
		SourceParser controllerParser = new ControllerParser();
		controllerParser.setSrcInfoList(srcInfoList);
		controllerParser.process();
		
		//fileTraceInfoList = new ArrayList<HashMap<String, String>>(); 
		//dbase.insertFileTraceInfo(fileTraceInfoList);
		//fileTraceInfoList.clear();
		
		//List<Map<String, Object>> fileTraceInfoList2 =  new ArrayList<Map<String, Object>>();
		//fileTraceInfoList2 = dbase.selectEmpMulti(1);
		//System.out.println("fileTraceInfoList2.size="+fileTraceInfoList2.size());
		//System.out.println("fileTraceInfoList2.size="+fileTraceInfoList2.get(0).get("file_nm"));
		//System.out.println("fileTraceInfoList2.size="+fileTraceInfoList2.get(0).get("sq"));
		
		//-----------------------------------------------------------
		// 엑셀 출력
		//-----------------------------------------------------------		
	} 

}
