package cn.lxsir.uniapp.controller;


import cn.lxsir.uniapp.entity.ChallengeResult;
import cn.lxsir.uniapp.entity.ImageClassify;
import cn.lxsir.uniapp.entity.KeywordResult;
import cn.lxsir.uniapp.service.ChallengeResultService;
import cn.lxsir.uniapp.service.ImageClassifyService;
import cn.lxsir.uniapp.service.KeywordResultService;
import com.baomidou.mybatisplus.extension.api.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 历史记录查询 前端控制器
 * </p>
 *
 * @author luoxiang
 * @since 2019-07-07
 */
@RestController
@RequestMapping("/history")
@Api(value = "历史 Controller",tags = {"查询历史记录"})
public class HistoryController {

    @Autowired
    KeywordResultService keywordResultService;

    @Autowired
    ChallengeResultService challengeResultService;

    @Autowired
    ImageClassifyService imageClassifyService;

    @PostMapping("/keyword")
    @ApiOperation(value = "返回关键词查询历史")
    public R keywordHistory(@RequestBody Map<String,Object> useridMap){
        List<KeywordResult> list = keywordResultService.keywordHistory(useridMap);
        return R.ok(list);
    }

    @PostMapping("/challenge")
    @ApiOperation(value = "返回挑战赛历史")
    public R challengeHistory(@RequestBody Map<String,Object> useridMap){
        List<ChallengeResult> list = challengeResultService.challengeHistory(useridMap);
        return R.ok(list);
    }

    @PostMapping("/image")
    @ApiOperation(value = "返回图片查询历史")
    public R imageHistory(@RequestBody Map<String,Object> useridMap){
        List<ImageClassify> list = imageClassifyService.imageHistory(useridMap);
        return R.ok(list);
    }

}

