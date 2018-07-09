package com.gufang.tld;

import java.util.ArrayList;
import java.util.List;

public class ToolbarInfo {
	private String id = null;
	List<LineInfo> lines = new ArrayList<LineInfo>();
	private LineInfo currentLine = null;
	
	public ToolbarInfo(String id)
	{
		this.id = id;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<LineInfo> getLines() {
		return lines;
	}
	public void setLines(List<LineInfo> lines) {
		this.lines = lines;
	}
	public void addLines(LineInfo li) {
		if(lines == null)
			lines = new ArrayList<LineInfo>();
		lines.add(li);
		currentLine = li;
	}
	public LineInfo getCurrentLine() {
		return currentLine;
	}
	public void setCurrentLine(LineInfo currentLine) {
		this.currentLine = currentLine;
	}
	
}
