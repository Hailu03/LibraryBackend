package com.library.programmingexercise.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "readernotification")
public class ReaderNotification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notificationID", nullable = false)
    private Integer notificationID;

    @MapsId("readerID")
    @JoinColumn(name = "readerID", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Integer readerID;

    @MapsId("bookID")
    @JoinColumn(name = "bookID", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Integer bookID;

    @Column(name = "message", length = 1000)
    private String message;

    @Column(name = "dateTime", nullable = false)
    private LocalDateTime dateTime;

    @Column(name = "isRead", nullable = false)
    private boolean isRead;
}
