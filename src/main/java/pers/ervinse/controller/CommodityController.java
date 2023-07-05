package pers.ervinse.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.ervinse.domain.Commodity;
import pers.ervinse.domain.Photo;
import pers.ervinse.service.CommodityService;
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
@RequestMapping("/medicines")
public class CommodityController {


    private final CommodityService commodityService;

    @Autowired
    public CommodityController(CommodityService commodityService) {
        this.commodityService = commodityService;
    }

    /**
     * 得到所有
     *
     * @return {@link List}<{@link Commodity}>
     */
    @GetMapping("/AllMedicine")
    public ApiResponse<List<Commodity>> getAll() {
        log.info("getAllCommodity");
        return ApiResponse.success(commodityService.getAll());
    }


    /**
     * 获取热点商品
     *
     * @return {@link List}<{@link Commodity}>
     */
    @GetMapping("/hotMedicine")
    public ApiResponse<List<Commodity>> getHotCommodity() {
        log.info("getHotMedicine");
        return ApiResponse.success(commodityService.getHotCommodity());
    }

    /**
     * 获得商品信息
     * 获取详细商品信息
     *
     * @param CommodityID 商品id
     * @return {@link ApiResponse}<{@link Commodity}>
     */
    @GetMapping("/MedicineInfo/{CommodityID}")
    public ApiResponse<Commodity> getCommodityInfo(@PathVariable Integer CommodityID) {
        return ApiResponse.success(commodityService.getCommodityInfo(CommodityID));
    }

    /**
     * 获得商品照片
     *
     * @param CommodityID 商品id
     * @return {@link ApiResponse}<{@link Photo}>
     */
    @GetMapping("/MedicinePicture/{CommodityID}")
    public ApiResponse<Photo> getCommodityPhoto(@PathVariable Integer CommodityID){
        return ApiResponse.success(commodityService.getOneCommodityPhoto(CommodityID));
    }

    /**
     * 把所有商品照片
     *
     * @param CommodityID 商品id
     * @return {@link ApiResponse}<{@link List}<{@link Photo}>>
     */
    @GetMapping("AllMedicinePicture/{CommodityID}")
    public ApiResponse<List<Photo>> getAllCommodityPhoto(@PathVariable Integer CommodityID){
        return ApiResponse.success(commodityService.getAllCommodityPhoto(CommodityID));
    }
}
