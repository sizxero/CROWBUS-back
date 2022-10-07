package com.sizxero.crowbus.repository;

import com.sizxero.crowbus.entity.Member;
import com.sizxero.crowbus.entity.member.BusDriver;
import com.sizxero.crowbus.entity.member.Passenger;
import com.sizxero.crowbus.entity.type.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {
    // 로그인 성공, 실패 여부
    boolean existsMemberByLoginIdAndPw(String loginId, String pw);
    Member findMemberByLoginIdAndPw(String loginId, String pw);
    // 아이디 중복 체크
    boolean existsMemberByLoginId(String loginId);
    // 아이디로 이름 찾기
    String findNameByLoginId(String loginId);
    // 아이디로 회원 정보 조회
    Optional<Member> findByLoginId(String loginId);

    @Query("Select m.roleType from Member m where m.loginId=?1")
    RoleType findRoleTypeByLoginId(String loginId);

    Optional<Passenger> findPassengerById(long id);
    Optional<BusDriver> findBusDriverById(long id);
    Optional<Passenger> findPassengerByLoginId(String loginId);
    Optional<BusDriver> findBusDriverByLoginId(String loginId);
}
