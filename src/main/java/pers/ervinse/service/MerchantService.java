package pers.ervinse.service;

import pers.ervinse.domain.Address;
import pers.ervinse.domain.Merchant;

import java.util.List;

public interface MerchantService {
    Merchant getAllInfo(Integer MerchantID);

    Address getAddress(Integer MerchantID);
}
