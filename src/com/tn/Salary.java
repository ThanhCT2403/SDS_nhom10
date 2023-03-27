package com.tn;

public class Salary {
    double p;
    public double salary(EnumPos enumPos){
        switch (enumPos){
            case manager:
                p=25000000;
                break;
            case staff:
                p=12600000;
                break;
            case intern:
                p=6300000;
                break;
        }
        return p;
    }
}
