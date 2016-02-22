package com.minion.loader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import com.minion.POBean;
import com.minion.dao.PODao;
import com.minion.dao.PORolesDao;

public class POLoader {


	private String filename = "";

	private PODao poDao;

	private PORolesDao poRolesDao;
	
	public POLoader(String csvFileName) {
		filename = csvFileName;
	}
	

	public PORolesDao getPoRolesDao() {
		return poRolesDao;
	}

	public void setPoRolesDao(PORolesDao poRolesDao) {
		this.poRolesDao = poRolesDao;
	}

	public PODao getPoDao() {
		return poDao;
	}

	public void setPoDao(PODao poDao) {
		this.poDao = poDao;
	}



	public void load() throws LoadingException {
		HashMap<String, POBean> poHash = new HashMap<String, POBean>();
		BufferedReader bufferedReader = null;
		try {
			FileReader fileReader = new FileReader(filename);
			bufferedReader = new BufferedReader(fileReader);
			String line = "";
			while ((line = bufferedReader.readLine()) != null) {
				POBean po = new POBean();
				try {
					System.out.println("Parsing line : " + line);
					if (line.startsWith("#")) {
						continue;
					}
					po.parse(line);
					poHash.put(po.getPoNumber() + "," + po.getPoRevision(), po);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			
			
			//For every PORow
			// update the database
			
			for(Map.Entry<String, POBean> poEntry : poHash.entrySet()){
	            System.out.println(poEntry.getKey() +" :: "+ poEntry.getValue());
	            poDao.updatePO(poEntry.getValue());
	            poRolesDao.createPORolesIfNotExists(poEntry.getValue());
	        }
			
		} catch (IOException e) {
			e.printStackTrace();
			throw new LoadingException();
		} finally {
			if (bufferedReader != null)
				try {
					bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
					throw new LoadingException();
				}
		}
	}
}
