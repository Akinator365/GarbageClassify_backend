package cn.akinator.garbage.service;

import java.util.Map;

/**
 * Desc:    百度api 接口
 */
public interface BaiduService {

    Map<String,Object> imageClassify(String fileName);

    Map<String,Object> apiSpecch(String fileName);
}
