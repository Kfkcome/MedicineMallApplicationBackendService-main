package pers.ervinse.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.ervinse.domain.Address;
import pers.ervinse.domain.Merchant;
import pers.ervinse.domain.Merchant_Address;
import pers.ervinse.mapper.AddressMapper;
import pers.ervinse.mapper.MerchantAddressMapper;
import pers.ervinse.mapper.MerchantMapper;
import pers.ervinse.service.MerchantService;

import java.util.List;

@Service
public class MerchantServiceImpl implements MerchantService {
    private final MerchantMapper merchantMapper;
    private final MerchantAddressMapper merchantAddressMapper;
    private final AddressMapper addressMapper;

    @Autowired
    public MerchantServiceImpl(MerchantMapper merchantMapper, MerchantAddressMapper merchantAddressMapper, AddressMapper addressMapper) {
        this.merchantMapper = merchantMapper;
        this.merchantAddressMapper = merchantAddressMapper;
        this.addressMapper = addressMapper;
    }

    @Override
    public Merchant getAllInfo(Integer MerchantID) {
        QueryWrapper<Merchant> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("MerchantID", MerchantID);
        return merchantMapper.selectOne(queryWrapper);
    }

    @Override
    public Address getAddress(Integer MerchantID) {
        QueryWrapper<Address> queryWrapper = new QueryWrapper<>();
        QueryWrapper<Merchant_Address> merchantAddressQueryWrapper = new QueryWrapper<>();
        merchantAddressQueryWrapper.eq("MerchantID", MerchantID);
        Merchant_Address merchantAddress = merchantAddressMapper.selectOne(merchantAddressQueryWrapper);
        if (merchantAddress == null) {
            return null;
        }
        queryWrapper.eq("AddressID", merchantAddress.getAddressID());
        return addressMapper.selectOne(queryWrapper);
    }
}
