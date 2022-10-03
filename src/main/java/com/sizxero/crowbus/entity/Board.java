package com.sizxero.crowbus.entity;

import javax.persistence.*;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Board {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private String name;

    @OneToMany
    @JoinColumn(name="board_id")
    private List<Post> posts = new ArrayList<>();
}
