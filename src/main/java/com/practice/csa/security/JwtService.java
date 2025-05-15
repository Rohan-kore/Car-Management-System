package com.practice.csa.security;

import java.security.Key;
import java.time.Duration;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	@Value("${app.jwt.secret}")
	private String secret;


	public	String createJwt(String username ,String userRole , Duration duration) {
		return	Jwts.builder().setClaims(Map.of(
				"role",userRole,
				"username", username
				))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+duration.getSeconds()*1000))

				.signWith(signatureKey(),SignatureAlgorithm.HS256).compact();
	}

	public   Key signatureKey() {

		return   Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));

	}


}
