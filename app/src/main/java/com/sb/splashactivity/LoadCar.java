package com.sb.splashactivity;

public class LoadCar {
    public String car_VINNum;
    public String car_CarName;
    public String car_CarBrand;
    public String car_CarModel;
    public LoadCar(){
        //default
    }

    public LoadCar(String car_VINNum, String car_CarName, String car_CarBrand, String car_CarModel) {
        this.car_VINNum = car_VINNum;
        this.car_CarName = car_CarName;
        this.car_CarBrand = car_CarBrand;
        this.car_CarModel = car_CarModel;
    }
}
