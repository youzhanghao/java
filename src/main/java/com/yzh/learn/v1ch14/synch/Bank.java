package com.yzh.learn.v1ch14.synch;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author youzhanghao [m13732916591_1@163.com]
 * @Description:
 * @Date 2019/8/16
 */
public class Bank {


    private final double[] accounts;
    // add lock
    private Lock bandLock;
    private Condition sufficinetFunds;


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
        accounts = new double[n];
        Arrays.fill(accounts,initialBlance);
        bandLock = new ReentrantLock();
        sufficinetFunds = bandLock.newCondition();
    }

    /**
     * @param from
     * @param to
     * @param amount
     * @return void
     * @author youzhanghao
     * @date
     */
    public void transfer(int from,int to, double amount) throws InterruptedException {
        bandLock.lock();
        try{
            while (accounts[from] < amount)
                sufficinetFunds.await();// 将该线程放到条件的等待集中，单句写法，异常扔到方法外，解耦，利于外部捕捉
            System.out.println("执行线程"+Thread.currentThread());
            accounts[from] -= amount;
            System.out.printf(" %10.2f from %d to %d", amount, from, to);
            accounts[to] += amount;
            System.out.printf(" Total Balance: %10.2f%n",getTotalBalance());
            // 解除该条件的等待集中所有线程的阻塞状态，不使用signal因为随机选的一个线程，可能因为某种原因，
            // 该线程也无法运行，导致无线程通知
            sufficinetFunds.signalAll();

        }finally {
            bandLock.unlock();
        }

    }

    /**
     * @param
     * @return double total amount of balance
     * @author youzhanghao
     * @date
     */
    private double getTotalBalance() {
        bandLock.lock();
        try{
            double sum = 0;

            for (double a: accounts){
                sum += a;
            }
            return sum;
        }finally {
            bandLock.unlock();
        }

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
                    try {
                        bank.transfer(fromAccount,toAccount,amount);
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

