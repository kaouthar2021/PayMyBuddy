package com.project.repository;

import com.project.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    Page<Transaction> findAllByUserSender_id(final int id, Pageable pageableParam);
    @Query(value = "SELECT user_id_sender, user_id_receiver, description, amount as amount , transaction_date, id FROM Transaction WHERE user_id_receiver = :idUser union SELECT user_id_sender, user_id_receiver, description, -1.00 * amount as amount, transaction_date, id FROM Transaction WHERE user_id_payer != user_id_beneficiary AND user_id_payer = :idUser ORDER BY transaction_date DESC",
            countQuery = "SELECT count(*) FROM Transaction WHERE user_id_beneficiary = :idUser OR (user_id_payer = :idUser AND user_id_beneficiary != :idUser)",
            nativeQuery = true)
    Page<Transaction> findAllByUserEmitterAndUserReceiver_Id(int idUser, Pageable pageableParam);
    Page<Transaction> findAllByUserSender_idOrUserReceiver_id(final int senderId,final int receiverId, Pageable pageableParam);


}

