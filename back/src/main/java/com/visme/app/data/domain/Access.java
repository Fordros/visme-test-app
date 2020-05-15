package com.visme.app.data.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "accesses")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Access {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    /**
     * Created at
     */
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    /**
     * Modified at
     */
    @LastModifiedDate
    @Column(nullable = false)
    private Date modifiedAt;

    /**
     * Project
     */
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    /**
     * Access time
     */
    private Long time = System.currentTimeMillis();
}
