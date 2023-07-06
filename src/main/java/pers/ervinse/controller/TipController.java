package pers.ervinse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.ervinse.domain.Tip;
import pers.ervinse.service.TipService;
import pers.ervinse.utils.ApiResponse;

@RestController
@RequestMapping("/tip")
public class TipController {
    @Autowired
    TipService tipService;

    @GetMapping("/random")
    ApiResponse<Tip> getRandomTip() {
        Tip randomTip = tipService.getRandomTip();
        if (randomTip == null) {
            return ApiResponse.fail(2070, "查询不到健康知识");
        }
        return ApiResponse.success(randomTip);
    }
}
