/*
 *
 */
package com.example.spring.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.transaction.annotation.Transactional;

import com.example.spring.entity.PaperBrandEntity;
import com.example.spring.fetch.PaperBrandFetchOption;
import com.example.spring.filter.PaperBrandFilter;

/**
 * @author n.alam
 *
 */
@Transactional
public class PaperBrandRepositoryImpl implements PaperBrandRepositoryCustom {

    @Resource
    private EntityManager entityManager;

    @Override
    public List<PaperBrandEntity> findAll(final PaperBrandFilter filter, PaperBrandFetchOption fetchOptions) {

        final TypedQuery<PaperBrandEntity> query = getDynamicTypedQuery(filter, fetchOptions);

        final List<PaperBrandEntity> paperBrandEntityList = filter.getPage() == null && filter.getPageSize() == null ? query.getResultList()
                : query.setFirstResult(filter.getPage() * filter.getPageSize())
                    .setMaxResults(filter.getPageSize())
                    .getResultList();
        if (paperBrandEntityList.isEmpty()) {
            return new ArrayList<>();
        }
        return paperBrandEntityList;
    }

    @Override
    public PaperBrandEntity findOne(final PaperBrandFilter filter, PaperBrandFetchOption fetchOptions) {
        final TypedQuery<PaperBrandEntity> query = getDynamicTypedQuery(filter, fetchOptions);
        return query.getSingleResult();
    }

    @Override
    public int count(final PaperBrandFilter filter) {
        return findAll(filter).size();
    }

    private TypedQuery<PaperBrandEntity> getDynamicTypedQuery(final PaperBrandFilter filter, PaperBrandFetchOption fetchOptions) {

        final CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        final CriteriaQuery<PaperBrandEntity> criteriQuery = criteriaBuilder.createQuery(PaperBrandEntity.class);
        final Root<PaperBrandEntity> root = criteriQuery.from(PaperBrandEntity.class);

        if (fetchOptions.getIsPaper()) {
            root.fetch(PaperBrandEntity_.paperEntities.getName());
        }

        final List<Predicate> listOfPredicates = new ArrayList<>();
        if (!CollectionUtils.isEmpty(filter.getPaperBrandIds())) {
            listOfPredicates.add(root.get(PaperBrandEntity_.id)
                .in(filter.getPaperBrandIds()));
        }

        if (filter.getName() != null) {
            listOfPredicates.add(root.get(PaperBrandEntity_.name)
                .in(filter.getName()));
        }
        if (filter.getIsDeactivated() != null) {
            listOfPredicates.add(root.get(PaperBrandEntity_.isDeactived)
                .in(filter.getIsDeactivated()));
        }

        if (!CollectionUtils.isEmpty(listOfPredicates)) {
            criteriQuery.where(listOfPredicates.toArray(new Predicate[] {}))
                .distinct(true);
        }
        return this.entityManager.createQuery(criteriQuery);
    }

    @Override
    public List<PaperBrandEntity> findAll(PaperBrandFilter filter) {
        return findAll(filter, new PaperBrandFetchOption());
    }

    @Override
    public PaperBrandEntity findOne(PaperBrandFilter filter) {
        return findOne(filter, new PaperBrandFetchOption());
    }

}
