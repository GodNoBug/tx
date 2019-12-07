package cn.itcast.dtx.notifydemo.bank1.spring;

import cn.itcast.dtx.notifydemo.bank1.entity.AccountPay;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 远程调用pay操作系统
 */
@FeignClient(value = "dtx-notifymsg-demo-pay", fallback = PayFallback.class)
public interface PayClient {
    @GetMapping("/pay/payresult/{txNo}")
    AccountPay queryPayResult(@PathVariable("txNo") String txNo);
}
