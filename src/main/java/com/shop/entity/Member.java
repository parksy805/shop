package com.shop.entity;


import com.shop.constant.Role;
import com.shop.dto.MemberFormDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Table(name="Member")
@Getter
@Setter
@ToString
public class Member extends BaseEntity{

    @Id
    @Column(name="name_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @Column(unique = true)//이메일로 회원구분
    private String email;

    private String password;
    private String address;

    @Enumerated(EnumType.STRING) //enum은 기본적으로 순서가 지정됨
    private Role role;

    public static Member createMember(MemberFormDto memberFormDto,
                                      PasswordEncoder passwordEncoder){
        Member member = new Member();
        member.setName(memberFormDto.getName());
        member.setEmail(memberFormDto.getEmail());
        member.setAddress(memberFormDto.getAddress());
        String password = passwordEncoder.encode(memberFormDto.getPassword());//암호화해서 pw저장 (ex)utf8
        member.setPassword(password);
        member.setRole(Role.ADMIN);
        return member;
    }

}
