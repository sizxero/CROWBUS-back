package com.sizxero.crowbus.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sizxero.crowbus.entity.type.RoleType;
import com.sun.istack.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@SuperBuilder
public class Member {
    @Id
    @GeneratedValue
    @Column(name="m_id")
    private Long id;
    @NotNull
    @Column(name="m_login_id", length = 20)
    private String loginId;
    @NotNull
    @Column(name="m_pw")
    private String pw;
    @NotNull
    @Column(name="m_name", length = 15)
    private String name;
    @NotNull
    @Column(name="m_phone", length = 30)
    private String phone;
    @NotNull
    @Column(name="m_type")
    private RoleType roleType;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="p_member_id")
    private List<Post> posts = new ArrayList<>();

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="rp_member_id")
    private List<Reply> replies = new ArrayList<>();
}