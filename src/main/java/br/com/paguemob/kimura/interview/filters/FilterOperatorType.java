package br.com.paguemob.kimura.interview.filters;

public enum FilterOperatorType {
	EQUAL("="), LIKE("like");

	private String code;

	FilterOperatorType(String code) {
		this.code = code;
	}

	public String getCode() {
		return this.code;
	}

}
