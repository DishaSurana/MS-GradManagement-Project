package com.accolite_ms.gradManagement.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

//@Cacheable
//@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name="skill")
public class Skill implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long id;

    @Column(unique = true)
    String skillName;

    public Skill() {
    }

    public Skill(String skillName) {
        this.skillName = skillName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Skill skill = (Skill) o;
        return id == skill.id && skillName.equals(skill.skillName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, skillName);
    }

    @Override
    public String toString() {
        return "Skill{" +
                "id=" + id +
                ", skillName='" + skillName + '\'' +
                '}';
    }
}
