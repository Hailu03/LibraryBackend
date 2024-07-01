package com.library.programmingexercise.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "keepstrackof")
public class Keepstrackof {
    @EmbeddedId
    private KeepstrackofId id;

    @MapsId("adminID")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "adminID", nullable = false)
    private Admin adminID;

    @MapsId("readerID")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "readerID", nullable = false)
    private ReadersInfo readerID;

    @Column(name = "message", length = 1000)
    private String message;

}