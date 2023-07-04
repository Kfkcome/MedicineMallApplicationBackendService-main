package pers.ervinse.service;

import pers.ervinse.domain.Commodity;

import java.util.List;

public interface CommodityService {

    List<Commodity> getAll();

    List<Commodity> getHotCommodity();

    Commodity getGoodInfo(Integer CommodityID);
}
