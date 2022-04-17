package cn.akinator.garbage.controller;



import cn.akinator.garbage.service.UserService;
import com.baomidou.mybatisplus.extension.api.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

/**
 * <p>
 * 用户身份
 * </p>
 */
@RestController
@RequestMapping("/authorize")
@Api(value = "用户身份 Controller",tags = {"openid 接口"})
public class AuthorizeController {

    @Autowired
    UserService userService;

    @PostMapping("/getOpenid")
    @ApiOperation(value = " 验证用户登录状态")
    public R getOpenid(@RequestBody Map<String,Object> codeMap){

        Map<String, Object> map = userService.getOpenid(codeMap);
        return R.ok(map);
    }

    @PostMapping("/getOpenidtest")
    @ApiOperation(value = " 验证用户登录状态test")
    public R getOpenidtest(@RequestBody Map<String,Object> codeMap){

        Map<String, Object> map = userService.getOpenidtest(codeMap);
        return R.ok(map);
    }

    @PostMapping("/setUserProfile")
    @ApiOperation(value = "设置用户信息")
    public R setUserProfile(@RequestBody Map<String,Object> profile){

        userService.setUserProfile(profile);
        return R.ok(profile);
    }

    @PostMapping("/getUserProfile")
    @ApiOperation(value = "客户端获取用户信息")
    public R getUserProfile(@RequestBody Map<String,Object> openidMap){

        Map<String, Object> map = userService.getUserProfile(openidMap);
        return R.ok(map);
    }

}

