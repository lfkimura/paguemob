package br.com.paguemob.kimura.interview.vo;

public class NameVO {
	private String title = "";

	private String first = "";

	private String last = "";

	public NameVO(String title, String first, String last) {
		super();
		this.title = title;
		this.first = first;
		this.last = last;
	}

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
