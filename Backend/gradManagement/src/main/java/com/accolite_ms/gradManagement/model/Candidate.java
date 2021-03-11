package com.accolite_ms.gradManagement.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

//@Cacheable
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name="candidate")
public class Candidate implements Serializable {

    @Id
    String grad_id;

    String name;
    String address;
    String email;
    String mobileNumber;

    @Enumerated(EnumType.STRING)
    Degree degree;

    @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, fetch = FetchType.EAGER)
    Institute institute;

    @Enumerated(EnumType.STRING)
    JobLocation location;

    Date joiningDate;
    String description;
    String feedback;

    Boolean isDeleted = false;

//    @Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, fetch = FetchType.EAGER)
    Set<Skill> skillSet;

    public Candidate() {
    }

    public Candidate(String grad_id, String name, String address, String email, String mobileNumber, Degree degree, Institute institute, JobLocation location, Date joiningDate, String description, String feedback, Set<Skill> skillSet) {
        this.grad_id = grad_id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.degree = degree;
        this.institute = institute;
        this.location = location;
        this.joiningDate = joiningDate;
        this.description = description;
        this.feedback = feedback;
        this.skillSet = skillSet;
    }

    public String getGrad_id() {
        return grad_id;
    }

    public void setGrad_id(String grad_id) {
        this.grad_id = grad_id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Degree getDegree() {
        return degree;
    }

    public void setDegree(Degree degree) {
        this.degree = degree;
    }

    public Institute getInstitute() {
        return institute;
    }

    public void setInstitute(Institute institute) {
        this.institute = institute;
    }

    public JobLocation getLocation() {
        return location;
    }

    public void setLocation(JobLocation location) {
        this.location = location;
    }

    public Date getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(Date joiningDate) {
        this.joiningDate = joiningDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Skill> getSkillSet() {
        return skillSet;
    }

    public void setSkillSet(Set<Skill> skillSet) {
        this.skillSet = skillSet;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public void setAll(Candidate fromCandidate){
        this.name = fromCandidate.name;
        this.address = fromCandidate.address;
        this.email = fromCandidate.email;
        this.mobileNumber = fromCandidate.mobileNumber;
        this.degree = fromCandidate.degree;
        this.institute = fromCandidate.institute;
        this.location = fromCandidate.location;
        this.joiningDate = fromCandidate.joiningDate;
        this.description = fromCandidate.description;
        this.feedback = fromCandidate.feedback;
        this.skillSet = fromCandidate.skillSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Candidate candidate = (Candidate) o;
        return grad_id.equals(candidate.grad_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(grad_id);
    }

    @Override
    public String toString() {
        return "Candidate{" +
                "grad_id='" + grad_id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", degree=" + degree +
                ", institute=" + institute +
                ", location=" + location +
                ", joiningDate=" + joiningDate +
                ", description='" + description + '\'' +
                ", feedback='" + feedback + '\'' +
                ", skillSet=" + skillSet +
                '}';
    }
}
