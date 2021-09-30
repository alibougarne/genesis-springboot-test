package com.genisis.test.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.genisis.test.model.Contact;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "\"enterprises\"")
public class Enterprise {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private UUID Id;

    @Basic
    @Column(name = "name", unique = true, nullable = false, length = 100)
    private String name;

    @Basic
    @Column(name = "address", nullable = false, length = 500)
    private String address;

    @Basic
    @Column(name = "tva_number", nullable = false)
    private Integer tvaNumber;

    @Basic
    @Column(name = "created_at", nullable = true)
    @Temporal(TemporalType.DATE)
    private Date createdAt;

    @Basic
    @Column(name = "updated_at", nullable = true)
    @Temporal(TemporalType.DATE)
    private Date updatedAt;

    // relations
    @ManyToMany(mappedBy = "enterprises")
    @JsonIgnore
    private Set<Contact> contacts;

    @PrePersist
    public void prePersist() {
        createdAt = new Date();
        updatedAt = createdAt;
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = new Date();
    }
}
