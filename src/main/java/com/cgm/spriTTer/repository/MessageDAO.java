package com.cgm.spriTTer.repository;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cgm.spriTTer.domain.Message;

@Repository
public class MessageDAO extends AbstractDAO<Message> {
	protected MessageDAO() {
		super(Message.class);
	}

	@Transactional
	public List<Message> findByUserId(final Long entityId) {
		List<Message> allitems = null;
		try {
			CriteriaBuilder cb = em().getCriteriaBuilder();
			CriteriaQuery<Message> cq = cb.createQuery(Message.class);
			Root<Message> root = cq.from(Message.class);

			cq.select(root);
			cq.where(cb.equal(root.get("user_id"), entityId));
			TypedQuery<Message> q = em().createQuery(cq);
			// result = q.getSingleResult();
			allitems = q.getResultList();
		} catch (Exception E) {
		}

		return allitems;

	}
}
