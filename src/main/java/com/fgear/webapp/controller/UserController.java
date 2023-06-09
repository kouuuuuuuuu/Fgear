package com.fgear.webapp.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fgear.webapp.domain.Role;
import com.fgear.webapp.domain.TokenRequest;
import com.fgear.webapp.domain.User;
import com.fgear.webapp.responseType.UserResponse;
import com.fgear.webapp.responseType.UserResponseByRoleUser;
import com.fgear.webapp.service.emailService.EmailSenderService;
import com.fgear.webapp.service.interf.RoleService;
import com.fgear.webapp.service.interf.UserService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final RoleService roleService;
    private final AuthenticationManager authenticationManager;

    @GetMapping("/getUser")
    public ResponseEntity<UserResponseByRoleUser> getListUserWithRoleUser(){
        List<UserResponse> userResponse = userService.getListUserWithRoleUser();
        UserResponseByRoleUser user = new UserResponseByRoleUser();
        if(userResponse.size() == 0 || userResponse.isEmpty()){
            user.setErrorMessage("do not have any user with role User");
            return ResponseEntity.ok().body(user);
        }
        user.setUsers(userResponse);
        return ResponseEntity.ok().body(user);
    }
    @PostMapping("/loginUser")
    public void loginUser(@RequestParam String username,
                          @RequestParam String password,
                          HttpServletResponse response,
                          HttpServletRequest request) throws IOException{
        try{
            Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String name = authentication.getName();
            User user = userService.findUserByID(name);
            Role role = roleService.findRoleName(user.getRoleID());
            String access_token = JWT.create()
                    .withSubject(user.getUserID())
                    .withExpiresAt(new Date(System.currentTimeMillis() + 10*60*1000))
                    .withIssuer(request.getRequestURL().toString())
                    .withClaim("roles",
                            Arrays.asList(role.getRoleName()))
                    .sign(algorithm);
            String refresh_token = JWT.create()
                    .withSubject(user.getUserID())
                    .withExpiresAt(new Date(System.currentTimeMillis() + 30*60*1000))
                    .withIssuer(request.getRequestURL().toString())
                    .withClaim("roles",
                            Arrays.asList(role.getRoleName()))
                    .sign(algorithm);
            Map<String, Object> tokens = new HashMap<>();
            UserResponse userRep = new UserResponse(user.getUserID(),user.getUserName(),user.getPhone(),user.getEmail(),user.getFacebook(),user.getImage(),user.getStatus(),user.getAddressID());
            tokens.put("access_token", access_token);
            tokens.put("refresh_token", refresh_token);
            tokens.put("user",userRep);
            response.setContentType("application/json");
            new ObjectMapper().writeValue(response.getOutputStream(), tokens);
        }catch (AuthenticationException e) {
            response.setHeader("error", "Invalid credentials");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            Map<String, String> error = new HashMap<>();
            error.put("error_message", "Your username or password is incorrect");
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(), error);
        }
    }
    @PostMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String username = decodedJWT.getSubject();
                User user = userService.findUserByID(username);
                Role role = roleService.findRoleName(user.getRoleID());
                String access_token = JWT.create()
                        .withSubject(user.getUserID())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles",
                                Arrays.asList(role.getRoleName()))
                        .sign(algorithm);
                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", access_token);
                tokens.put("refresh_token", refresh_token);
                response.setContentType("application/json");
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            } catch (Exception e) {
                response.setHeader("error", e.getMessage());
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                Map<String, String> error = new HashMap<>();
                error.put("error_message", e.getMessage());
                response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        } else {
            throw new RuntimeException("Refresh token is missing");
        }
    }
    @PostMapping ("/token/google")
    public void tokenGoogle(@RequestBody String accessToken ,HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            TokenRequest tokenRequest = mapper.readValue(accessToken, TokenRequest.class);
            String tokenValue = tokenRequest.getAccessToken();
            FirebaseAuth auth = FirebaseAuth.getInstance();
            FirebaseToken decodedToken = auth.verifyIdToken(tokenValue);
            String name = decodedToken.getEmail();
            Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
            User user = userService.findUserByEmail(name);
            Role role;
            if(user == null){
                String userID = decodedToken.getUid();
                String userName = decodedToken.getName();
                String image = decodedToken.getPicture();
                String email = decodedToken.getEmail();
                int status;
                if(decodedToken.isEmailVerified() == true){
                    status = 1;
                }else{
                    status = 0;
                }
                user = new User(userID, userName, "", email, "", "", image, 2, status,0);
                userService.createUserByNotFound(user);
                role = roleService.findRoleName(user.getRoleID());
            }else {
                role = roleService.findRoleName(user.getRoleID());
            }
            String access_token = JWT.create()
                    .withSubject(user.getUserID())
                    .withExpiresAt(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
                    .withIssuer(request.getRequestURL().toString())
                    .withClaim("roles",
                            Arrays.asList(role.getRoleName()))
                    .sign(algorithm);
            UserResponse userResponse = new UserResponse(user.getUserID(), user.getUserName(), user.getPhone(), user.getEmail(),user.getFacebook(),user.getImage(),user.getStatus(),user.getAddressID());
            Map<String, Object> tokens = new HashMap<>();
            tokens.put("access_token", access_token);
            tokens.put("User", userResponse);
            tokens.put("refresh_token", tokenValue);
            response.setContentType("application/json");
            new ObjectMapper().writeValue(response.getOutputStream(), tokens);
        } catch (Exception e) {
            response.setHeader("error", e.getMessage());
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            Map<String, String> error = new HashMap<>();
            error.put("error_message", e.getMessage());
            response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(), error);
        }
    }
}
