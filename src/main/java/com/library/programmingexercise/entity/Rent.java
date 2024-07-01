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
@Table(name = "rent")
public class Rent {
    @EmbeddedId
    private RentId id;

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

    @Column(name = "reserve_date", nullable = false)
    private LocalDate reserveDate;

    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;
}