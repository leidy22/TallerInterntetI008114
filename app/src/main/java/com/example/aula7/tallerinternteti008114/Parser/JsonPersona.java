package com.example.aula7.tallerinternteti008114.Parser;

import com.example.aula7.tallerinternteti008114.Models.Persona;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AULA 7 on 17/04/2018.
 */

public class JsonPersona {
    public static List<Persona> getDataJson(String s) throws JSONException {

        JSONArray jsonArray = new JSONArray(s);
        List<Persona> personaList = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++){
            JSONObject item = jsonArray.getJSONObject(i);

           Persona persona = new Persona();

           persona.setCodigo(item.getString("codigo"));
           persona.setName(item.getString("name"));
           persona.setEdad(item.getString("edad"));
           persona.setPass(item.getString("pass"));

            personaList.add(persona);
        }

        return personaList;
    }
}

