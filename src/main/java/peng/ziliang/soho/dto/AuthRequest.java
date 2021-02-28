package peng.ziliang.soho.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@ApiModel(description = "登陆请求")
@Data
public class AuthRequest {

    @NotNull
    @Email
    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("密码")
    @NotNull
    private String password;

}
