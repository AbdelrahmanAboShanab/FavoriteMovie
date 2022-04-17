package com.example.favoritemovie.db;

import androidx.room.TypeConverter;

import com.google.gson.Gson;

import java.util.List;

public class Converters {

    @TypeConverter
    public String FromListToString(List<Integer> list){
        return new Gson().toJson(list);
    }

    @TypeConverter
    public List<Integer> FromStringToList(String list){
        return new Gson().fromJson(list,List.class);
    }
}
