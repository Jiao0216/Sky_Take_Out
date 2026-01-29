package com.sky.controller.user;


import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController("userShopController")
@Api(tags = "用户相关接口")
@RequestMapping("/user/shop")
@Slf4j
public class ShopStatusController {

    private static final String KEY = "SHOP_STATUS";

    @Autowired
    private final RedisTemplate<Object, Object> redisTemplate;

    public ShopStatusController(RedisTemplate<Object, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    @GetMapping("/status")
    @ApiOperation("获取营业状态")
    public Result<Integer> getStatus() {

       Integer status = (Integer)redisTemplate.opsForValue().get(KEY);
       log.info("获取店铺状态为：{}",status==1? "营业中":"打烊中");
        return Result.success(status);
    }
}
