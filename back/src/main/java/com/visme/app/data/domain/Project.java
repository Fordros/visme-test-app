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
import java.util.ArrayList;
import java.util.List;

@ToString(exclude = "accesses")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "projects")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Project {

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
     * Title
     */
    @Column(nullable = false)
    private String title;

    /**
     * Type
     */
    @Column(nullable = false)
    private ProjectType type;

    /**
     * Favorite state
     */
    private Boolean favorite = false;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * Accesses to project
     */
    @OneToMany(mappedBy = "project", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Access> accesses = new ArrayList<>();

    public void addAccessTime(Access access) {
        this.accesses.add(access);
    }
}
