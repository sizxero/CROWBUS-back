package com.sizxero.crowbus.service;

import com.sizxero.crowbus.entity.Member;
import com.sizxero.crowbus.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LoginService {
    @Autowired
    private MemberRepository repository;

    public Member getByCredentials(final String loginId, final String pw) {
        return repository.findMemberByLoginIdAndPw(loginId, pw);
    }
}
