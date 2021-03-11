package com.accolite_ms.gradManagement.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

//@Cacheable
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name="institute")
public class Institute implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long id;

    String name;

    String address;

    public Institute() {
    }

    public Institute(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Institute institute = (Institute) o;
        return id == institute.id && name.equals(institute.name) && address.equals(institute.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address);
    }

    @Override
    public String toString() {
        return "Institute{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
