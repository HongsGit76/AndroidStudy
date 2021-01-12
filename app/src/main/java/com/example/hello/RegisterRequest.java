package com.example.hello;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {

    // 서버 URL 설정
    final static private  String URL = "http://127.0.0.1:3000/register";
    private Map<String, String> map;
    public RegisterRequest(
            String userID,
            String userPassword,
            String userName,
            int userAge,
            Response.Listener<String> listener
    ){
        super(
                Method.POST,
                URL,
                listener,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("ERR: " + error.getMessage());
                    }
                }
        );

        map = new HashMap<>();
        map.put("userID", userID);
        map.put("userPassword", userPassword);
        map.put("userName", userName);
        map.put("userAge", userAge + "");

    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
