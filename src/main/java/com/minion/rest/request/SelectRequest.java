package com.minion.rest.request;

import java.util.List;

import com.minion.rest.request.bean.SelectItem;

public class SelectRequest {
	List<SelectItem> names;

	public List<SelectItem> getNames() {
		return names;
	}

	public void setNames(List<SelectItem> names) {
		this.names = names;
	}


}
