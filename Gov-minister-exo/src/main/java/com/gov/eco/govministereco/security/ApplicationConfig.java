package com.gov.eco.govministereco.security;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {


//    private final RecruiterRepository recruiterRepository;
//    private final AgentRepository agentRepository;

    @Bean
    public UserDetailsService userDetailsService(){

//        return username -> recruiterRepository.findByEmail(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new UserDetailsService(){

            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//                Optional<Recruiter> recruiter = recruiterRepository.findByEmail(username);

                return null;
//
//                if(recruiter.isEmpty()){
//                    System.out.println(recruiter + "noroor");
//                    return agentRepository.getAgentByEmail(username)
//                            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//                }else {
//                    System.out.println(recruiter + "ues");
//
//                    return recruiterRepository.findByEmail(username)
//                            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//                }

            }

        };
//        return username -> agentRepository.getAgentByEmail(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }



}
