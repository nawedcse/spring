package com.example.spring.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.util.CollectionUtils;

import com.example.spring.entity.StatusEntity;
import com.example.spring.filter.StatusFilter;

public class StatusRepositoryImpl implements StatusRepositoryCustom {

	@Resource
    private EntityManager entityManager;
	
	@Override
	public List<StatusEntity> findAll(StatusFilter filter) {
		final TypedQuery<StatusEntity> dynamicTypedQuery = getDynamicTypedQuery(filter);
		final List<StatusEntity> entityList = (filter.getPage() == null && filter.getPageSize() == null) ? dynamicTypedQuery.getResultList()
                : dynamicTypedQuery.setFirstResult(filter.getPage() * filter.getPageSize())
                    .setMaxResults(filter.getPageSize())
                    .getResultList();
		return entityList == null ? new ArrayList<>() : entityList;
	}


	@Override
	public StatusEntity findOne(StatusFilter filter) {
		 return getDynamicTypedQuery(filter).getSingleResult();
	}

	@Override
	public int count(StatusFilter filter) {
		return findAll(filter).size();
	}


	private TypedQuery<StatusEntity> getDynamicTypedQuery(StatusFilter filter) {

		 final CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
	        final CriteriaQuery<StatusEntity> criteriaQuery = criteriaBuilder.createQuery(StatusEntity.class)
	            .distinct(true);
	        final Root<StatusEntity> root = criteriaQuery.from(StatusEntity.class);
	        
	        root.fetch(StatusEntity_.STATUS_LANGUAGE_ENTITIES, JoinType.LEFT);  // oneToMany relation this is second entity (STATUS_LANGUAGE_ENTITIES) 
	        
	        final List<Predicate> predicates = new ArrayList<>();
	        
	        if (CollectionUtils.isNotEmpty(filter.getIds())) {
	            predicates.add(root.get(StatusEntity_.id)
	                .in(filter.getIds()));
	        }
	        if (filter.getIsActive() != null) {
	            predicates.add(criteriaBuilder.equal(root.get(StatusEntity_.IS_DEACTIVATED), !filter.getIsActive()));
	        }

	        if (!predicates.isEmpty()) {
	            criteriaQuery.where(predicates.toArray(new Predicate[] {}))
	                .distinct(true);
	        }

	        return this.entityManager.createQuery(criteriaQuery);
	}
	
	
	
//	private TypedQuery<GrammageEntity> getDynamicTypedQuery(final GrammageFilter filter) {
//
//        final CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
//        final CriteriaQuery<GrammageEntity> criteriQuery = criteriaBuilder.createQuery(GrammageEntity.class);
//        final Root<GrammageEntity> root = criteriQuery.from(GrammageEntity.class);
//
//        final List<Predicate> listOfPredicates = new ArrayList<>();
//
//        if (!CollectionUtils.isEmpty(filter.getGrammageIds())) {
//            listOfPredicates.add(root.get(GrammageEntity_.id)
//                .in(filter.getGrammageIds()));
//        }
//
//        if (filter.getIsDeactivated() != null) {
//            listOfPredicates.add(root.get(GrammageEntity_.isDeactived)
//                .in(filter.getIsDeactivated()));
//        }
//
//        if (filter.getGrammage() != null) {
//            listOfPredicates.add(root.get(GrammageEntity_.grammage)
//                .in(filter.getGrammage()));
//        }
//
//        if (!CollectionUtils.isEmpty(listOfPredicates)) {
//            criteriQuery.where(listOfPredicates.toArray(new Predicate[] {}))
//                .distinct(true);
//        }
//
//        return this.entityManager.createQuery(criteriQuery);
//
//    }
}
