package com.sizxero.crowbus.entity;

import javax.persistence.*;

import com.sizxero.crowbus.entity.common.BaseTimeEntity;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Reply extends BaseTimeEntity {
    @Id
    @GeneratedValue
    @Column(name="rp_id")
    private Long id;
    @NotNull
    @Column(name="rp_content")
    private String content;

    @ManyToOne
    @JoinColumn(name="rp_post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name="rp_member_id")
    private Member member;
}
