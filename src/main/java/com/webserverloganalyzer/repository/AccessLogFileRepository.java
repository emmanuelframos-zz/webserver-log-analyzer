package com.webserverloganalyzer.repository;

import com.webserverloganalyzer.domain.AccessLog;
import com.webserverloganalyzer.domain.AccessLogFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.Objects;

@Repository
public class AccessLogFileRepository {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private AccessLogRepository accessLogRepository;

    @Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
    private Integer batchSize;

    public void persist(AccessLogFile accessLogFile){

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {

            entityTransaction.begin();

            accessLogFile.setId(findMaxIdPlusOne());

            entityManager.persist(accessLogFile);

            BigInteger accessLogIdCount = accessLogRepository.findMaxIdPlusOne();
            int entityCount = accessLogFile.getAccessLogs().size();
            for (AccessLog accessLog : accessLogFile.getAccessLogs()){
                if (entityCount > 0 && entityCount % batchSize == 0) {
                    entityTransaction.commit();
                    entityTransaction.begin();
                    entityManager.clear();
                }

                accessLog.setId(accessLogIdCount);
                accessLog.setAccessLogFile(accessLogFile.getId());

                entityManager.persist(accessLog);
                accessLogIdCount = accessLogIdCount.add(BigInteger.ONE);
                ++entityCount;
            }

            entityTransaction.commit();

        } catch (RuntimeException e) {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            throw e;
        } finally {
            entityManager.close();
        }
    }

    public BigInteger findMaxIdPlusOne(){

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Query query = entityManager.createNativeQuery("SELECT MAX(id) + 1 FROM access_log_file");

        BigInteger id = (BigInteger)query.getSingleResult();

        entityManager.close();

        return (Objects.isNull(id)?BigInteger.ONE:id);
    }
}