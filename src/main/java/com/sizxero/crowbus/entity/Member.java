package com.sizxero.crowbus.entity;

import javax.persistence.*;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "MTYPE", length=1)
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
    @Column(name="m_pw", length = 20)
    private String pw;
    @NotNull
    @Column(name="m_name", length = 15)
    private String name;
    @NotNull
    @Column(name="m_phone", length = 30)
    private String phone;

    @OneToMany
    @JoinColumn(name="p_member_id")
    private List<Post> posts = new ArrayList<>();

    @OneToMany
    @JoinColumn(name="rp_member_id")
    private List<Reply> replies = new ArrayList<>();
}