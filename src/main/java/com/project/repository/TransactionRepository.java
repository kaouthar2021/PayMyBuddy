package com.project.repository;

import com.project.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {

//    @Query(value = "SELECT * FROM transactions t WHERE t.user_id_sender = ?1 OR t.user_id_receiver = ?1 ",
//            countQuery = "SELECT count(*) FROM transactions t WHERE t.user_id_sender = ?1 OR t.user_id_receiver = ?1 ",
//            nativeQuery = true)
//    Page<Transaction> findAllTransactionById(Integer userId, Pageable pageable);

    Page<Transaction> findAll(Pageable pageable);

}
