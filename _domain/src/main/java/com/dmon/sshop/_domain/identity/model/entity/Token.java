package com.dmon.sshop._domain.identity.model.entity;

import com.dmon.sshop._domain.common.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.time.Instant;

@Entity
@Table(name = "tokens")
@DynamicInsert
@DynamicUpdate
@SQLDelete(sql = "UPDATE tokens SET deleted = true WHERE account_id=?")
@SQLRestriction("deleted = false")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Token extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "tokenId", nullable = false, updatable = false)
    String id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accountId", nullable = false, updatable = false, unique = true)
    @JsonIgnore
    Account account;

    String refreshToken;

    Instant refreshExpDate;
}
