package cn.itcast.dtx.tccdemo.bank1.spring;

import org.dromara.hmily.annotation.Hmily;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(value="tcc-demo-bank2",fallback=Bank2ClientFallback.class)
public interface Bank2Client {
    //远程调用李四的微服务
    @GetMapping("/bank2/transfer")
    @Hmily//把张三微服务的全局事务的信息传到李四下游微服务里边
    public  Boolean transfer(@RequestParam("amount") Double amount);
}
