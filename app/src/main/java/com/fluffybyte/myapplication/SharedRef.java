package com.fluffybyte.myapplication;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedRef {
    SharedPreferences ref;
    public SharedRef(Context context){
        ref = context.getSharedPreferences("myRef", Context.MODE_PRIVATE);
    }

    public void saveData(String key, String value){
        SharedPreferences.Editor editor = ref.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public String loadData(String key){
        String value = ref.getString(key, "");
        return value;
    }
}
