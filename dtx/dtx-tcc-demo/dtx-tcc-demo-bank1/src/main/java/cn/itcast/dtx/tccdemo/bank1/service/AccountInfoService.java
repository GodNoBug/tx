package cn.itcast.dtx.tccdemo.bank1.service;

public interface AccountInfoService {
    // 张三扣款接口
    public void updateAccountBalance(String accountNo, Double amount);
}
