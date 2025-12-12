package com.fingeinuis.repositort;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fingeinuis.entity.transaction;

public interface transactionRepo extends JpaRepository<transaction, Long> 
{
    List<transaction> findByUserId(Long userId);

}
