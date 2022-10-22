package edu.miu.bankservice.repository;

import edu.miu.bankservice.entity.Bank;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BankRepository extends CassandraRepository<Bank, UUID> {

}
