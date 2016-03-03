package com.minion.loader.excel;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class KronosLoader extends ExcelLoader{
	
	public KronosLoader(int sheetNo, long startRowNo){
		super(sheetNo,startRowNo);
	}

	public KronosLoader(int sheetNo){
		super(sheetNo);
	}
	
	public KronosLoader(long startRowNo){
		super(startRowNo);
	}
	
	public KronosLoader(){
		super();
	}
	
	public List<KronosBean> load(String location){
		this.location = location;
		List<KronosBean> kronosSheets = new ArrayList<KronosBean>();	

		try{
			
			//Need to move this block to Excel Loader as it is an common implementation
			setWorkbook();
			int i;
			int j;
			if(sheetNo==-1){
				i=0;
				j=workbook.getNumberOfSheets()-1;
			}else{
				i=sheetNo;
				j=sheetNo;
			}
			
			
			
			sheet : for (;i <= j; i++) {
				Sheet sheet = workbook.getSheetAt(i);
				Iterator<Row> rowIterator = sheet.iterator();
				row : while (rowIterator.hasNext()) {
					KronosBean bean = new KronosBean();
					Row row = rowIterator.next();
					while(row.getRowNum()<startRowNo){
						continue row ;
					}
					if(isRowEmpty(row)){
						continue sheet;
					}
					Iterator<Cell> cellIterator = row.cellIterator();
					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
						 if (Cell.CELL_TYPE_STRING == cell.getCellType()) {
							 switch (cell.getColumnIndex()){
								 case 0:
									 bean.setName(cell.getStringCellValue());
									 break;
								 case 1:
									 bean.setId(cell.getStringCellValue());
									 break;
								 case 4:
									 bean.setAccount(cell.getStringCellValue());
									 break;
								 case 5:
									 bean.setXfr(cell.getStringCellValue());
									 break;							 
								 case 6:
									 bean.setOrganization(cell.getStringCellValue());
									 break;
								 case 8:
									 bean.setPayCode(cell.getStringCellValue());
									 break;
							 }
						 }
						 else if(Cell.CELL_TYPE_NUMERIC == cell.getCellType()){
							 if(cell.getColumnIndex() == 13){
								 bean.setExpectedHours(cell.getNumericCellValue());
							 }else if(cell.getColumnIndex() == 19){
								 bean.setLoggedHours(cell.getNumericCellValue());
							 }else if(cell.getColumnIndex() == 2 && HSSFDateUtil.isCellDateFormatted(cell)){
								 bean.setDate(new java.sql.Date(cell.getDateCellValue().getTime()));
							 }
						 }
					}
					kronosSheets.add(bean);
				}		
			}
			
				
		}
		catch(FileNotFoundException fe){
			System.out.println(fe.getMessage());
			fe.printStackTrace();
		}
		catch(IOException ioe){
			System.out.println(ioe.getMessage());
			ioe.printStackTrace();
		}
		return kronosSheets;
	}
	
	public static void main(String args[]){
		KronosLoader koronosLoader  = new KronosLoader(-1,19);
		for(KronosBean bean : koronosLoader.load("G:/Play/vasu/Jan 2016.xls")){
			System.out.println(bean);
			System.out.println(bean.toString());
		}
		
	}
	
	
}
