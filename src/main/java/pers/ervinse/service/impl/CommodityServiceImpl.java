package pers.ervinse.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.ervinse.domain.Commodity;
import pers.ervinse.domain.CommodityPhoto;
import pers.ervinse.domain.Photo;
import pers.ervinse.mapper.CommodityMapper;
import pers.ervinse.mapper.PhotoMapper;
import pers.ervinse.service.CommodityService;
import pers.ervinse.utils.PhotoUtils;

import java.sql.Wrapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

@Service
public class CommodityServiceImpl implements CommodityService {


    private final CommodityMapper commodityMapper;
    private final PhotoMapper photoMapper;

    @Autowired
    public CommodityServiceImpl(CommodityMapper commodityMapper, PhotoMapper photoMapper) {
        this.commodityMapper = commodityMapper;
        this.photoMapper = photoMapper;
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

    @Override
    public List<Commodity> getCommodityByType(int CommodityType) {
        QueryWrapper<Commodity> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("CommodityType",CommodityType);
        return  commodityMapper.selectList(queryWrapper);
    }

    @Override
    public List<Commodity> getCommodityByName(String CommodityName) {
        return commodityMapper.selectCommodityNameCommodities(CommodityName);
    }

    /**
     * 得到药品信息
     *
     * @param CommodityID 商品id
     * @return {@link Commodity}
     */
    @Override
    public Commodity getCommodityInfo(Integer CommodityID) {
        return commodityMapper.selectByCommodityIDCommodity(CommodityID);
    }

    @Override
    public Photo getOneCommodityPhoto(Integer CommodityID) {
        QueryWrapper<Photo> photoQueryWrapper = new QueryWrapper<>();
        CommodityPhoto commodityPhoto = photoMapper.selectOnePhotoByCommodityID(CommodityID);
        photoQueryWrapper.eq("PhotosID",commodityPhoto.getPhotosID());
        //photoQueryWrapper.eq("CommodityID",commodityPhoto.getCommodityID());
        Photo photo = photoMapper.selectOne(photoQueryWrapper);
        photo.setPhotoBytes(new String(PhotoUtils.convertPhotoToByte(photo.getPhotoAddress())));
        return photo;
    }

    @Override
    public List<Photo> getAllCommodityPhoto(Integer CommodityID) {
        List<Integer> photoIDs=new ArrayList<>();
        List<CommodityPhoto> commodityPhotos=photoMapper.selectAllPhotoByCommodityID(CommodityID);
        for (CommodityPhoto commodityPhoto : commodityPhotos) {
            photoIDs.add(commodityPhoto.getPhotosID());
        }
        List<Photo> photos = photoMapper.selectBatchIds(photoIDs);
        for (Photo photo : photos) {
            photo.setPhotoBytes(new String(PhotoUtils.convertPhotoToByte(photo.getPhotoAddress())));
        }
        return photos;
    }

}
