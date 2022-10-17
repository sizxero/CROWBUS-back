package com.sizxero.crowbus.service;

import com.sizxero.crowbus.entity.Member;
import com.sizxero.crowbus.entity.member.BusDriver;
import com.sizxero.crowbus.entity.member.Passenger;
import com.sizxero.crowbus.repository.MemberRepository;
import com.sizxero.crowbus.security.Encrypt;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class SignUpService {
    @Autowired
    private MemberRepository repository;

    public Optional<Passenger> signupPassenger(final Passenger entity) {
        validate(entity);
        Encrypt enc = new Encrypt();
        entity.setPw(enc.getEncrypt(entity.getPw(), "MD5"));
        repository.save(entity);
        return repository.findPassengerById(entity.getId());
    }

    public Optional<BusDriver> signupBusDriver(final BusDriver entity) {
        validate(entity);
        Encrypt enc = new Encrypt();
        entity.setPw(enc.getEncrypt(entity.getPw(), "MD5"));
        repository.save(entity);
        return repository.findBusDriverById(entity.getId());
    }

    public void validate(final Member entity) {
        if(entity == null) {
            log.warn("Entity cannot be null");
            throw new RuntimeException("Entity cannot be null.");
        }
        if(entity.getLoginId() == null) {
            log.warn("Unknown user.");
            throw new RuntimeException("Unknown user.");
        }
    }
}
