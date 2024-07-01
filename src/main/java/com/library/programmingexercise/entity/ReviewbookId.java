package com.library.programmingexercise.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class ReviewbookId implements Serializable {
    private static final long serialVersionUID = 8247918023678256841L;
    @Column(name = "bookID", nullable = false)
    private Integer bookID;

    @Column(name = "readerID", nullable = false)
    private Integer readerID;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ReviewbookId entity = (ReviewbookId) o;
        return Objects.equals(this.readerID, entity.readerID) &&
                Objects.equals(this.bookID, entity.bookID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(readerID, bookID);
    }

}