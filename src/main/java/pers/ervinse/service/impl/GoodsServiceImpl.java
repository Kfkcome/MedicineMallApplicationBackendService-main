package pers.ervinse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.ervinse.domain.Commodity;
import pers.ervinse.mapper.GoodsMapper;
import pers.ervinse.service.GoodsService;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {


    private final GoodsMapper goodsMapper;

    @Autowired
    public GoodsServiceImpl(GoodsMapper goodsMapper) {
        this.goodsMapper = goodsMapper;
    }

    /**
     * 得到所有药品
     *
     * @return {@link List}<{@link Goods}>
     */
    @Override
    public List<Commodity> getAll() {
        return goodsMapper.selectList(null);
    }

    /**
     * 获取药品信息
     *
     * @return {@link List}<{@link Goods}>
     */
    @Override
    public List<Commodity> getHotGoods() {
        return goodsMapper.selectHotGoods();
    }

    /**
     * 得到药品信息
     *
     * @param CommodityID 商品id
     * @return {@link Goods}
     */
    @Override
    public Commodity getGoodInfo(Integer CommodityID) {
        return goodsMapper.selectByCommodityIDGoods(CommodityID);
    }

}
