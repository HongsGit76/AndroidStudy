package com.example.hello;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {

    // 서버 URL 설정
    final static private  String URL = "https://99edad6df78b.ngrok.io/login";
    private Map<String, String> map;
    public LoginRequest(
            String userID,
            String userPassword,
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
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
