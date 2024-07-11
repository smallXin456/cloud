package com.atguigu.cloud.service;


import org.springframework.stereotype.Service;

import java.math.BigDecimal;

public interface AccountService {

    void decrease( Long productId, BigDecimal count);


}
