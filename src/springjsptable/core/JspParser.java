package springjsptable.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import springjsptable.Constant;

public class JspParser extends CommonParser {

	public static void main(String[] args) {

		// -----------------------------------------------------------
		// [1] 소스 정보를 파일과 폴더에서 읽어와 리스트에 저장
		// -----------------------------------------------------------
		List<HashMap<String, String>> srcInfoList = new ArrayList<HashMap<String, String>>();
		SourceFileInfo trace = new SourceFileInfo();
		srcInfoList = trace.readSrcList(Constant.srcPath);

		// -----------------------------------------------------------
		// [2] jsp내 .do 정보를 추출하여 리스트에 저장
		// -----------------------------------------------------------
		SourceParser jspParser = new JspParser();
		jspParser.setSrcInfoList(srcInfoList);
		jspParser.process();

		// DB에 저장
		jspParser.insertParseResult();
	}

	public JspParser(){
		System.out.println("Start JspParser");
		
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
		//for (int i = 0; i < 25; i++) {

			fileName = srcInfoList.get(i).get("fileName");
			filePathName = srcInfoList.get(i).get("filePathName");
			
			if (fileName.matches("(?i).*.jsp")) {
				src = srcInfoList.get(i).get("src");
				src2 = elimateComment(src,"jsp");
				parseBasic(src2);
			}	
		}
	}

	@Override
	public void parseBasic(String src) {
		Pattern p; Matcher m;
		String parseRule; boolean a;

		// rba/select650TList.do
		parseRule = "\\p{Alnum}+/+\\p{Alnum}+(.)do";
		//parseRule = "[a-zA-Z0-9]+/+[a-zA-Z0-9]+(.)do";
		//String parseRule = "/+[a-zA-Z]+/+[a-zA-Z]+(.)do";
		//String parseRule = "('|\")+/+[a-zA-Z]+/+[a-zA-Z]+(.)do(\'|\")+";
		p = Pattern.compile(parseRule); m = p.matcher(src); a = false;
		while (a = m.find()) {
			pos = String.valueOf(m.start()); parseResult = m.group();
			setParseResultList();
		}

		//parseRule = "(?i)queryId.+[\"]\\p{Alnum}[.]\\p{Alnum}[\"]";
		//parseRule = "(?i)queryId.+(\")[a-zA-Z0-9. ]+(\")";
		//parseRule = "(?i)[\"][a-zA-Z]{3}[0-9]{3}[.][a-zA-Z0-9_-|:]+[\"]";
		parseRule = "(?i)[\"][a-zA-Z]{3}[0-9]{3}[.][a-zA-Z0-9_-|\\:]*[\"]";
		p = Pattern.compile(parseRule); m = p.matcher(src); a = false;
		while (a = m.find()) {
			pos = String.valueOf(m.start()); parseResult = m.group();
			parseResult = parseResult.replace("\"","");
			setParseResultList("JSP3");
		}
	}

	public void setParseResultList() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("fileName"    , fileName);
		map.put("filePathName", filePathName);
		map.put("kind", "JSP1");
		map.put("pos", pos );
		map.put("parseResult", parseResult);
		this.parseResultList.add(map);
		System.out.println(map);
	}

	public void setParseResultList(String kind) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("fileName"    , fileName);
		map.put("filePathName", filePathName);
		map.put("kind", kind);
		map.put("pos", pos );
		map.put("parseResult", parseResult);
		this.parseResultList.add(map);
		System.out.println(map);
	}
	
	
	public void insertParseResult() {
		dao.deleteParseResult("JSP1");
		dao.deleteParseResult("JSP3");		
		dao.insertParseResult(parseResultList);
		dao.deleteParseResult("JSP2");
		dao.insertElinateJspReduction();
	}
	
}
