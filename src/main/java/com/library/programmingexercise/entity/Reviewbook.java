package com.library.programmingexercise.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reviewbook")
public class Reviewbook {
    @EmbeddedId
    private ReviewbookId id;

    @MapsId("bookID")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "bookID", nullable = false)
    private Book bookID;

    @MapsId("readerID")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "readerID", nullable = false)
    private ReadersInfo readerID;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "review_text", length = 1000)
    private String reviewText;

}