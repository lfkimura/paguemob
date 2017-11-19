package br.com.paguemob.kimura.interview.enums;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum IndustryType implements Serializable {
	AGRICULTURE("Agriculture"), TRANSPORT("Transport"), BANKING("Banking"), BUSINESS_SERVICES(
			"Bussiness Services"), EDUCATION("Education"), HEALTH(
					"Health"), HARDWARE("Hardware"), INTERNET("Internet"), SOFTWARE("Software"), OTHER("Other");

	private IndustryType(String name) {
		this.name = name;
	}

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static IndustryType findByName(String name) {
		for (IndustryType type : values()) {
			if (type.getName().equals(name))
				return type;
		}
		return IndustryType.OTHER;
	}

}
