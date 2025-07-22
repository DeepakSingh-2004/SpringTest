package com.example.SpringTest.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtHelper {

    public static  final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    // public static final long JWT_TOKEN_VALIDITY = 60;

    private String secret =  "afafafafafafafafafafafafafafacasdasdchdsndbwkjhbjeklmhfeiujhewbhucbdncdhsjcbhdubdcbdkhcbgfcgev";

    //retrieve username from jwt token

    public Date getExpirationDateFromToken(String token){
        return  getClaimFromToken(token, Claims::getExpiration);
    }

   public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver){
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
   }

   //FOR RETRIEVING ANY INFORMATION FROM TOKEN WE WILL NEED THE SECRET KEY
   public Claims getAllClaimsFromToken(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
   }

   //CHECK IF THE TOKEN HAS EXPIRED
    final Boolean isTokenExpired(String token){
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());

    }

    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername());
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)// any extra data you want to add
                .setSubject(subject)// usually the username
                .setIssuedAt(new Date(System.currentTimeMillis()))// issue time
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))// expiry
                .signWith(SignatureAlgorithm.HS512, secret)// algorithm + secret key
                .compact();// generate the token

    }


    // ✅ 7. Validate token
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }


    // ✅ 8. Get username from token
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }
}

    //WHILE CREATING THE TOKEN -
    //1. DEFINE CLAIMS OF THE TOKEN, LIKE ISSUER, EXPIRATION, SUBJECT, AND THE ID
    //2. SIGN THE JWT USING THE HS512 ALGORITHM AND SECRET KEY.
    //3. ACCORDING TO JWS COMPACT SERIALIZATION(https://tools.ietf.orf/html/draft-ietf-jose-json-web-signature-41#sec




