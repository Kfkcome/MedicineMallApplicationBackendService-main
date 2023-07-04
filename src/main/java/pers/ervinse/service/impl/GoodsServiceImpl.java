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

    @Override
    public List<Commodity> getAll() {
        return goodsMapper.selectList(null);
    }

    @Override
    public List<Commodity> getHotGoods() {
        return goodsMapper.selectHotGoods();
    }

    @Override
    public Commodity getGoodInfo(Integer CommodityID) {
        return goodsMapper.selectByCommodityIDGoods(CommodityID);
    }

}
