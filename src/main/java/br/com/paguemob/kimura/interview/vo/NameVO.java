package br.com.paguemob.kimura.interview.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class NameVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String title = "";

	private String first = "";

	private String last = "";

	public NameVO(){}
	public NameVO(String title, String first, String last) {
		super();
		this.title = title;
		this.first = first;
		this.last = last;
	}

	@JsonIgnore
	public String getFullName() {
		return (title + " " + first + " " + last).trim();

	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

}
