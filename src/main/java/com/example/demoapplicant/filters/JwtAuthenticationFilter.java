package com.example.demoapplicant.filters;

import com.example.demoapplicant.model.entity.User;
import com.example.demoapplicant.repository.TokenRepository;
import com.example.demoapplicant.repository.UserRepository;
import com.example.demoapplicant.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  @Autowired
  private JwtService jwtService;
  @Autowired
  private UserDetailsService userDetailsService;
  @Autowired
  private TokenRepository tokenRepository;
  @Autowired
  private UserRepository userRepository;

  @Override
  protected void doFilterInternal(@NonNull HttpServletRequest request,
                                  @NonNull HttpServletResponse response,
                                  @NonNull FilterChain filterChain) throws ServletException, IOException {
    if (request.getServletPath().contains("/auth")) {
      filterChain.doFilter(request, response);
      return;
    }

    if (request.getServletPath().contains("/swagger-ui")) {
      filterChain.doFilter(request, response);
      return;
    }

    String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      filterChain.doFilter(request, response);
      return;
    }

    String jwt = authHeader.substring(7);
    String userEmail = jwtService.extractUsername(jwt);
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (userEmail == null || authentication != null) {
      filterChain.doFilter(request, response);
      return;
    }

    UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
    boolean isTokenExpiredOrRevoked = tokenRepository.findByToken(jwt)
        .map(token -> !token.getIsExpired() && !token.getIsRevoked())
        .orElse(false);

    if (isTokenExpiredOrRevoked) {
      Optional<User> user = userRepository.findByEmail(userEmail);

      if (user.isPresent()) {
        boolean isTokenValid = jwtService.isTokenValid(jwt, user.get());

        if (isTokenValid) {
          UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
              userDetails,
              null,
              userDetails.getAuthorities()
          );
          authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
          SecurityContextHolder.getContext().setAuthentication(authToken);
        }
      }
    }

    filterChain.doFilter(request, response);
  }
}
