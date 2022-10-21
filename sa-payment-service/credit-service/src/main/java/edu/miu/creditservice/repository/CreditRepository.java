package edu.miu.creditservice.repository;

import edu.miu.creditservice.entity.Credit;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CreditRepository extends CassandraRepository<Credit, UUID> {

}
