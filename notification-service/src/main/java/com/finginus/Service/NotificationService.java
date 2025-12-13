package com.finginus.Service;

import java.util.List;

import com.finginus.entity.Notfication;


public interface NotificationService 
{
    void processTransactionEvent(Long userId, double amount, String type, String category);

    List<Notfication> getUserNotifications(long userId);

}
