package com.sizxero.crowbus.entity.member;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.sizxero.crowbus.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SuperBuilder
@DiscriminatorValue("PASSENGER")
public class Passenger extends Member {
    private String favoriteRoute;
}