package cn.lxsir.uniapp.service.impl;

import cn.lxsir.uniapp.entity.*;
import cn.lxsir.uniapp.mapper.ImageClassifyMapper;
import cn.lxsir.uniapp.service.ImageClassifyService;
import cn.lxsir.uniapp.service.KeywordResultService;
import cn.lxsir.uniapp.service.KeywordSearchNumService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.Maps;
import lombok.extern.log4j.Log4j2;
import org.json.JSONArray;
import org.json.JSONObject;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.Struct;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

/**
 * <p>
 * 图像识别记录 服务实现类
 * </p>
 *
 * @author luoxiang
 * @since 2019-07-13
 */
@Service
@Log4j2
public class ImageClassifyServiceImpl extends ServiceImpl<ImageClassifyMapper, ImageClassify> implements ImageClassifyService {

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    KeywordResultService krService;
    @Autowired
    KeywordSearchNumService ksnService;

    @Async
    @Override
    public void imageHandle(String filename, JSONObject res, JSONObject resultVo, List<QuestionBank> questionBanks, QuestionBank questionBankOne) {

        log.error("应该为 3 ");
        final JSONArray jsonArray = res.getJSONArray("result");
        StringBuilder allKeyword=new StringBuilder();
        for (int i=0;i<jsonArray.length();i++) {
            allKeyword.append(jsonArray.getJSONObject(i).getString("keyword")+",");
        }
        ImageClassify imageClassify = ImageClassify.builder().oneKeyword(resultVo.getString("keyword"))
                .oneResult(JSON.toJSONString(questionBankOne))
                .allKeyword(allKeyword.toString())
                .allResult(JSON.toJSONString(questionBanks))
                .userName("张三")
                .filepath(filename).build();
        this.save(imageClassify);

        String name = resultVo.getString("keyword");
        boolean keywordResultsSave = krService.save(KeywordResult.builder().keyword(name).result(JSON.toJSONString(questionBanks)).build());
        KeywordSearchNum keywordNum = ksnService.getOne(new QueryWrapper<KeywordSearchNum>().eq("keyword", name));
        if(StringUtils.isEmpty(keywordNum)){
            keywordNum=KeywordSearchNum.builder().keyword(name).num(1).build();
        }else{
            keywordNum.setNum(keywordNum.getNum()+1);
        }
        ksnService.saveOrUpdate(keywordNum);

    }

    @Override
    public Map<String, Object> imageMatch(String fileName, String userid) {

        System.out.println("-----------------------filename:"+fileName);
        String url = "http://ceshi.mua5201314.com/api/checkPic";
        String base64 = ImageToBase64(fileName);
        base64 = "data:image/jpg;base64,"+base64;

        Map body = new HashMap();
        body.put("image",base64);
        com.alibaba.fastjson.JSONObject jsonObject = restTemplate.postForObject(url, JSON.toJSON(body),com.alibaba.fastjson.JSONObject.class);

        String classify = jsonObject.get("content").toString();
        String score = jsonObject.get("score").toString();
        System.out.println(classify);

        String type = classify.substring(0,classify.indexOf("/"));
        String name = classify.substring(classify.indexOf("/")+1);

        ImageClassify imageClassify = ImageClassify.builder().oneKeyword(name)
                .userid(userid)
                .type(type)
                .score(score)
                .filepath(fileName).build();
        this.save(imageClassify);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", name);
        resultMap.put("type", type);
        resultMap.put("score", score);
        System.out.println(resultMap);
        return resultMap;
    }

    @Override
    public String handleUploadFile(MultipartFile file, String path) {
        String fileName = file.getOriginalFilename();
        System.out.println("filename:" + fileName);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        File filePathIF = new File(path, String.valueOf(now.getYear()));
        if (!filePathIF.exists()) {
            filePathIF.mkdir();
            filePathIF = new File(filePathIF.getAbsolutePath(), String.valueOf(now.getMonthValue()));
            if (!filePathIF.exists()) {
                filePathIF.mkdir();
                filePathIF = new File(filePathIF.getAbsolutePath(), String.valueOf(now.getDayOfMonth()));
                if (!filePathIF.exists()) {
                    filePathIF.mkdir();
                }
            }
        }
        String[] split = fileName.split("\\.");
        String newFileName = UUID.randomUUID().toString().toLowerCase() + "." + split[split.length - 1];
        File dest = new File(filePathIF, newFileName);
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            log.error("文件处理失败：" + e.getMessage());
            throw new RuntimeException("文件处理失败");
        }
        return dest.getAbsolutePath();
    }

    @Override
    public List<ImageClassify> imageHistory(Map<String, Object> useridMap) {

        Integer userid = ( Integer) useridMap.get("userid");

        QueryWrapper<ImageClassify> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("times");
        queryWrapper.eq("userid", userid);

        List<ImageClassify> resultList = list(queryWrapper);

        System.out.println(resultList);

        return resultList;
    }

    public static byte[] readImage(String imgPath) {
        byte[] data = null;
        InputStream in = null;

        // 读取图片字节数组
        try {
            in = new FileInputStream(imgPath);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return data;
    }

    public static void saveImage(byte[] imageByte){
        InputStream input = null;

        try {
            //转化成流
            input = new ByteArrayInputStream(imageByte);
            BufferedImage bi = ImageIO.read(input);
            File file = new File("temp.png");
            ImageIO.write(bi, "png", file);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String ImageToBase64(String imgPath) {
        byte[] data = readImage(imgPath);

        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();

        // 返回Base64编码过的字节数组字符串
        return encoder.encode(Objects.requireNonNull(data));
    }
}
