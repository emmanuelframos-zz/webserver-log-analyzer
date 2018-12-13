package com.webserverloganalyzer.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.Objects;

@Repository
public class AccessLogRepository {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public BigInteger findMaxIdPlusOne(){

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Query query = entityManager.createNativeQuery("SELECT MAX(id) + 1 FROM access_log");

        BigInteger id = (BigInteger)query.getSingleResult();

        entityManager.close();

        return (Objects.isNull(id)?BigInteger.ONE:id);
    }
}