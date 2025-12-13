package com.finginus.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finginus.entity.Notfication;

public interface notificationRepo extends JpaRepository<Notfication, Long>
{
    List<Notfication> findByUserId(long userId);

}
