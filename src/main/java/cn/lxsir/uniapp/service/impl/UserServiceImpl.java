package cn.lxsir.uniapp.service.impl;

import cn.lxsir.uniapp.entity.SlideShow;
import cn.lxsir.uniapp.entity.User;
import cn.lxsir.uniapp.mapper.UserMapper;
import cn.lxsir.uniapp.service.UserService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author luoxiang
 * @since 2019-07-13
 */
@Service
@Log4j2
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    RestTemplate restTemplate;

    @Value("${weixin.appid}")
    String appid;
    @Value("${weixin.secret}")
    String secret;

    @Override
    public Map<String, Object> getOpenid(Map<String,Object> codeMap)
    {
        System.out.println("========== 进入wxLogin/getOpenid方法  ==========");
        System.err.println("微信授权登录");
        String code = (String) codeMap.get("code");
        Map<String, Object> resultMap = new HashMap<>();

        // 拼接sql
        String loginUrl = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appid +
                "&secret=" + secret + "&js_code=" + code + "&grant_type=authorization_code";

        resultMap.put("code", code);

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(loginUrl, String.class,resultMap);

        JSONObject jsonObject=JSONObject.parseObject(responseEntity.getBody());
        String openid = jsonObject.getString("openid");

        System.out.println("openid:" + openid);

        String session_key = jsonObject.getString("session_key");

        System.out.println("微信返回的结果" + responseEntity.getBody());

        User user = getOne(new QueryWrapper<User>().eq("openid", openid));

        //System.err.println("用户信息：" + users);

        if (StringUtils.isEmpty(openid))
        {
            resultMap.put("state",500);
            resultMap.put("message", "未获取到openid");
            return resultMap;
        }
        else
        {
            // 判断是否为首次登陆
            if (user == null)
            {
                resultMap.put("state", 200);
                resultMap.put("openid", openid);
                resultMap.put("sessionKey", session_key);
                resultMap.put("message", "未查询到用户信息");
                User newUser = new User();
                newUser.setOpenid(openid);
                newUser.setSessionKey(session_key);
                save(newUser);

                user = getOne(new QueryWrapper<User>().eq("openid", openid));
                resultMap.put("userid",user.getUserId());
            }
            else
            {
                resultMap.put("state", 300);
                // 前台拿的数据是map的key
                resultMap.put("openid", openid);
                resultMap.put("sessionKey", session_key);
                resultMap.put("message", "该用户已经存在");
                resultMap.put("userid",user.getUserId());

            }
        }
        System.out.println("map: " + resultMap);

        return resultMap;
    }

    @Override
    public Map<String, Object> getOpenidtest(Map<String,Object> codeMap)
    {

        System.out.println("========== 进入wxLogin/getOpenid方法  ==========");
        System.err.println("微信授权登录");
        String code = (String) codeMap.get("code");
        Map<String, Object> resultMap = new HashMap<>();

        // 拼接sql
        String loginUrl = "https://api.weixin.qq.com/sns/jscode2session?appid=" + "wxbd652bb38ac2fc27" +
            "&secret=" + "774fd73979ac685062d55edd95d84545" + "&js_code=" + code + "&grant_type=authorization_code";
        //String loginUrl = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appid +
        //        "&secret=" + secret + "&js_code=" + code + "&grant_type=authorization_code";

        resultMap.put("code", code);

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(loginUrl, String.class,resultMap);

        JSONObject jsonObject=JSONObject.parseObject(responseEntity.getBody());
        String openid = jsonObject.getString("openid");

        System.out.println("openid:" + openid);

        String session_key = jsonObject.getString("session_key");

        System.out.println("微信返回的结果" + responseEntity.getBody());

        User user = getOne(new QueryWrapper<User>().eq("openid", openid));

        //System.err.println("用户信息：" + users);

        if (StringUtils.isEmpty(openid))
        {
            resultMap.put("state",500);
            resultMap.put("message", "未获取到openid");
            return resultMap;
        }
        else
        {
            // 判断是否为首次登陆
            if (user == null)
            {
                resultMap.put("state", 200);
                resultMap.put("openid", openid);
                resultMap.put("sessionKey", session_key);
                resultMap.put("message", "未查询到用户信息");
                User newUser = new User();
                newUser.setOpenid(openid);
                newUser.setSessionKey(session_key);
                save(newUser);

                user = getOne(new QueryWrapper<User>().eq("openid", openid));
                resultMap.put("userid",user.getUserId());
            }
            else
            {
                resultMap.put("state", 300);
                // 前台拿的数据是map的key
                resultMap.put("openid", openid);
                resultMap.put("sessionKey", session_key);
                resultMap.put("message", "该用户已经存在");
                resultMap.put("userid",user.getUserId());
            }
        }
        System.out.println("map: " + resultMap);

        return resultMap;
    }

    @Override
    public void setUserProfile(Map<String, Object> profile) {
        String nickName = (String) profile.get("nickName");
        String avatarUrl = (String) profile.get("avatarUrl");
        String openid = (String) profile.get("openid");

        User user = new User();
        user.setNickName(nickName);
        user.setAvatarUrl(avatarUrl);
        update(user,new QueryWrapper<User>().eq("openid", openid));
    }

    @Override
    public Map<String, Object> getUserProfile(Map<String, Object> openidMap) {

        String openid = (String) openidMap.get("openid");
        User user = getOne(new QueryWrapper<User>().eq("openid", openid));
        Map<String, Object> resultMap = new HashMap<>();

        if (StringUtils.isEmpty(openid)) {
            resultMap.put("state",500);
            resultMap.put("message", "未获取到openid");
            return resultMap;
        } else {
            // 判断是否为首次登陆
            if (user == null) {
                resultMap.put("state",501);
                resultMap.put("message", "未找到用户");
                return resultMap;
            } else {
                resultMap.put("state", 200);
                // 前台拿的数据是map的key
                resultMap.put("userid",user.getUserId());
                resultMap.put("openid", openid);
                resultMap.put("nickName", user.getNickName());
                resultMap.put("avatarUrl", user.getAvatarUrl());
                resultMap.put("message", "成功返回用户信息");
            }

        }
        System.out.println("map: " + resultMap);

        return resultMap;

    }
}
