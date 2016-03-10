package com.minion.loader.excel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.boot.logging.LogLevel;

import com.minion.Utils;
import com.minion.loader.RowBean;

public class InvoiceLoader extends ExcelLoader {

	private ArrayList<InvoiceBean> beans = null;

	private Iterator<InvoiceBean> beanIterator = null;

	public InvoiceLoader(int sheetNo, long startRowNo) {
		super(sheetNo, startRowNo);
	}

	public InvoiceLoader(int sheetNo) {
		super(sheetNo);
	}

	public InvoiceLoader(long startRowNo) {
		super(startRowNo);
	}

	public InvoiceLoader() {
		super();
	}

	public void load(String location) {
		beans = new ArrayList<InvoiceBean>();

		beanIterator = null;

		this.location = location;

		try {

			// Need to move this block to Excel Loader as it is an common
			// implementation
			setWorkbook();
			int i;
			int j;
			if (sheetNo == -1) {
				i = 0;
				j = workbook.getNumberOfSheets() - 1;
			} else {
				i = sheetNo;
				j = sheetNo;
			}

			sheet: for (; i <= j; i++) {
				Sheet sheet = workbook.getSheetAt(i);
				Iterator<Row> rowIterator = sheet.iterator();
				row: while (rowIterator.hasNext()) {
					InvoiceBean bean = new InvoiceBean();
					Row row = rowIterator.next();
					while (row.getRowNum() < startRowNo) {
						continue row;
					}
					if (isRowEmpty(row)) {
						continue sheet;
					}
					Iterator<Cell> cellIterator = row.cellIterator();
					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
						if (Cell.CELL_TYPE_STRING == cell.getCellType()) {
							switch (cell.getColumnIndex()) {
							case 0:
								bean.setVendorName(cell.getStringCellValue());
								break;
							case 1:
								bean.setFullName(cell.getStringCellValue());
								break;
							case 2:
								bean.setInvoiceNum(cell.getStringCellValue());
								break;
							}
						} else if (Cell.CELL_TYPE_NUMERIC == cell.getCellType()) {
							switch (cell.getColumnIndex()) {
							case 3:
								bean.setQtyInvoiced(new Float(cell.getNumericCellValue()));
								break;
							case 4:
								bean.setUnitPrice(Math.round(new Float(cell.getNumericCellValue())));
								break;
							case 5:
								bean.setAmount(new Float(cell.getNumericCellValue()));
								break;
							case 6:
								bean.setInvoiceAmt(new Float(cell.getNumericCellValue()));
								break;
							case 8:
								bean.setStartDate(new java.sql.Date(cell.getDateCellValue().getTime()));
								break;
							case 9:
								bean.setEndDate(new java.sql.Date(cell.getDateCellValue().getTime()));
								break;
							case 7:
								bean.setPoNum(Math.round(cell.getNumericCellValue())+"");
								break;
							case 15:
								bean.setEmpId(Math.round(cell.getNumericCellValue())+"");
								break;								
							}
						}
					}
					Utils.log(LogLevel.TRACE, bean.toString());
					beans.add(bean);
				}
			}

		} catch (FileNotFoundException fe) {
			System.out.println(fe.getMessage());
			fe.printStackTrace();
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
			ioe.printStackTrace();
		}
	}

	@Override
	public RowBean getNextRow() {
		if (beanIterator == null) {
			beanIterator = beans.iterator();
		}
		if (beanIterator.hasNext()) {
			return (RowBean) beanIterator.next();
		} else {
			return null;
		}
	}
	
	public static void main(String args[]){
		InvoiceLoader loader = new InvoiceLoader();
		loader.load("C:/Users/prabh/Desktop/invoice_merged_v1.1.xlsm");
		for(InvoiceBean bean : loader.beans){
			System.out.println(bean);
		}
	}

}
