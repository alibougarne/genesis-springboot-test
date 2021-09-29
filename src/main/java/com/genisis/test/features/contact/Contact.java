package com.genisis.test.features.contact;

import com.genisis.test.features.enterprise.Enterprise;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "\"contacts\"")
public class Contact implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private UUID Id;

    @Basic
    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @Basic
    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    @Basic
    @Column(name = "address", nullable = false, length = 500)
    private String address;

    @Basic
    @Column(name = "is_freelancer")
    private Boolean  isFreelancer = false;

    @Basic
    @Column(name = "tva_number", nullable = true)
    private Integer tvaNumber = null;

    @Basic
    @Column(name = "created_at", nullable = true)
    @Temporal(TemporalType.DATE)
    private Date createdAt;

    @Basic
    @Column(name = "updated_at", nullable = true)
    @Temporal(TemporalType.DATE)
    private Date updatedAt;

    // relations
    @ManyToMany
    @JoinTable(
            name = "contacts_enterprises",
            joinColumns = {
                    @JoinColumn(name = "contact_id"),
            },
            inverseJoinColumns = {@JoinColumn(name = "enterprise_id")}
    )
    private Set<Enterprise> enterprises;

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
