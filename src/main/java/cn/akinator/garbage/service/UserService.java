package cn.akinator.garbage.service;

import cn.akinator.garbage.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 用户表 服务类
 * </p>
 */
public interface UserService extends IService<User> {

    Map<String, Object> getOpenid(Map<String,Object> codeMap);

    Map<String, Object> getOpenidtest(Map<String,Object> codeMap);

    void setUserProfile(Map<String,Object> profile);

    Map<String, Object> getUserProfile(Map<String, Object> openidMap);

}
