package com.Personnel.Ticketing.security;



import com.Personnel.Ticketing.Exception.TokenInvalideException;
import com.Personnel.Ticketing.Models.Jwt;
import com.Personnel.Ticketing.Models.Utilisateur;
import com.Personnel.Ticketing.Repository.JwtDao;
import com.Personnel.Ticketing.Services.UtilisateurServiceImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class JwtService {

    public static final String BEARER = "bearer";
    public static final String TOKEN_INVALIDE_OU_INCONNU = "Token invalide ou inconnu";
    private final String ENCRYPTION_KEY = "6eeb9b411856a8d557246fd07de41b0a58407bd0fbe526edb526f2d5e87a6362";
    private UtilisateurServiceImpl utilisateurService;
    private JwtDao jwtDao;

    public Map<String,String> generate(String username){
        Utilisateur utilisateur = (Utilisateur) this.utilisateurService.loadUserByUsername(username);
        this.disableTokens(utilisateur);

        final Map<String, String> jwtMap = this.generateJwt(utilisateur);

//        RefreshToken refreshToken = RefreshToken.builder()
//                .valeur(UUID.randomUUID().toString())
//                .expire(false)
//                .creation(Instant.now())
//                .expiration(Instant.now().plusMillis(60 *60 *60 *1000))
//                .build();

        Jwt jwt = Jwt.builder()
                .value((String) jwtMap.get(BEARER))
                .desactive(false)
                .expire(false)
                .utilisateur(utilisateur)
//                .refreshToken(refreshToken)
                .build();
        jwtDao.save(jwt);
//        jwtMap.put(REFRESH,  refreshToken.getValeur());
//        jwtMap.put("user",  utilisateur);

        return jwtMap;

    }

    private Map<String, String> generateJwt(Utilisateur utilisateur){

//        Map<String, String> claims = Map.of(
//                "nom", utilisateur.getNom(),
//                "email", utilisateur.getEmail()
//        );

        final long currentedTimeMillis = System.currentTimeMillis();
        final long expirationTimeMillis = currentedTimeMillis+30*60*1000;

        final  Map<String,Object> claims= Map.of(
                "nom", utilisateur.getNom(),
                "email", utilisateur.getEmail(),
                Claims.EXPIRATION,new Date(expirationTimeMillis),
                Claims.SUBJECT,utilisateur.getEmail()

        );

        final String bearer = Jwts.builder()
                .setIssuedAt(new Date(currentedTimeMillis))
                .setExpiration(new Date(expirationTimeMillis))
                .setSubject(utilisateur.getEmail())
                .setClaims(claims)
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
        return Map.of("bearer",bearer);
    }

    private Key getKey(){
        final byte[] decoder= Decoders.BASE64.decode(ENCRYPTION_KEY);
        return Keys.hmacShaKeyFor(decoder);


    }

    public String extractUsername(String token) {
        return this.getClaim(token, Claims::getSubject);
    }

    public boolean isTokenExpired(String token) {
        Date expirationDate = getExpirationDateFromToken(token);
        return expirationDate.before(new Date());
    }

    private Date getExpirationDateFromToken(String token) {
        return this.getClaim(token, Claims::getExpiration);
    }

    private <T> T getClaim(String token, Function<Claims, T> function) {
        Claims claims = getAllClaims(token);
        return function.apply(claims);
    }

    private Claims getAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(this.getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private void disableTokens(Utilisateur utilisateur) {
        final List<Jwt> jwtList = this.jwtDao.findUtilisateur(utilisateur.getEmail()).peek(
                jwt -> {
                    jwt.setDesactive(true);
                    jwt.setExpire(true);
                }
        ).collect(Collectors.toList());

        this.jwtDao.saveAll(jwtList);
    }


    public Jwt tokenByValue(String value) {
        Optional<Jwt> jwt = this.jwtDao.findByValueAndDesactiveAndExpire(value, false, false);
        if(jwt.isEmpty()) {
            System.out.println("Token introuvable ou invalide : " + value);
            throw   new TokenInvalideException(TOKEN_INVALIDE_OU_INCONNU);
        }
        return jwt.get();
    }


    public String deconnexion() {
        Utilisateur utilisateur = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Jwt jwt = this.jwtDao.findUtilisateurValidToken(
                utilisateur.getEmail(),
                false,
                false
        ).orElseThrow(() -> new RuntimeException(TOKEN_INVALIDE_OU_INCONNU));
        if(jwt!=null){
            jwt.setExpire(true);
            jwt.setDesactive(true);
            this.jwtDao.save(jwt);
        }
        return "la déconnexion a été effectué avec succè";

    }

    //SUPPRESSION PROGRAME DES OLD TOKEN
    //@Scheduled(cron = "@daily")
    @Scheduled(cron = "0 */1 * * * *")
    public void removeUselessJwt() {
        log.info("Suppression des token à {}", Instant.now());
        //System.out.println("Suppression des token à {}"+ Instant.now());
        this.jwtDao.deleteAllByExpireAndDesactive(true, true);
    }
}
