package com.sizxero.crowbus.entity.member;

import javax.persistence.*;

import com.sizxero.crowbus.entity.Member;
import com.sizxero.crowbus.entity.Reservation;
import com.sizxero.crowbus.entity.Route;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SuperBuilder
@DiscriminatorValue("P")
public class Passenger extends Member {

    @ManyToOne
    @JoinColumn(name="favorite_route")
    private Route favoriteRoute;

    @OneToMany
    @JoinColumn(name="passenger_id")
    private List<Reservation> reservations = new ArrayList<>();
}