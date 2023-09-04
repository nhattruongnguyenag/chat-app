package com.chatapp.repository.impl;

import com.chatapp.entity.MessageEntity;
import com.chatapp.repository.CustomizedMessageRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomizedMessageRepositoryImpl implements CustomizedMessageRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<MessageEntity> findBySenderOrReceiver(Long senderId, Long receiverId) {
        String sql = new StringBuilder("SELECT m FROM MessageEntity m")
                .append("\nJOIN m.sender as s")
                .append("\nJOIN m.receiver as r")
                .append("\nWHERE (s.id = ?1 OR r.id = ?2)")
                .append("\nAND (s.id = ?3 OR r.id = ?4)")
                .append("\nORDER BY m.createdAt ASC").toString();

        Query query = entityManager.createQuery(sql)
                .setParameter(1, senderId)
                .setParameter(2, senderId)
                .setParameter(3, receiverId)
                .setParameter(4, receiverId);

        return query.getResultList();
    }
}
