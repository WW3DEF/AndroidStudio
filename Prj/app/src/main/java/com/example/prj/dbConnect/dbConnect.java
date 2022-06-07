package com.example.prj.dbConnect;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class dbConnect extends AsyncTask<String, Void, String> {
    String sendMsg, receiveMsg;

    @Override
    protected String doInBackground(String... strings) {
        try {
            String str;
            URL url = null;

            //--------------------------------------------------------------------------------------------------------
            if(strings[1].equals("recipes")){
                url = new URL("http://10.0.2.2:8080/android/Android/recipes.jsp");
            }
            else if(strings[1].equals("user")){
                url = new URL("http://10.0.2.2:8080/android/Android/user.jsp");
            }
            else if(strings[1].equals("favorite")){
                url = new URL("http://10.0.2.2:8080/android/Android/favorite.jsp");
            }
            else if(strings[1].equals("recipecontent")){
                url = new URL("http://10.0.2.2:8080/android/Android/recipescontent.jsp");
            }
            else if(strings[1].equals("board")){
                url = new URL("http://10.0.2.2:8080/android/Android/Board.jsp");
            }

            //--------------------------------------------------------------------------------------------------------

            if(strings[0].equals("selectBase")){
                sendMsg = "type="+strings[0]+"&base="+strings[2];
            }
            if(strings[0].equals("selectRecipe")){
                sendMsg = "type="+strings[0]+"&keyword="+strings[2];
            }
            else if(strings[0].equals("selectFavorite")){
                sendMsg = "type="+strings[0]+"&userid="+strings[2];
            }
            else if(strings[0].equals("userCheck")){
                sendMsg = "type="+strings[0]+"&id="+strings[2]+"&pwd="+strings[3];
            }
            else if(strings[0].equals("insertUser")){
                sendMsg = "type="+strings[0]+"&id="+strings[2]+"&nickName="+strings[3]+"&pwd="+strings[4];
            }
            else if(strings[0].equals("favoriteCheck")){
                sendMsg = "type="+strings[0]+"&userid="+strings[2]+"&recipesname="+strings[3];
            }
            else if(strings[0].equals("deleteFavorite")){
                sendMsg = "type="+strings[0]+"&userid="+strings[2]+"&recipesname="+strings[3];
            }
            else if(strings[0].equals("insertFavorite")){
                sendMsg = "type="+strings[0]+"&userid="+strings[2]+"&recipesname="+strings[3];
            }
            else if(strings[0].equals("insertRecipes")){
                sendMsg = "type="+strings[0]+"&msg="+strings[2];
            }
            else if(strings[0].equals("selectRecipecontent")){
                sendMsg = "type="+strings[0]+"&name="+strings[2];
            }
            else if(strings[0].equals("selectFavoriteRecipe")){
                sendMsg = "type="+strings[0]+"&userid="+strings[2]+"&keyword="+strings[3];
            }
            else if(strings[0].equals("deleteRecipe")){
                sendMsg = "type="+strings[0]+"&name="+strings[2];
            }
            else if(strings[0].equals("selectBoard")){
                sendMsg = "type="+strings[0];
            }
            else if(strings[0].equals("insertBoard")){
                sendMsg = "type="+strings[0]+"&nickName="+strings[2]+"&title="+strings[3]+"&content="+strings[4];
            }
            else if(strings[0].equals("selectId")){
                sendMsg = "type="+strings[0]+"&id="+strings[2];
            }
            else if(strings[0].equals("updateBoard")){
                sendMsg = "type="+strings[0]+"&id="+strings[2]+"&title="+strings[3]+"&content="+strings[4];
            }
            //--------------------------------------------------------------------------------------------------------

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            con.setRequestMethod("POST");
            OutputStreamWriter osw = new OutputStreamWriter(con.getOutputStream(),"UTF-8");

            osw.write(sendMsg);
            osw.flush();
            if(con.getResponseCode() == con.HTTP_OK){
                InputStreamReader tmp = new InputStreamReader(con.getInputStream(),"UTF-8");
                BufferedReader reader = new BufferedReader(tmp);
                StringBuffer buffer = new StringBuffer();
                while((str = reader.readLine())!=null){
                    buffer.append(str);
                }
                receiveMsg = buffer.toString();
            }else{
                Log.i("통신 결과", con.getResponseCode()+"에러");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("결과 : "+receiveMsg);
        return receiveMsg;
    }
}