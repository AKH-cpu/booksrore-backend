package com.akhadam.kitabi.security;

import com.akhadam.kitabi.SpringApplicationContext;
import com.akhadam.kitabi.dto.UserDto;
import com.akhadam.kitabi.requests.UserLoginRequest;
import com.akhadam.kitabi.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public AuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        try {
            UserLoginRequest creds = new ObjectMapper().readValue(request.getInputStream(), UserLoginRequest.class);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    creds.getEmail(),
                    creds.getPassword(),
                    new ArrayList<>()));

        } catch (IOException e) {
            throw new RuntimeException(e);

        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException {

        String userEmail = ((User) authResult.getPrincipal()).getUsername();

        long expirationTime = Calendar.getInstance().getTimeInMillis() + SecurityConstants.EXPIRATION_TIME;

      UserService userService = (UserService) SpringApplicationContext.getBean("userServiceImpl");
       UserDto userDto = userService.findByEmail(userEmail);

        String token = Jwts.builder()
                .setSubject(userEmail)
               .claim("id", userDto.getUserId())
              .claim("name", userDto.getName())
                .setExpiration(new Date(expirationTime))
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.TOKEN_SECRET)
                .compact();


        response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
        response.addHeader("user_id", userDto.getUserId());
        response.getWriter().write("{\"token\": \"" + token + "\", \"id\": \"" + userDto.getUserId() + "\"}");

    }
}
