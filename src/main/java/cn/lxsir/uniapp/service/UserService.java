package cn.lxsir.uniapp.service;

import cn.lxsir.uniapp.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author luoxiang
 * @since 2019-07-13
 */
public interface UserService extends IService<User> {

    Map<String, Object> getOpenid(Map<String,Object> codeMap);

    Map<String, Object> getOpenidtest(Map<String,Object> codeMap);

    void setUserProfile(Map<String,Object> profile);

    Map<String, Object> getUserProfile(Map<String, Object> openidMap);

}
