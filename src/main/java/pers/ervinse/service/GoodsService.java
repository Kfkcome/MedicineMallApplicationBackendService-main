package pers.ervinse.service;

import pers.ervinse.domain.Commodity;

import java.util.List;

public interface GoodsService {

    List<Commodity> getAll();

    List<Commodity> getHotGoods();

    Commodity getGoodInfo(Integer CommodityID);
}
