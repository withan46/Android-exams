package com.example.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AnimalList {

    private ArrayList<Animal> animalList = new ArrayList<>();

    AnimalList(String ip){
        String url= "http://"+ip+"/animals/getAnimals.php";

        try {
            OkHttpHandler okHttpHandler = new OkHttpHandler();
            animalList = okHttpHandler.getAnimals(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> getAllAnimalName() {
        List<String> temp = new ArrayList<>();
        for (int i=0; i<animalList.size(); i++) {
            temp.add(animalList.get(i).getName());
        }
        return temp;
    }

    public String getAnimalImage(String animalName) {
        for (int i = 0; i < animalList.size(); i++){
            if (Objects.equals(animalName, animalList.get(i).getName())){
                return animalList.get(i).getImage();
            }
        }
        return null;
    }

    public Object getAnimalType(String animalType) {
        for (int i = 0; i < animalList.size(); i++){
            if (Objects.equals(animalType, animalList.get(i).getName())){
                return animalList.get(i).getType();
            }
        }
        return null;
    }

    public Object getAnimalVoice(String animalVoice) {
        for (int i = 0; i < animalList.size(); i++){
            if (Objects.equals(animalVoice, animalList.get(i).getName())){
                return animalList.get(i).getVoice();
            }
        }
        return null;
    }
}
