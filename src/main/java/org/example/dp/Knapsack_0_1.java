package org.example.dp;

import java.util.Arrays;

public class Knapsack_0_1 {

    public static void main(String[] args){

        int[] val = { 60, 100, 120 };
        int[] wt = { 10, 20, 30 };
        int W = 50;
         int[][] dp = new int[wt.length][W+1];

        for(int i =0;i<3;i++){
            for(int j=0;j<51;j++){
                dp[i][j] = -1;
            }
        }
        System.out.println(knapsack(wt,val,W, wt.length-1,dp));
        System.out.println(knapsack_top_down(wt,val,W,wt.length));

    }
    private static int knapsack(int[] wt, int[] val, int W, int index, int[][] dp){
        if(W ==0 || index ==0){
            dp[index][W] = 0;
            return 0;}
        if(dp[index][W]==0){
            return 0;
        }
        if(wt[index]<= W) {
            dp[index][W] = Math.max(val[index] + knapsack(wt, val, W - wt[index], index - 1,dp), knapsack(wt, val, W, index - 1,dp));
            return dp[index][W];
        }
        else if(wt[index]> W){
            dp[index][W] = knapsack(wt, val, W, index-1,dp);
            return dp[index][W];
        }
        return 0;
    }
    private static int knapsack_top_down(int[] wt, int[] val, int W, int length){
        //initialize
        int[][] dp = new int[length][W+1];
        for(int i=0;i<length;i++){
            for (int j =0;j<W+1;j++){
                if(i==0 || j==0){
                    dp[i][j]=0;
                }
            }
        }
        //logic
        for(int i=1;i<length;i++){
            for (int j =1;j<W+1;j++){
                if(wt[i]<=W && j-wt[i]>=0){
                    dp[i][j] = Math.max(val[i]+ dp[i-1][j-wt[i]], dp[i-1][j]);
                }
                else
                    dp[i][j] = dp[i-1][j];
            }
        }
        return dp[length-1][W];

    }
}
