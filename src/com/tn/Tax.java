package com.tn;

public class Tax {
    public float tax(float n){
        float tong = 0;
        if(n<=5000000){
            tong = n*5/100;
        } else if (n>5000000&&n<=10000000) {
            tong = n*10/100-250000;
        }else if (n>10000000&&n<=18000000) {
            tong = n*15/100-750000;
        }else if (n>18000000&&n<=32000000) {
            tong = n*20/100-1650000;
        }else if (n>32000000&&n<=52000000) {
            tong = n*25/100-3250000;
        }else if (n>52000000&&n<=80000000) {
            tong = n*30/100-5850000;
        }else if (n>80000000) {
            tong = n*35/100-9850000;
        }
        return tong;
    }
}
