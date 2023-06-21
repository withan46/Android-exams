package com.example.carpicker;

import java.util.*;

public class CarBrand {
    private String name;
    private List<String> models = new ArrayList<String>();
    private HashMap<String, Media> media = new HashMap<>();

    public CarBrand(String brand, String models, String images) {
        name = brand;
        this.models = Arrays.asList(models.split(","));
        String[] imageArray = images.split(",");
        for (int i=0; i<this.models.size(); i++) {
            media.put(this.models.get(i),new Media(imageArray[i]));
        }
    }

    public Media getMedia(String key){
        return this.media.get(key);
    }
    public boolean hasName(String b) {
        return name.equals(b);
    }
    public String getName() {return name; }
    public List<String> getAllModels() {return models;}
}
