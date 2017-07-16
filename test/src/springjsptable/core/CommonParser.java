package springjsptable.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import springjsptable.db.ParserDaoImpl;

public class CommonParser implements SourceParser {

	public static void main(String[] args) {
		CommonParser parse = new CommonParser();

		List<HashMap<String, String>> fileInfoList = new ArrayList<HashMap<String, String>>(); 
		HashMap<String, String> map = new HashMap<String, String>();
		//map.put("fileName", "COM110MI00.jsp");
		//map.put("pathName", "C:/A-SAFE/workspace/a-safe/src/main/webapp/WEB-INF/jsp/com/");

		map.put("fileName", "AML301MI00.jsp");
		map.put("pathName", "C:/A-SAFE/workspace/a-safe/src/main/webapp/WEB-INF/jsp/aml/");
				
		fileInfoList.add(map);
		parse.setFileInfoList(fileInfoList);
		parse.process();
	}

	public List<HashMap<String, String>> srcInfoList = new ArrayList<HashMap<String, String>>(); 
	public List<HashMap<String, String>> fileInfoList = new ArrayList<HashMap<String, String>>(); 
	public List<HashMap<String, Object>> parseResultList = new ArrayList<HashMap<String, Object>>();

	public String configLocation;
	public AbstractApplicationContext ctx;
	public ParserDaoImpl dao;
	
	public CommonParser() {
		System.out.println("Start controllerParser");
		configLocation = "springjsptable/db/ParserMybatisOracle.xml"; // src/main/resources/springMybatisOracle.xml
		ctx = new GenericXmlApplicationContext(configLocation);
		dao = ctx.getBean("ParserDaoImpl",ParserDaoImpl.class);
	}
	

	public List getSrcInfoList() {
		return this.srcInfoList;
	};	
	
	public void setSrcInfoList(List<HashMap<String, String>> srcInfoList) {
		this.srcInfoList = srcInfoList;
	};

	public void setFileInfoList(List<HashMap<String, String>> fileInfoList) {
		this.fileInfoList = fileInfoList;
	}

	// public List getFileInfoList() { return fileInfoList; }

	public void process() {}

	@Override
	public String elimateComment(String src, String kind) {
		Pattern p; Matcher m;

		//String longComment = "/\\*([^*]|\\*+[^/*])*\\*+/";
		//String longComment = "/\\*+([.])*\\*+/";
		//String longComment = "/\\*(?:.|[\\n\\r])*?\\*/";
		
		String longComment = "";
		if(kind.equals("jsp")) {
			longComment = "/\\*+([.])*\\*+/";
		} else if(kind.equals("java")) {
			longComment = "/\\*([^*]|[\r\n]|(\\*+([^*/]|[\r\n])))*\\*+/";
		}
		
		String longComment2 = "<!--[^>](.*?)-->";
		
		String lineComment1 = "--.*";
		String lineComment2 = "//.*";
		//
		//String whitespace = "[\t\n]";

		// ------------------------------------------------------------------------------
		// Execute Parsing
		// ------------------------------------------------------------------------------
		try {
			//System.out.println("elimateComment 1");

			// ---------------------
			// Del longComment2
			// ---------------------
			p = Pattern.compile(longComment2); m = p.matcher(src); src = m.replaceAll("");
			
			// ---------------------
			// Del longComment
			// ---------------------
			p = Pattern.compile(longComment); m = p.matcher(src); src = m.replaceAll("");

			
			// ---------------------
			// Del lineComment1
			// ---------------------
			p = Pattern.compile(lineComment1); m = p.matcher(src); src = m.replaceAll("");
			//System.out.println("src1=" + src);
			//System.out.println("elimateComment 2");

			// ---------------------
			// Del lineComment2
			// ---------------------
			p = Pattern.compile(lineComment2); m = p.matcher(src); src = m.replaceAll("");

			// ---------------------
			// Del whitespace
			// ---------------------
			//p = Pattern.compile(whitespace);
			//m = p.matcher(src);
			//src = m.replaceAll("");
			
			//System.out.println("src2=" + src);
			//System.out.println("elimateComment 3");
			
			
		} catch (Exception e) {
			System.out.println("e=" + e.getMessage());
		}
		
		return src;
	}

	@Override
	public void parseBasic(String src) {}
	
	@Override
	public List getParseResultList() {
		return this.parseResultList;
	}
	
	@Override
	public void setParseResultList(List<HashMap<String, Object>> parseResultList) {
		this.parseResultList = parseResultList;
	}

	@Override
	public void insertParseResult() {}

	@Override
	public void insertSemanticAnalysis() {}


}