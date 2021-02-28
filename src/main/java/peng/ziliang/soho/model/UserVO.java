package peng.ziliang.soho.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;


@Data
public class UserVO {

    @Id
    private Long tid;
    private String fullName;
    private String nickName;
    private String loginName;
    private Character gender;
    private String email;

}
