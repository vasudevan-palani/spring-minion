package com.minion.loader;

public class Loader {

	private FileLoader fileLoader;

	private RowTransformer rowTransformer;

	public void load(String path) {
		fileLoader.load(path);
		RowBean row = null;
		while ((row = fileLoader.getNextRow()) != null) {
			rowTransformer.transform(row);
		}
	}

	public FileLoader getFileLoader() {
		return fileLoader;
	}

	public void setFileLoader(FileLoader fileLoader) {
		this.fileLoader = fileLoader;
	}

	public RowTransformer getRowTransformer() {
		return rowTransformer;
	}

	public void setRowTransformer(RowTransformer rowTransformer) {
		this.rowTransformer = rowTransformer;
	}

	
	
}