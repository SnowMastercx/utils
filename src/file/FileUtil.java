package file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class FileUtil {
	public static File workPath = new File(System.getProperty("user.dir"));
	
	/**
	 * 创建文件
	 * @param fileUrl
	 * @param name
	 * @param type
	 */
	public void createFile(String fileUrl, String name, String type) {
		String fileName = workPath + File.separator + fileUrl + File.separator + name + "." + type;
		File file = new File(fileName);
		
		
		if (!file.exists()) {
			
			new File(new String(file.getParentFile().getPath())).mkdirs();
			try {
				file.createNewFile();
				System.out.println("create file: " + fileName + " success!" );
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("create file: " + fileName + " failed, because that " + e.getMessage());
			}
		}
		else {
			System.out.println("That file is already exit!");
		}
	}
	
	/**
	 * 列出路径下所有的文件
	 * @param fileUrl
	 */
	public void lsAllfile(String fileUrl) {
		String dir = workPath + File.separator + fileUrl;
		File dirPath = new File(dir);
		File[] files = dirPath.listFiles();
		
		System.out.println("list all files below the " + dirPath.getName());
		
		for (File file : files) {
			System.out.println(" " + file.getName());
		}
	}
	
    /**
     * 读取文件中的字符串数组
     * @param file 
     * @param fir
     * @param sec
     * @return
     * @throws IOException
     */
	public String[][] resAccount (String fileUrl, int fir, int sec) throws IOException {
		String[][] arr= new String[fir][sec];
		String fileName = workPath + File.separator + fileUrl;
		File file = new File(fileName);
		
		int index = 0;
		String temp = null;
		if (file.exists() && file.isFile()) {
			try {
				@SuppressWarnings("resource")
				BufferedReader reader = new BufferedReader(new FileReader(file));
				while ((temp = reader.readLine()) != null) {
					String[] tempStr = temp.split(" ");
					for (int i = 0; i < sec; i++) {
						arr[index][i] = tempStr[i];
					}
					index++;
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				System.out.println("");
			}
		}
		
		for (int i = 0; i < fir; i++) {
			for (int j = 0; j < sec; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
		
		return arr;
	}
	
	/**
	 * 写入文件
	 * @param fileUrl
	 * @param content
	 * @param encoding
	 * @throws IOException
	 */
	public void writeContent (String fileUrl, String content, String encoding) throws IOException {
		String fileName = workPath + File.separator + fileUrl;
		File file = new File(fileName);
		
		if (file.exists()) {
			FileOutputStream out = new FileOutputStream(file, true);
			StringBuffer sb = new StringBuffer();
			sb.append(content);
			out.write(sb.toString().getBytes(encoding));
			out.close();
			
			System.out.print("write content into file success!");
		}
	}
	
	//TODO
	public void readFromExcel () {
		
	}
	
	//for test
	public static void main(String[] args) {
		String fileUrl = "testFile";
		String name = "testcreateFile";
		String type = "txt";
		
		new FileUtil().createFile(fileUrl, name, type);
		 
//		new FileUtil().lsAllfile(fileUrl);
		
//		try {
//			new FileUtil().resAccount(fileUrl, 2, 2);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
//		try {
//			new FileUtil().writeContent(fileUrl, " 13580472222 123456", "UTF-8");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
}
