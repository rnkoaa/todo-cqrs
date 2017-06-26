package com.todo.cqrs.todo.impl.jpa;

import com.todo.cqrs.lib.DomainEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created on 6/26/2017.
 */
@Component("customDomainEventRepository")
@Profile("jpa")
public class CustomDomainEventRepositoryImpl implements CustomDomainEventRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    CustomDomainEventRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public List<DomainEvent> findDomainEvents(String aggregateId) {
        String sql = "Select event_details from todocqrs.domain_event where aggregate_id = :aggregateId";
        Query namedQuery = entityManager.createNativeQuery(sql);
        namedQuery.setParameter("aggregateId", aggregateId);

        List resultList = namedQuery.getResultList();
        for (Object o : resultList) {
            String result = (String) o;
            System.out.println(result);
        }
        //PreparedStatement statement = new
        return null;
    }

    @Override
    public List<DomainEvent> findDomainEvents() {
        String sql = "Select event_details from todocqrs.domain_event";
        Query namedQuery = entityManager.createNativeQuery(sql);

        List resultList = namedQuery.getResultList();
        for (Object o : resultList) {
            String result = (String) o;
            System.out.println(result);
        }
        //PreparedStatement statement = new
        return null;
    }
}
