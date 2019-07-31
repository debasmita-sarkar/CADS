package com.cads.tableHeaders;

import java.util.Map;

public class HeadersTemplate {
	public HeadersTemplate(String title, String field) {
		super();
		this.title = title;
		this.field = field;
	}
	String title;
	String field;
	Map<? super Object, String> lookup;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	
	public Map<Object, String> getLookup() {
		return lookup;
	}
	public void setLookup(Map<Object, String> lookup) {
		this.lookup = lookup;
	}
	public HeadersTemplate(String title, String field, Map<Object, String> map) {
		super();
		this.title = title;
		this.field = field;
		this.lookup = map;
	}
	@Override
	public String toString() {
		return "HeadersTemplate [title=" + title + ", field=" + field + ", lookup=" + lookup + "]";
	}
	

}
