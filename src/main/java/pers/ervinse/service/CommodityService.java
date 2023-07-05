package pers.ervinse.service;

import pers.ervinse.domain.Commodity;
import pers.ervinse.domain.Photo;
import pers.ervinse.mapper.PhotoMapper;

import java.util.List;

public interface CommodityService {

    List<Commodity> getAll();

    List<Commodity> getHotCommodity();

    Commodity getCommodityInfo(Integer CommodityID);
    Photo getOneCommodityPhoto(Integer CommodityID);
    List<Photo> getAllCommodityPhoto(Integer CommodityID);
}
