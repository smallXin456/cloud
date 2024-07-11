package com.atguigu.cloud.service.lmpl;

import com.atguigu.cloud.apis.AccountFeignApi;
import com.atguigu.cloud.apis.StorageFeignApi;
import com.atguigu.cloud.entities.Order;
import com.atguigu.cloud.mapper.OrderMapper;
import com.atguigu.cloud.service.OrderService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private AccountFeignApi accountFeignApi;

    @Resource
    private StorageFeignApi storageFeignApi;

    @Override
    @GlobalTransactional(name = "zzzz-create-order",rollbackFor = Exception.class)
    public void create(Order order) {

        //xid全局事务id的检测
        String xid = RootContext.getXID();
        System.out.println("新建订单xid:"+xid);
        order.setStatus(0);

        int i = orderMapper.insertSelective(order);

        Order orderFromDB = null;
        if(i>0){
            orderFromDB=orderMapper.selectOne(order);


            //扣减库存
            storageFeignApi.decrease(orderFromDB.getProductId(),orderFromDB.getCount());
            System.out.println("减库存结束");

            //扣减账户
            accountFeignApi.decrease(orderFromDB.getUserId(),orderFromDB.getMoney());
            System.out.println("减账户结束");


            orderFromDB.setStatus(1);

            Example example = new Example(Order.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("userId",orderFromDB.getUserId());
            criteria.andEqualTo("status",0);

            int i1 = orderMapper.updateByExampleSelective(orderFromDB, example);

            System.out.println("订单修改完成");
        }
        System.out.println("结束新建订单xid:"+xid);
    }
}
