package pers.ervinse.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.ervinse.domain.Goods;
import pers.ervinse.service.GoodsService;
import pers.ervinse.utils.ApiResponse;

import java.util.List;

/**
 * 药品控制器
 *
 * @author kfk
 * @date 2023/07/04
 */
@Slf4j
@RestController
@RequestMapping("/goods")
public class GoodsController {


    private final GoodsService goodsService;

    @Autowired
    public GoodsController(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    /**
     * 得到所有
     *
     * @return {@link List}<{@link Goods}>
     */
    @GetMapping("/AllGoods")
    public ApiResponse<List<Goods>> getAll(){
        log.info("getAllGoods");
        return ApiResponse.success(goodsService.getAll());
    }


    /**
     * 获取热点商品
     *
     * @return {@link List}<{@link Goods}>
     */
    @GetMapping("/hotGoods")
    public ApiResponse<List<Goods>> getHotGoods(){
        log.info("getHotGoods");
        return ApiResponse.success(goodsService.getHotGoods());
    }

    /**
     * 获取详细商品信息
     *
     * @param CommodityID 商品id
     * @return {@link Goods}
     */
    @GetMapping("/GoodInfo/{CommodityID}")
    public ApiResponse<Goods> getGoodInfo(@PathVariable Integer CommodityID){
        return ApiResponse.success(goodsService.getGoodInfo(CommodityID));
    }
}
