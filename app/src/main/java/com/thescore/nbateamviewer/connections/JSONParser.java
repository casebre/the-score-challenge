package com.thescore.nbateamviewer.connections;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.thescore.nbateamviewer.entities.Team;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by rafael on 26/03/18.
 */

public class JSONParser {

    private String FILE_NAME = "input.json";
    private  Gson converter = new Gson();
    private JsonParser jsonParser = new JsonParser();

    private String loadFromFile(Context context, String fileName) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(fileName);

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return json;
    }


    public ArrayList<Team> getTeams(Context context) {
        JsonParser parser = new JsonParser();
        String results = loadFromFile(context, FILE_NAME);
        return getArrayListFromJson(parser.parse(results).getAsJsonArray());

    }

    public ArrayList<Team> getArrayListFromJson(JsonArray jsonArray) {
        Type collectionType = new TypeToken<ArrayList<Team>>() {}.getType();
        return converter.fromJson(jsonArray, collectionType);
    }

}
