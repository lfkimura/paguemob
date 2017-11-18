package br.com.paguemob.kimura.interview.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.paguemob.kimura.interview.filters.Filter;
import br.com.paguemob.kimura.interview.model.Company;
import br.com.paguemob.kimura.interview.repository.CompanyRepositoryCustom;

public class CompanyRepositoryImpl implements CompanyRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Company> findCompaniesWithFilters(List<Filter<String>> filters) {
		StringBuilder builder = new StringBuilder("SELECT c FROM Company c ");
		if (!filters.isEmpty()) {
			builder = builder.append("WHERE ");
			for (int i = 0; i < filters.size(); i++) {
				Filter<String> filter = filters.get(i);
				builder = builder.append("c.").append(filter.getName()).append(" ")
						.append(filter.getOperator().getCode()).append(" :").append(filter.getName());
				if (i < filters.size() - 1)
					builder.append(" AND ");
			}
		}

		TypedQuery<Company> query = entityManager.createQuery(builder.toString(), Company.class);
		for (Filter<String> filter : filters) {
			query.setParameter(filter.getName(), filter.getValue());
		}

		return query.getResultList();
	}

}
