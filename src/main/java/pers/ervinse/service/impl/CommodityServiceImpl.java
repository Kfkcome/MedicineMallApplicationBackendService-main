package pers.ervinse.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.ervinse.domain.Commodity;
import pers.ervinse.domain.CommodityPhoto;
import pers.ervinse.domain.Photo;
import pers.ervinse.domain.Review;
import pers.ervinse.mapper.CommodityMapper;
import pers.ervinse.mapper.PhotoMapper;
import pers.ervinse.mapper.ReviewMapper;
import pers.ervinse.service.CommodityService;
import pers.ervinse.utils.PhotoUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommodityServiceImpl implements CommodityService {


    private final CommodityMapper commodityMapper;
    private final PhotoMapper photoMapper;
    private final ReviewMapper reviewMapper;

    @Autowired
    public CommodityServiceImpl(CommodityMapper commodityMapper, PhotoMapper photoMapper, ReviewMapper reviewMapper) {
        this.commodityMapper = commodityMapper;
        this.photoMapper = photoMapper;
        this.reviewMapper = reviewMapper;
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
        QueryWrapper<Commodity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("CommodityType", CommodityType);
        return commodityMapper.selectList(queryWrapper);
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
        photoQueryWrapper.eq("PhotosID", commodityPhoto.getPhotosID());
        //photoQueryWrapper.eq("CommodityID",commodityPhoto.getCommodityID());
        Photo photo = photoMapper.selectOne(photoQueryWrapper);
        photo.setPhotoBytes(new String(PhotoUtils.convertPhotoToByte(photo.getPhotoAddress())));
        return photo;
    }

    @Override
    public List<Photo> getAllCommodityPhoto(Integer CommodityID) {
        List<Integer> photoIDs = new ArrayList<>();
        List<CommodityPhoto> commodityPhotos = photoMapper.selectAllPhotoByCommodityID(CommodityID);
        for (CommodityPhoto commodityPhoto : commodityPhotos) {
            photoIDs.add(commodityPhoto.getPhotosID());
        }
        List<Photo> photos = photoMapper.selectBatchIds(photoIDs);
        for (Photo photo : photos) {
            photo.setPhotoBytes(new String(PhotoUtils.convertPhotoToByte(photo.getPhotoAddress())));
        }
        return photos;
    }

    @Override
    public List<Review> getCommodityReview(Integer CommodityID) {
        QueryWrapper<Review> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("CommodityID", CommodityID);
        return reviewMapper.selectList(queryWrapper);
    }

}
