package com.minion.loader;

public interface FileLoader {
	public void load(String path);
	
	public RowBean getNextRow();
}
