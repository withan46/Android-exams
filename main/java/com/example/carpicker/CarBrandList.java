package com.example.carpicker;

import java.util.*;

public class CarBrandList {
    ArrayList<CarBrand> cbList = new ArrayList<CarBrand>();

    public CarBrandList(String ip) {
        String url= "http://"+ip+"/multimediaDBServices/getMedia.php";

        try {
            OkHttpHandler okHttpHandler = new OkHttpHandler();
            cbList = okHttpHandler.populateDropDown(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> getAllBrands() {
        List<String> temp = new ArrayList<String>();
        for (int i=0; i<cbList.size(); i++) {
            temp.add(cbList.get(i).getName());
        }
        return temp;
    }

    public List<String> getAllModels(String b) {
        List<String> temp = new ArrayList<String>();
        for (int i=0; i<cbList.size(); i++) {
            if (cbList.get(i).hasName(b)) {
                temp = cbList.get(i).getAllModels();
            }
        }
        return temp;
    }

    /**
     * Method added to return CarBrand.Media object when model is provided
     * */
    public Media lookup(String brand, String model) {
        for (int i = 0; i < cbList.size(); i++) {
            if (cbList.get(i).hasName(brand)) {
                return cbList.get(i).getMedia(model);
            }

        }
        return null;
    }

}
