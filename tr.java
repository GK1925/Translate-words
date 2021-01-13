package translator;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.charset.Charset;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class tr {
	private static final Charset CHARSET = Charset.forName("UTF-8");
	private static final String FIND_REPLACE_XLSX = "C:/Users/MANIVANNAN/Desktop/french_dictionary.xlsx";
	private static final int FINDCOL = 0;
	private static final int REPLACECOL = 1;

	public void replaceTextFile(String inFile, String outFile) throws IOException {
		List<FindReplaceStr> list = getFindReplaceList();
		Path path = Paths.get(inFile);
		String str = readFile(path);
	/*	Scanner txtFile = new Scanner(new File("c:/Users/MANIVANNAN/Downloads/t8.shakespeare.txt"));
		TreeMap<String, Integer> map = new TreeMap<String, Integer>();
		 while(txtFile.hasNext()) {
	 String word=txtFile.next();
	 int count=1;
	 if(map.containsKey(word)) {
		 count=map.get(word)+1;
	 }
	 map.put(word,count);
 }
 txtFile.close();
 System.out.print("Frequency");
 for(Map.Entry<String,Integer> entry: map.entrySet()) {
	 System.out.println(entry);
 }*/
 for(FindReplaceStr item:list) {
	 str=str.replace(item.findStr,item.replaceStr);
 }
 Path topath=Paths.get(outFile);
 BufferedWriter writer=Files.newBufferedWriter(topath, CHARSET);
 writer.write(str);
 writer.close();
}
private String readFile(Path path)throws IOException{
	byte[] bytes=Files.readAllBytes(path);
	return new String(bytes,CHARSET);
}
private List<FindReplaceStr> getFindReplaceList()throws EncryptedDocumentException,IOException
{
	Workbook workbook=WorkbookFactory.create(new File(FIND_REPLACE_XLSX));
	Sheet sheet=workbook.getSheetAt(0);
	DataFormatter dataFormatter=new DataFormatter();
	int rows=sheet.getPhysicalNumberOfRows();
	List<FindReplaceStr> list=new ArrayList<FindReplaceStr>();
	for(int i=1;i<rows;i++) {
		Row row=sheet.getRow(i);
		list.add(new FindReplaceStr(dataFormatter.formatCellValue(row.getCell(FINDCOL)),(dataFormatter.formatCellValue(row.getCell(REPLACECOL)))));
	}
	return list;
		
	}
}
