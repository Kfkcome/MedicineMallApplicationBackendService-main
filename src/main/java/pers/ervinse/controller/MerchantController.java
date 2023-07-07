package pers.ervinse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.ervinse.annotatian.LogPrint;
import pers.ervinse.domain.Address;
import pers.ervinse.domain.Merchant;
import pers.ervinse.service.MerchantService;
import pers.ervinse.utils.ApiResponse;

import javax.xml.stream.Location;

@RestController
@RequestMapping("/merchant")

public class MerchantController {
    private final MerchantService merchantService;

    @Autowired
    public MerchantController(MerchantService merchantService) {
        this.merchantService = merchantService;
    }

    @LogPrint
    @GetMapping("/info/{MerchantID}")
    ApiResponse<Merchant> getMerchantInfo(@PathVariable Integer MerchantID) {
        Merchant allInfo = merchantService.getAllInfo(MerchantID);
        if (allInfo == null) {
            return ApiResponse.fail(2009, "查询不到商家信息");
        }
        return ApiResponse.success(allInfo);
    }

    @GetMapping("/location/{MerchantID}")
    ApiResponse<Address> getMerchantAdress(@PathVariable Integer MerchantID) {
        Address address = merchantService.getAddress(MerchantID);
        if (address == null) {
            return ApiResponse.fail(2009, "查询不到商家信息");
        }
        return ApiResponse.success(address);
    }

}
