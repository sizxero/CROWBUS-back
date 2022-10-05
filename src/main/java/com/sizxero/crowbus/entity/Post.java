package com.sizxero.crowbus.entity;

import javax.persistence.*;

import com.sizxero.crowbus.entity.common.BaseTimeEntity;
import com.sizxero.crowbus.entity.type.BoardType;
import com.sun.istack.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Post extends BaseTimeEntity {
    @Id
    @GeneratedValue
    @Column(name="p_id")
    private Long id;
    @NotNull
    @Column(name="p_boardtype")
    private BoardType boardType;
    @NotNull
    @Column(name="p_title", length = 50)
    private String title;
    @NotNull
    @Column(name="p_content")
    private String content;
    @Column(name="p_hit")
    private Integer hit;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="rp_post_id")
    private List<Reply> replies = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="p_member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "p_route_id")
    private Route route;
}
