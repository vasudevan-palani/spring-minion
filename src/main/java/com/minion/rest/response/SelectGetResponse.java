package com.minion.rest.response;

import java.util.HashMap;
import java.util.List;

import com.minion.rest.response.bean.SelectGetBean;

public class SelectGetResponse extends Response{

		HashMap<String,List<SelectGetBean>> list;
		
		public SelectGetResponse() {
			list = new HashMap<String,List<SelectGetBean>>();
		}

		public HashMap<String, List<SelectGetBean>> getList() {
			return list;
		}

		public void setList(HashMap<String, List<SelectGetBean>> list) {
			this.list = list;
		}
		

		
}