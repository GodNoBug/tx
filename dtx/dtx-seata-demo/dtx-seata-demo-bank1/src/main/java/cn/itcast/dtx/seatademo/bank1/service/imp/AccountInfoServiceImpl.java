package cn.itcast.dtx.seatademo.bank1.service.imp;

import cn.itcast.dtx.seatademo.bank1.dao.AccountInfoDao;
import cn.itcast.dtx.seatademo.bank1.service.AccountInfoService;
import cn.itcast.dtx.seatademo.bank1.spring.Bank2Client;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class AccountInfoServiceImpl implements AccountInfoService {
    @Autowired
    private AccountInfoDao accountInfoDao;
    @Autowired
    private Bank2Client bank2Client;
    @Transactional
    @GlobalTransactional//开启全局事务
    @Override
    public void updateAccountBalance(String accountNo, Double amount) {
        log.info("bank1 service begin, XID:{}", RootContext.getXID());
        // 扣减张三金额
        int i = accountInfoDao.updateAccountBalance(accountNo, amount*-1);
        // 调用李四的微服务,转账
        String transfer = bank2Client.transfer(amount);
        if ("fallback".equals(transfer)){
            //调用微服务李四异常，seata会感知
            throw new RuntimeException("调用微服务李四异常");
        }
        if (amount == 2){
            throw new RuntimeException("bank1 make exception 1");
        }
    }
}
