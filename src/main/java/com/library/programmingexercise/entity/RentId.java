package com.library.programmingexercise.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.*;
import org.hibernate.Hibernate;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RentId implements Serializable {
    @Serial
    private static final long serialVersionUID = -4472636900630881246L;

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "rentID", nullable = false)
    private Integer rentID;

    @Column(name = "bookID", nullable = false)
    private Integer bookID;

    @Column(name = "readerID", nullable = false)
    private Integer readerID;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        RentId entity = (RentId) o;
        return Objects.equals(this.readerID, entity.readerID) &&
                Objects.equals(this.rentID, entity.rentID) &&
                Objects.equals(this.bookID, entity.bookID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(readerID, rentID, bookID);
    }

}