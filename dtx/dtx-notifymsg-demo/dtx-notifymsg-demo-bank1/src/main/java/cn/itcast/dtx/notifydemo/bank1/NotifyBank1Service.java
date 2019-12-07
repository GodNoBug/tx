package cn.itcast.dtx.notifydemo.bank1;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 账户系统:接收通知，更改账户金额
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrix
@EnableFeignClients(basePackages = {"cn.itcast.dtx.notifydemo.bank1.spring"})
public class NotifyBank1Service {

	public static void main(String[] args) {
		SpringApplication.run(NotifyBank1Service.class, args);
	}

}
