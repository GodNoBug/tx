package cn.itcast.dtx.seatademo.bank1.service;

public interface AccountInfoService {
    // 张三扣减金额
    public void updateAccountBalance(String accountNo, Double amount);
}
