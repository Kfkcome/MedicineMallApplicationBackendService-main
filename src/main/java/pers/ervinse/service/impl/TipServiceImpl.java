package pers.ervinse.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.ervinse.domain.Tip;
import pers.ervinse.mapper.TipMapper;
import pers.ervinse.service.TipService;

import java.util.Random;

@Service
public class TipServiceImpl implements TipService {
    private final TipMapper tipMapper;

    @Autowired
    public TipServiceImpl(TipMapper tipMapper) {
        this.tipMapper = tipMapper;
    }

    @Override
    public Tip getRandomTip() {
        Random random = new Random();
        int i = random.nextInt(9);
        QueryWrapper<Tip> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("TipID", i);
        return tipMapper.selectOne(queryWrapper);
    }
}
