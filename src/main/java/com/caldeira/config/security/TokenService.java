package com.caldeira.config.security;
import com.caldeira.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Service
public class TokenService {

	@Value("${session.jwt.expiration:86400000}")
	private String expiration;

	@Value("${session.jwt.secret:$2a$10$z4fao7BU0.bN27q4on2Wk.QU50l/MIvnoWOS3l4JpMyuh9s98BWUS}")
	private String secret;

	private Key getSigningKey() {
		byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	public String generateToken(Authentication authentication) {
		User loggedin = (User) authentication.getPrincipal();
		Date today = new Date();
		Date expirationTime = new Date(today.getTime() + Long.parseLong(expiration));

		return Jwts.builder()
				.setIssuer("SESSION API;)")
				.setSubject(loggedin.getId().toString())
				.setIssuedAt(today)
				.setExpiration(expirationTime)
				.signWith(getSigningKey(), SignatureAlgorithm.HS256) // <-- CHAVE CORRETA
				.compact();
	}

	public boolean isTokenValid(String token) {
		try {
			Jwts.parserBuilder()
					.setSigningKey(getSigningKey()) // <-- CHAVE CORRETA
					.build()
					.parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			System.out.println("Erro ao validar token: " + e.getMessage());
			return false;
		}
	}

	public String getIdUsuario(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(getSigningKey()) // <-- CHAVE CORRETA
				.build()
				.parseClaimsJws(token)
				.getBody()
				.getSubject();
	}
}