package com.gufang.tld;

import java.util.ArrayList;
import java.util.List;

public class FieldInfo {
	private String type = "text";
	private String id = null;
	private String label = null;
	private String url = null;
	private String idField = "id";
	private String nameField = "name";
	private String datagridId = "";
	private String width = "100";
	private List<OptionInfo> options = new ArrayList<OptionInfo>();
	private String onclick = "";
	private String multiline = "false";
	
	public FieldInfo(String type,String id,String label,String url)
	{
		this.type = type;
		this.id = id;
		this.label = label;
		this.url = url;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getIdField() {
		return idField;
	}
	public void setIdField(String idField) {
		this.idField = idField;
	}
	public String getNameField() {
		return nameField;
	}
	public void setNameField(String nameField) {
		this.nameField = nameField;
	}
	public String getDatagridId() {
		return datagridId;
	}
	public void setDatagridId(String datagridId) {
		this.datagridId = datagridId;
	}
	public List<OptionInfo> getOptions() {
		return options;
	}
	public void setOptions(List<OptionInfo> options) {
		this.options = options;
	}
	public void addOptions(OptionInfo oi)
	{
		if(options == null)
			options = new ArrayList<OptionInfo>();
		options.add(oi);
	}
	public void addOptions(String id,String name)
	{
		if(options == null)
			options = new ArrayList<OptionInfo>();
		options.add(new OptionInfo(id,name));
	}
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	public String getOnclick() {
		return onclick;
	}
	public void setOnclick(String onclick) {
		this.onclick = onclick;
	}
	public String getMultiline() {
		return multiline;
	}
	public void setMultiline(String multiline) {
		this.multiline = multiline;
	}
}
