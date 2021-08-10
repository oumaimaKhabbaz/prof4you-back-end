package com.prof4you.app.config;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.prof4you.app.dto.LoginDto;
import com.prof4you.app.dto.TokenDto;
import com.prof4you.app.entities.Account;
import com.prof4you.app.repositories.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@AllArgsConstructor
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private static final Logger LOGGER = Logger.getLogger(JWTAuthenticationFilter.class.getName());

    private AuthenticationManager authenticationManager;
    private AccountRepository accountRepository;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        Account account = null;
        LoginDto loginDto = null;
        try {
            loginDto = new ObjectMapper().readValue(request.getInputStream(), LoginDto.class);

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        account = accountRepository.findByEmail(loginDto.getEmail());
        if (account != null) {
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
        }
        throw new RuntimeException("Inactive Email");
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException, ServletException {
        User user = (User) authResult.getPrincipal();
        Gson gson = new Gson();
        List<String> roles = new ArrayList<>();
        authResult.getAuthorities().forEach(a -> {
            roles.add(a.getAuthority());
        });
        String jwt = JWT.create()
                .withIssuer(request.getRequestURI())
                .withSubject(user.getUsername())
                .withArrayClaim("roles", roles.toArray(new String[roles.size()]))
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityParams.EXPIRATION))
                .sign(Algorithm.HMAC256(SecurityParams.SECRET));
        TokenDto tokenDto = new TokenDto();
        tokenDto.setAUTHORIZATION(jwt);

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(gson.toJson(tokenDto));
        out.flush();
        //response.addHeader(SecurityParams.JWT_HEADER_NAME, jwt);
    }

}