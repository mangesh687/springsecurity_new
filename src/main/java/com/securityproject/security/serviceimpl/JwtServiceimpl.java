package com.securityproject.security.serviceimpl;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.securityproject.security.service.Jwtservice;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoder;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtServiceimpl implements Jwtservice {
	@Value("${spring.jwt.secret}")
	private String SECRET_KEY;
	
	@Value("${spring.jwt.expiration}")
	private long JWTEXPIRATION;

	@Override
	public String extractUsername(String token) {
		// TODO Auto-generated method stub
		return  extractClaim(token, Claims::getSubject);
	}
 
	 private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
	        final Claims claims = extractAllClaims(token);
	        return claimsResolver.apply(claims);
	    }

	    private Claims extractAllClaims(String token) {
	        return Jwts.parserBuilder()
	                .setSigningKey(getSigningKey())
	                .build()
	                .parseClaimsJws(token)
	                .getBody();
	    }

	@Override
	public boolean isTokenValid(String token, UserDetails userDetails) {
		 final String username = extractUsername(token);
	        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
	}

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
    
	@Override
	public String generatedToken(Map<String, Object> claims, UserDetails userDetails) {
		// TODO Auto-generated method stub
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+JWTEXPIRATION))
				.signWith(getSigningKey(),SignatureAlgorithm.HS256)
				.compact();
	}
	private Key getSigningKey() {
		byte[] keyByte=Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyByte);
	}

}
