package com.yzh.learn.v1ch14.unsynch;

import java.util.Arrays;

/**
 * @Author youzhanghao [m13732916591_1@163.com]
 * @Description:
 * @Date 2019/8/16
 */
public class Bank {

    private final double[] accounts;

    public static final int MAX_COUNTS = 100;
    public static final double INITAL_BALANCE = 1000;
    public static final double MAX_AMOUNT = 1000;
    public static final int DELAY = 10;



    /**
     * @param n
     * @param initialBlance
     * @return
     * @author youzhanghao
     * @date
     */
    public Bank(int n, double initialBlance)
    {
        // 定义了初始化数组
        accounts = new double[n];
        Arrays.fill(accounts,initialBlance);
    }

    /**
     * @param from
     * @param to
     * @param amount
     * @return void
     * @author youzhanghao
     * @date
     */
    public void transfer(int from,int to, double amount)
    {
        if (accounts[from] < amount) return;
        System.out.println("执行线程"+Thread.currentThread());
        accounts[from] -= amount; // 该步非原子性操作 被其他线程抢夺了
        System.out.printf(" %10.2f from %d to %d", amount, from, to);
        accounts[to] += amount;
        System.out.printf(" Total Balance: %10.2f%n",getTotalBalance());

    }

    /**
     * @param
     * @return double total amount of balance
     * @author youzhanghao
     * @date
     */
    private double getTotalBalance() {
        double sum = 0;

        for (double a: accounts){
            sum += a;
        }
        return sum;
    }

    /**
     * @param
     * @return int
     * @author youzhanghao
     * @date
     */
    public int size(){
        return accounts.length;
    }

    public static void main(String[] args) {

        // 1.创建账号100个，每个账号初始资金1000
        Bank bank = new Bank(MAX_COUNTS,INITAL_BALANCE);
        // 2.转账100次 每隔10s转账一次
        for(int i = 0; i < MAX_COUNTS; i++){
            int fromAccount = i;
            Runnable r = () ->{
                while (true){
                    int toAccount = (int) (bank.size() * Math.random());
                    double amount = MAX_AMOUNT * Math.random();
                    System.out.printf("当前%d线程%s", fromAccount,Thread.currentThread());
                    bank.transfer(fromAccount,toAccount,amount);
                    try {
                        Thread.sleep((int)(DELAY*Math.random()));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            Thread thread = new Thread(r);
            thread.start();

        }
    }
}

