package peng.ziliang.soho.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import peng.ziliang.soho.common.constants.URLPrefix;
import peng.ziliang.soho.dto.AuthRequest;
import peng.ziliang.soho.model.User;
import peng.ziliang.soho.repository.UserRepo;
import peng.ziliang.soho.security.JwtTokenUtil;

import javax.validation.Valid;

@RestController
@RequestMapping(URLPrefix.API_PUBLIC)
public class DefaultController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserRepo userRepo;

    public DefaultController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, UserRepo userRepo) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userRepo = userRepo;
    }

    @GetMapping("/login")
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody @Valid AuthRequest request) {

        try {
            Authentication authenticate = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

            User user = (User) authenticate.getPrincipal();

            return ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION, jwtTokenUtil.generateAccessToken(user))
                    .body(user);
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
