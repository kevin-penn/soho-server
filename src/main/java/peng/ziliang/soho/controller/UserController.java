package peng.ziliang.soho.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.mapstruct.factory.Mappers;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import peng.ziliang.soho.common.constants.URLPrefix;
import peng.ziliang.soho.model.User;
import peng.ziliang.soho.model.UserMapper;
import peng.ziliang.soho.model.UserVO;
import peng.ziliang.soho.repository.UserRepo;
import peng.ziliang.soho.security.JwtTokenUtil;

@Api(tags = "用户管理")
@RestController
@RequestMapping(URLPrefix.API_PRIV_USER)
public class UserController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserRepo userRepo;

    public UserController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, UserRepo userRepo) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userRepo = userRepo;
    }

    @ApiOperation(value = "用户基本信息")
    @GetMapping("/info")
    public ResponseEntity<UserVO> info() {

        User user = userRepo.queryCustom().get();

        return ResponseEntity.ok().body(UserMapper.INSTANCE.userToUserVO(user));
    }
}
