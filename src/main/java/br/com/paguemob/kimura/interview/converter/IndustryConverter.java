package br.com.paguemob.kimura.interview.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.com.paguemob.kimura.interview.enums.IndustryType;

@Converter
public class IndustryConverter implements AttributeConverter<IndustryType, String> {

	/**
	 * Convert IndustryType object to a String
	 */
	@Override
	public String convertToDatabaseColumn(IndustryType IndustryType) {
		return IndustryType.getName();
	}

	@Override
	public IndustryType convertToEntityAttribute(String IndustryTypeString) {
		return IndustryType.findByName(IndustryTypeString);
	}

}