package com.minion.loader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public class BillingSheetLoader {

	private String filename = "";

	public BillingSheetLoader(String csvFileName) {
		filename = csvFileName;
	}

	public void load() throws LoadingException {
		ArrayList<BillingSheetBean> billingHash = new ArrayList<BillingSheetBean>();
		BufferedReader bufferedReader = null;
		try {
			FileReader fileReader = new FileReader(filename);
			bufferedReader = new BufferedReader(fileReader);
			String line = "";
			while ((line = bufferedReader.readLine()) != null) {
				BillingSheetBean bean = new BillingSheetBean();
				try {
					System.out.println("Parsing line : " + line);
					if (line.startsWith("#")) {
						continue;
					}
					bean.parse(line);
					billingHash.add(bean);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}

			// For every PORow
			// update the database

			for (BillingSheetBean bean : billingHash) {
				System.out.println(bean.getEmpId() + ":" + bean.getProjectESAId());
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
