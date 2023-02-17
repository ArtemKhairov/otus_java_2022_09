package ru.otus.dataprocessor;

import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class FileSerializer implements Serializer {
    private final  String filePath;
    public FileSerializer(String fileName) {
        this.filePath = fileName;
    }

    @Override
    public void serialize(Map<String, Double> data) {
        try(FileWriter fw = new FileWriter(filePath)){
            new Gson().toJson(data,fw);
        }catch (IOException ex){
            throw new FileProcessException(ex);
        }
    }
}
