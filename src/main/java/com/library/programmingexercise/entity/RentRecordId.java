package com.library.programmingexercise.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RentRecordId implements Serializable {
    private static final long serialVersionUID = 1081397176841675562L;

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
        RentRecordId entity = (RentRecordId) o;
        return Objects.equals(this.readerID, entity.readerID) &&
                Objects.equals(this.rentID, entity.rentID) &&
                Objects.equals(this.bookID, entity.bookID);
    }
    @Override
    public int hashCode() {
        return Objects.hash(readerID, rentID, bookID);
    }

}