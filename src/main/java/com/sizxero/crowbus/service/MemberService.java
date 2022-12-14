package com.sizxero.crowbus.service;

import com.sizxero.crowbus.entity.Member;
import com.sizxero.crowbus.entity.member.BusDriver;
import com.sizxero.crowbus.entity.member.Passenger;
import com.sizxero.crowbus.entity.type.RoleType;
import com.sizxero.crowbus.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    public String readNameByLoginId(String loginId) { return memberRepository.findNameByLoginId(loginId); }
    public RoleType findRoleTypeByLoginId(String loginId) { return memberRepository.findRoleTypeByLoginId(loginId); }
    public boolean existLoginId(String loginId) { return memberRepository.existsMemberByLoginId(loginId); }
    public Optional<Member> readOneMember(Long id) { return memberRepository.findById(id); }
    public Optional<Passenger> readOnePassenger(Long id) {
        return memberRepository.findPassengerById(id);
    }
    public Optional<BusDriver> readOneBusDriver(Long id) {
        return memberRepository.findBusDriverById(id);
    }
    public Optional<Passenger> readOnePassengerByLoginId(String loginId) {
        return memberRepository.findPassengerByLoginId(loginId);
    }
    public Optional<BusDriver> readOneBusDriverByLoginId(String loginId) {
        return memberRepository.findBusDriverByLoginId(loginId);
    }

    public Optional<Member> readMemberByLoginId(String loginId) {
        return memberRepository.findByLoginId(loginId);
    }
}
