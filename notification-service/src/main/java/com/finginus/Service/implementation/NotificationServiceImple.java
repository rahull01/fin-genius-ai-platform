package com.finginus.Service.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.finginus.Repository.notificationRepo;
import com.finginus.Service.NotificationService;
import com.finginus.entity.Notfication;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@org.springframework.transaction.annotation.Transactional
public class NotificationServiceImple implements NotificationService {

    private final notificationRepo notificationRepository;

    @Override
    public void processTransactionEvent(Long userId, double amount, String type, String category) {

        log.info("📩 Transaction Event -> userId={}, amount={}, type={}, category={}",
                userId, amount, type, category);

String message = null;
String normalizedType = type.trim().toUpperCase();
String normalizedCategory = category.trim().toUpperCase();

if (normalizedType.startsWith("DEBIT") && amount >= 4000) {
    message = "🚨 High Spending Alert! You spent ₹" + amount + " on " + category;
}
else if (normalizedType.startsWith("CREDIT") && amount >= 1000) {
    message = "✅ Amount Credited! ₹" + amount + " has been added to your account.";
}
else if (normalizedCategory.contains("SHOPPING") && amount >= 3000) {
    message = "🛍️ Shopping Alert! You spent ₹" + amount + " on " + category;
}
        
        if (message != null) {
            Notfication notification = Notfication.builder()
                    .userId(userId)
                    .message(message)
                    .build();

            notificationRepository.save(notification);

            log.info("✅ Notification saved for userId={}", userId);
        } else {
            log.info("ℹ️ No notification created (conditions not matched)");
        }
    }

    @Override
    public List<Notfication> getUserNotifications(long userId) {
        return notificationRepository.findByUserId(userId);
    }
}
