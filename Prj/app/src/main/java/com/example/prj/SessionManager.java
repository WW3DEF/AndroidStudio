package com.example.prj;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    public SessionManager(Context context){
        sharedPreferences = context.getSharedPreferences("AppKey",0);
        editor = sharedPreferences.edit();
        editor.apply();
    }
    public void setId(String id){
        editor.putString("KEY_ID",id);
        editor.commit();
    }
    public String getId(){
        return sharedPreferences.getString("KEY_ID","");
    }
    public void setPwd(String pwd){
        editor.putString("KEY_PWD",pwd);
        editor.commit();
    }
    public String getPwd(){
        return sharedPreferences.getString("KEY_PWD","");
    }
    public void setName(String nickName){
        editor.putString("KEY_NAME",nickName);
        editor.commit();
    }
    public String getName(){
        return sharedPreferences.getString("KEY_NAME","");
    }
    public void setSearch(String search){
        editor.putString("KEY_SEARCH", search);
        editor.commit();
    }
    public String getSearch() {
        return sharedPreferences.getString("KEY_SEARCH", "");
    }
}
