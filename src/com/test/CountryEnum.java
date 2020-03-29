package com.test;

import jdk.nashorn.internal.objects.annotations.Getter;

public enum CountryEnum {
    ONE(1,"齐"),TWO(2,"楚"),THREE(3,"燕"),FOUR(4,"赵"),FIVE(5,"魏"),SIX(6,"韩");
    private Integer retCode;
    private String retMessage;

    CountryEnum(Integer retCode, String retMessage) {
        this.retCode = retCode;
        this.retMessage = retMessage;
    }

    public Integer getRetCode() {
        return retCode;
    }

    public void setRetCode(Integer retCode) {
        this.retCode = retCode;
    }

    public String getRetMessage() {
        return retMessage;
    }

    public void setRetMessage(String retMessage) {
        this.retMessage = retMessage;
    }
    public static CountryEnum for_eachCountryEnum(int index){
        CountryEnum[] array=CountryEnum.values();
        for (CountryEnum tmp : array){
            if(tmp.getRetCode()==index){
                return tmp;
            }
        }
        return null;
    }
}
