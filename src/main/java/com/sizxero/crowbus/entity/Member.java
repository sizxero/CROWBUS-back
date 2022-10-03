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
@DiscriminatorColumn(name = "MTYPE")
@SuperBuilder
public class Member {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private String loginId;
    @NotNull
    private String pw;
    @NotNull
    private String name;
    @NotNull
    private String phone;

    @OneToMany
    @JoinColumn(name="member_id")
    private List<Post> posts = new ArrayList<>();

    @OneToMany
    @JoinColumn(name="member_id")
    private List<Reply> replies = new ArrayList<>();
}