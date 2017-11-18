package br.com.paguemob.kimura.interview.filters;

public class Filter<T> {

	private String name;
	private FilterOperatorType operator;
	private T value;

	public Filter() {
	}

	public Filter(String name, FilterOperatorType operator, T value) {
		this.name = name;
		this.operator = operator;
		if(operator== FilterOperatorType.LIKE){
			this.value = (T) ("%"+value+"%");
		}
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public FilterOperatorType getOperator() {
		return operator;
	}

	public void setOperator(FilterOperatorType operator) {
		this.operator = operator;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

}
