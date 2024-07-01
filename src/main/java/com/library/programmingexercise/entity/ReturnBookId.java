package com.library.programmingexercise.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class ReturnBookId implements Serializable {
    private static final long serialVersionUID = -3025311252292544251L;
    @Column(name = "returnID", nullable = false)
    private Integer returnID;

    @Column(name = "bookID", nullable = false)
    private Integer bookID;

    @Column(name = "readerID", nullable = false)
    private Integer readerID;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ReturnBookId entity = (ReturnBookId) o;
        return Objects.equals(this.returnID, entity.returnID) &&
                Objects.equals(this.readerID, entity.readerID) &&
                Objects.equals(this.bookID, entity.bookID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(returnID, readerID, bookID);
    }

}