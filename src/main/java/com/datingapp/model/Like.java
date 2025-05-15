package com.datingapp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "likes", indexes = {
        @Index(name = "idx_liker_id", columnList = "liker_id"),
        @Index(name = "idx_liked_id", columnList = "liked_id")
},  uniqueConstraints = {
        @UniqueConstraint(name = "uk_liker_liked", columnNames = {"liker_id", "liked_id"})
})
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "liker_id", nullable = false)
    private User liker;

    @ManyToOne
    @JoinColumn(name = "liked_id", nullable = false)
    private User liked;

    @Column(nullable = false)
    private boolean isMutual = false;

    @Column
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

}
