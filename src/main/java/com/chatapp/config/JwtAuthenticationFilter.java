package com.chatapp.config;

import com.chatapp.util.TokenProvider;
import com.chatapp.service.CustomizedUserDetailsService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private CustomizedUserDetailsService customizedUserDetailsService;

    @Autowired
    private TokenProvider jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer")) {
            String token = extractTokenFromHeader(header);
            if (isValidToken(token)) {
                String username = jwtTokenUtil.extractUsernameFromToken(token);
                
                if (username != null && getCurrentAuthentication() == null) {
                    startAuthentication(username);
                }
            }
        } else {
            logger.warn("couldn't find bearer string, will ignore the header");
        }

        chain.doFilter(req, res);
    }

    private void startAuthentication(String username) {
        UserDetails userDetails = customizedUserDetailsService.loadUserByUsername(username);
        SecurityContextHolder.getContext().setAuthentication(convertToAuthentication(userDetails));
        logger.info("authenticated user " + username + ", setting security context");
    }

    private static UsernamePasswordAuthenticationToken convertToAuthentication(UserDetails userDetails) {
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    private static Authentication getCurrentAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    private static String extractTokenFromHeader(String header) {
        return header.replace("Bearer ", "");
    }

    private boolean isValidToken(String token) {
        boolean isValidToken = false;

        try {
            if (jwtTokenUtil.isTokenNotExpired(token)) {
                isValidToken = true;
            }
        } catch (IllegalArgumentException e) {
            logger.error("an error occured during getting username from token", e);
        } catch (ExpiredJwtException e) {
            logger.warn("the token is expired and not valid anymore", e);
        } catch (SignatureException e) {
            logger.error("Authentication Failed. Username or Password not valid.");
        }
        
        return isValidToken;
    }
}