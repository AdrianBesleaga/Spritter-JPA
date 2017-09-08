package com.cgm.spriTTer.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.cgm.spriTTer.domain.User;

@Transactional
@EnableTransactionManagement
public abstract class AbstractDAO<E> {
	@PersistenceContext(name = "twitter-jpa")
	private EntityManager em;

	private final Class<E> entityClass;

	protected AbstractDAO(final Class<E> entityClass) {
		this.entityClass = entityClass;
	}

	@Transactional
	public EntityManager em() {
		return em;
	}

	@Transactional
	public E findById(final Long entityId) {
		return em.find(entityClass, entityId);
	}
	


	@Transactional
	public List<User> findByName(final String entityName) {
		//System.out.println("SELECT id,name FROM "+entityClass.getName()+" WHERE name = '"+entityName+"'");
		

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<User> cq = cb.createQuery(User.class);
		Root user = cq.from(User.class);

		cq.select(user);
		cq.where(cb.equal(user.get("name"), entityName));
		TypedQuery<User> q = em.createQuery(cq);
		List<User> allitems = q.getResultList();
		
		/*
		 * 
// Select the employees and the mailing addresses that have the same address.
CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
Root employee = criteriaQuery.from(Employee.class);
Root address = criteriaQuery.from(MailingAddress.class);
criteriaQuery.multiselect(employee, address);
criteriaQuery.where(criteriaBuilder.equal(employee.get("address"), address.get("address"));
Query query = entityManager.createQuery(criteriaQuery);
List<Object[]> result = query.getResultList();
		 * 
		 */
		
		return  allitems;
		
		//return (E) em.createQuery("SELECT id,name FROM "+entityClass.getName()+" WHERE name = '"+entityName+"'" , entityClass).getSingleResult();

	}

	@Transactional
	public void save(final E entityToBeSaved) {
		em.persist(entityToBeSaved);
	}

	@Transactional
	public void update(final E entityToBeUpdated) {
		em.merge(entityToBeUpdated);
	}

	@Transactional
	public void remove(final E entityToBeRemoved) {
		em.remove(entityToBeRemoved);
	}

	@Transactional
	public void delete(final Long id) {
		em.remove(findById(id));
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<E> findAll() {
		return (List<E>) em.createQuery(new StringBuilder().append("SELECT entity FROM ")
				.append(entityClass.getCanonicalName()).append(" entity ").toString()).getResultList();
	}

}
