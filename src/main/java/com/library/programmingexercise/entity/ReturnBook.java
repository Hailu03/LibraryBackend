package com.library.programmingexercise.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "returnbook")
public class ReturnBook {
    @EmbeddedId
    private ReturnBookId id;

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

    @Column(name = "return_date", nullable = false)
    private LocalDate returnDate;

    @Column(name = "rentID", nullable = false)
    private Integer rentID;

}