package com.cgm.spriTTer.repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cgm.spriTTer.domain.User;

@Repository
public class UserDAO extends AbstractDAO <User> {
	protected UserDAO() {
		super(User.class);
	}
	
	@Transactional
	public User findByName(final String entityName) {
		User result = null;
		try {
			CriteriaBuilder cb = em().getCriteriaBuilder();
			CriteriaQuery<User> cq = cb.createQuery(User.class);
			Root<User> root = cq.from(User.class);

			cq.select(root);
			cq.where(cb.equal(root.get("name"), entityName));
			TypedQuery<User> q = em().createQuery(cq);
			result = q.getSingleResult();
			// List<E> allitems = q.getResultList();
			if (result != null) {
				return result;
			}
		} catch (Exception E) {}

		return result;

	}
}
