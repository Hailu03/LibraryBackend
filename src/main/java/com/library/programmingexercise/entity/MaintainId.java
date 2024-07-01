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
public class MaintainId implements Serializable {
    private static final long serialVersionUID = 8815519710528377849L;
    @Column(name = "bookID", nullable = false)
    private Integer bookID;

    @Column(name = "adminID", nullable = false)
    private Integer adminID;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        MaintainId entity = (MaintainId) o;
        return Objects.equals(this.adminID, entity.adminID) &&
                Objects.equals(this.bookID, entity.bookID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adminID, bookID);
    }

}
