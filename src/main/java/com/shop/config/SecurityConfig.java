package com.shop.config;

import com.shop.entity.Member;
import com.shop.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter {

    @Autowired
    MemberService memberService;

    @Override
    protected void configure(HttpSecurity http) throws Exception{ //Http =>HttpSecurity로 =>Https
        http.formLogin()
                .loginPage("/members/login")        // 1.로그인할때 성공하면
                .defaultSuccessUrl("/")             // 여기 페이지로 이동
                .usernameParameter("email")         // username에 email을 보내고
                .failureUrl("/members/login/error")  // 2. 로그인을 실패하면 /error로 보내고
                .and()
                .logout()                           // 3. 로그아웃 성공시
                .logoutRequestMatcher(new AntPathRequestMatcher("/members/logout"))
                .logoutSuccessUrl("/");             // 여기 페이지로 이동

        http.authorizeRequests()
                .mvcMatchers("/","/members/**", "/item/**", "/images/**").permitAll()//permitall:모든 사용자가 로그인없이 사용할수 있게
                .mvcMatchers("/admin/**").hasRole("ADMIN")//운영자만 접속할수 있게 설정
                .anyRequest().authenticated();//URL을 임의적으로 쳐서 들어올때 로그인하게 인증요청
        http.exceptionHandling() //
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint());
    }

    @Override
    public void configure(WebSecurity web) throws Exception { //인증되지 않는 사용자가 접근했을때도 하위 파일들,
        web.ignoring().antMatchers("/css/**","/js/**","/img/**");//css, js, img 보여지게 설정
    }

    @Bean
    public PasswordEncoder passwordEncoder(){ //비밀번호 암호화
        return new BCryptPasswordEncoder();
    }

    @Override      // 아래 Builder 타입을 통해서 memberService로 DB정보를 가져오고 유저 인증정보를 설정
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(memberService)      //
                .passwordEncoder(passwordEncoder()); //비밀번호 암호화 지정
    }

}
