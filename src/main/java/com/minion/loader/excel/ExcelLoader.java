package com.minion.loader.excel;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.minion.loader.FileLoader;

public abstract class ExcelLoader implements FileLoader{

	protected String location;
	protected int sheetNo ;
	protected Workbook workbook;
	protected long startRowNo;

	public ExcelLoader(int sheetNo, long startRowNo) {
		this.sheetNo = sheetNo;
		this.startRowNo = startRowNo;
	}
	
	

	public ExcelLoader(int sheetNo) {
		this(sheetNo,2);
	}



	public ExcelLoader(long startRowNo) {
		this(-1,startRowNo);
	}



	public ExcelLoader() {
		this(2);
	}



	public void setWorkbook() throws IOException{
		workbook = null;
		FileInputStream fis = new FileInputStream(location);
		
		if(location!=null && location.contains(".")){
			if(location.substring(location.lastIndexOf('.')+1).equals("xlsx")){
				workbook = new XSSFWorkbook(fis);
			}
			else if(location.substring(location.lastIndexOf('.')+1).equals("xls")){
				workbook = new HSSFWorkbook(fis);
			}
			else{
				System.out.println("Invalid File extension: " +location.lastIndexOf('.')+1 );
			}
		}
		else{
			System.out.println("Invalid File format: " +location);
		}
	}
	
	public static boolean isRowEmpty(Row row) {
	    for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
	        Cell cell = row.getCell(c);
	        if (cell != null && cell.getCellType() != Cell.CELL_TYPE_BLANK)
	            return false;
	    }
	    return true;
	}
	
}
