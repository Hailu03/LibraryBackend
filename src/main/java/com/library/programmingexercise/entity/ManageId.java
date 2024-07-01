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
public class ManageId implements Serializable {
    private static final long serialVersionUID = -4856198053587535793L;
    @Column(name = "adminID", nullable = false)
    private Integer adminID;

    @Column(name = "bookID", nullable = false)
    private Integer bookID;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ManageId entity = (ManageId) o;
        return Objects.equals(this.adminID, entity.adminID) &&
                Objects.equals(this.bookID, entity.bookID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adminID, bookID);
    }

}