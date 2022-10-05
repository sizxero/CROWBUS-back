package com.sizxero.crowbus.service;

import com.sizxero.crowbus.entity.Member;
import com.sizxero.crowbus.entity.member.BusDriver;
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

    public Optional<Member> readOneMember(Long id) { return memberRepository.findById(id); }
    public Optional<BusDriver> readOneBusDriver(Long id) {
        return memberRepository.findBusDriverById(id);
    }
}
