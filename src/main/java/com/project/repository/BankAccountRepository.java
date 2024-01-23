package com.project.repository;

import com.project.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount,Long> {
    List<BankAccount> findAllByUser_id(int idUser);
}
