package pers.ervinse.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.ervinse.annotatian.LogPrint;
import pers.ervinse.domain.Commodity;
import pers.ervinse.domain.Photo;
import pers.ervinse.domain.Review;
import pers.ervinse.enums.ResponseCode;
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
    @LogPrint
    @GetMapping("/MedicinePicture/{CommodityID}")
    public ApiResponse<Photo> getCommodityPhoto(@PathVariable Integer CommodityID) {
        return ApiResponse.success(commodityService.getOneCommodityPhoto(CommodityID));
    }

    /**
     * 把所有商品照片
     *
     * @param CommodityID 商品id
     * @return {@link ApiResponse}<{@link List}<{@link Photo}>>
     */
    @GetMapping("AllMedicinePicture/{CommodityID}")
    public ApiResponse<List<Photo>> getAllCommodityPhoto(@PathVariable Integer CommodityID) {
        return ApiResponse.success(commodityService.getAllCommodityPhoto(CommodityID));
    }

    /**
     * 被类型商品
     * 根据类型查询商品
     *
     * @param CommodityType 商品类型
     * @return {@link ApiResponse}<{@link List}<{@link Commodity}>>
     */
    @GetMapping("type/{CommodityType}")
    public ApiResponse<List<Commodity>> getCommodityByType(@PathVariable Integer CommodityType) {
        return ApiResponse.success(commodityService.getCommodityByType(CommodityType));
    }

    /**
     * 获得商品名字
     *
     * @param CommodityName 商品名称
     * @return {@link ApiResponse}<{@link List}<{@link Commodity}>>
     */
    @GetMapping("name")
    public ApiResponse<List<Commodity>> getCommodityByName(String CommodityName) {
        return ApiResponse.success(commodityService.getCommodityByName(CommodityName));
    }

    /**
     * 获得商品评论
     *
     * @param CommodityID 商品id
     * @return {@link ApiResponse}<{@link List}<{@link Review}>>
     */
    @GetMapping("review/{CommodityID}")
    public ApiResponse<List<Review>> getCommodityReview(@PathVariable Integer CommodityID) {
        List<Review> commodityReview = commodityService.getCommodityReview(CommodityID);
        if (commodityReview == null || commodityReview.isEmpty()) {
            return ApiResponse.fail(ResponseCode.REVIEW_NOT_EXIT);
        }
        return ApiResponse.success(commodityReview);
    }


}
