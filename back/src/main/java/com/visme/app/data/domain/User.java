package com.visme.app.data.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@ToString(exclude = "projects")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    /**
     * Created at
     */
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * Modified at
     */
    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime modifiedAt;

    /**
     * User name
     */
    @Column(nullable = false)
    private String name;

    /**
     * User email
     */
    @Column(nullable = false)
    private String email;

    /**
     * User password
     */
    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Project> projects;
}
