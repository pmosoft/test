package springjsptable.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import springjsptable.Constant;


/*
 * 설    명 : 화면소소내 사용 테이블정보 추출
 * 처리내역 :
 *   1. webPath 아래에 있는 화면 소스파일 및 폴더 정보를 저장
 *   2. 화면소스를 파싱하여 사용 CONTROLLER,SERVIER,SQLMAL(TABLE) 정보 추출
 *   3. 엑셀 출력
 *
 * */
public class SourceFileInfo {

	public static void main(String[] args) {
		//-----------------------------------------------------------
		// 사용 객체 생성
		//-----------------------------------------------------------
		SourceFileInfo trace = new SourceFileInfo();
		List<HashMap<String, String>> fileInfoList = new ArrayList<HashMap<String, String>>(); 

/*
		//-----------------------------------------------------------
		// 1. 웹경로에서 화면소스 파일 및 폴더 정보를 추출하여 DB에 저장
		//-----------------------------------------------------------
		// 1.1 파일 및 폴더 정보 추출
		fileInfoList = trace.readWebSrcList(Constant.webPath);
		// 1.2 파일 및 폴더 정보 저장
		oracleBasicJdbc.insertWebSrcFileInfo(fileInfoList);
*/
		
		//-----------------------------------------------------------
		// 2. 모든 소스를 기록한다(.jsp,Controller.java,Vo.java,Service.java,DAO.java,Impl.java
		//-----------------------------------------------------------
		// 2.1 파일 및 폴더 정보 추출
		List<HashMap<String, String>> srcInfoList = new ArrayList<HashMap<String, String>>(); 
		srcInfoList = trace.readSrcList(Constant.srcPath);
		// 2.2 파일 및 폴더 정보 저장
		//oracleBasicJdbc.insertWebSrcFileInfo(fileInfoList);
		
		System.out.println(srcInfoList.size());
		System.out.println(srcInfoList.get(0).get("src"));
		
		
	}

	public SourceFileInfo() {
		System.out.println("Start SourceFileInfo");
	}
	List<HashMap<String, String>> fileInfoList = new ArrayList<HashMap<String, String>>(); 
	List<HashMap<String, String>> srcInfoList = new ArrayList<HashMap<String, String>>(); 

	/*
	 * 모든 소스 정보를 기록한다.
	 * */
	public List readSrcList(String filePathName) {
		File dir = new File(filePathName);
		File[] fileList = dir.listFiles();
		//HashMap<String, String> map = null;

		try {
			for (int i = 0; i < fileList.length; i++) {
				File file = fileList[i];
				if (file.isFile() && file.getName().matches(".*\\.[jsp|java|xml)]+") && !file.getName().matches(".*\\.js")) {
					
					HashMap<String, String> map = new HashMap<String, String>();
			        map.put("fileName"   , file.getName());
			        map.put("filePathName"   , file.getPath().replace('\\', '/').replace("C:/A-SAFE/workspace/a-safe/src/main/", ""));
			        String src = readFile(file.getPath().replace('\\', '/'));
			        map.put("src" , src);
					srcInfoList.add(map); 
					
					System.out.println("\t 파일 이름 = " + file.getName());
					System.out.println("\t 경로 이름 = " + file.getPath().replace('\\', '/'));
				} else if (file.isDirectory()) {
					System.out.println("디렉토리 이름 = " + file.getName());
					// 서브디렉토리가 존재하면 재귀적 방법으로 다시 탐색
					readSrcList(file.getCanonicalPath().toString());
				}
			}

		} catch (IOException e) {}
		
		System.out.println("srcInfoList.size() = " + srcInfoList.size());
		
		return srcInfoList;
	}
	
	
	/*
	 * webPath 아래에 있는 화면 소스파일 및 폴더 정보를 저장
	 * */
	public List readWebSrcList(String filePathName) {
		File dir = new File(filePathName);
		File[] fileList = dir.listFiles();
		//HashMap<String, String> map = null;
		
		try {
			for (int i = 0; i < fileList.length; i++) {
				File file = fileList[i];
				if (file.isFile()) {
					
					// 파일이 있다면 파일 이름 출력
					HashMap<String, String> map = new HashMap<String, String>();
			        map.put("fileName"   , file.getName());
			        map.put("pathName"   , file.getParent().replace('\\', '/')+"/");
			        map.put("upPathName" , file.getParentFile().getParent().replace('\\', '/')+"/");
			        map.put("fileSize"   , Long.toString(file.length()));

					System.out.println("\t 파일 이름 = " + map.get("fileName"));
					System.out.println("\t 폴더 = " + map.get("pathName"));
					System.out.println("\t 부모폴더 = " + map.get("upPathName"));
					System.out.println("\t 파일 크기 = " + map.get("fileSize"));

					fileInfoList.add(map);

					System.out.println("fileInfoList 파일 크기 = " + fileInfoList.size());


				} else if (file.isDirectory()) {
					System.out.println("디렉토리 이름 = " + file.getName());
					// 서브디렉토리가 존재하면 재귀적 방법으로 다시 탐색
					readWebSrcList(file.getCanonicalPath().toString());
				}
			}


		} catch (IOException e) {}

		System.out.println("fileInfoList 파일 크기20 = " + fileInfoList.size());

		return fileInfoList;
	}

	public String readFile(String filePathName) {
		BufferedWriter bw = null;
		BufferedReader br = null;

		String src = "";
		try {

			File file = new File(filePathName);

			if (file.isFile()) {

				br = new BufferedReader(new InputStreamReader(new FileInputStream(filePathName)));

				while (true) {
					String str = br.readLine();
					// System.out.println("str="+str);
					if (str != null)
						src += str + "\n";
					else
						break;
				}
				// src="-----\n:;
				// src+="/*asdfasfa*/\n:;
				// src+="//aaa\n:;
				// src+="FRA_CNTR_SAMP_FRM_DTLS , \n:;
				// src+="FRA_CNTR_SAMP_FRM_DTLSAA , \n:;
				// src+="FRA_CNTR_SAMP_FRM_DTLSBB , \n:;
				//System.out.println("src=" + src);
				br.close();
			}
		} catch (Exception e) {
			System.out.println("e=" + e.getMessage());
		}
		
		return src;
	}	
	
}
