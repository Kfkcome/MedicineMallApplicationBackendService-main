package pers.ervinse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.ervinse.domain.Commodity;
import pers.ervinse.mapper.CommodityMapper;
import pers.ervinse.service.CommodityService;

import java.util.List;

@Service
public class CommodityServiceImpl implements CommodityService {


    private final CommodityMapper commodityMapper;

    @Autowired
    public CommodityServiceImpl(CommodityMapper commodityMapper) {
        this.commodityMapper = commodityMapper;
    }

    /**
     * 得到所有药品
     *
     * @return {@link List}<{@link Commodity}>
     */
    @Override
    public List<Commodity> getAll() {
        return commodityMapper.selectList(null);
    }

    /**
     * 获取药品信息
     *
     * @return {@link List}<{@link Commodity}>
     */
    @Override
    public List<Commodity> getHotCommodity() {
        return commodityMapper.selectHotCommodity();
    }

    /**
     * 得到药品信息
     *
     * @param CommodityID 商品id
     * @return {@link Commodity}
     */
    @Override
    public Commodity getGoodInfo(Integer CommodityID) {
        return commodityMapper.selectByCommodityIDCommodity(CommodityID);
    }

}
