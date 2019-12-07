package cn.itcast.dtx.tccdemo.bank2.service;

public interface AccountInfoService {
    // 张三扣款接口
    public void updateAccountBalance(String accountNo, Double amount);
}
