package com.sizxero.crowbus.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "MTYPE")
public class Member {
    @Id
    @GeneratedValue
    private Long id;
    private String loginId;
    private String pw;
    private String name;
    private String phone;

    @OneToMany
    @JoinColumn(name="member_id")
    private List<Reservation> reservations = new ArrayList<>();

    @OneToMany
    @JoinColumn(name="member_id")
    private List<Post> posts = new ArrayList<>();

    @OneToMany
    @JoinColumn(name="member_id")
    private List<Reply> replies = new ArrayList<>();
}