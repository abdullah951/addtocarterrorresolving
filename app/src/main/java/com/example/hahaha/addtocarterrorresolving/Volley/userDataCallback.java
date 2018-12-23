package com.example.hahaha.addtocarterrorresolving.Volley;

import org.json.JSONObject;

public interface userDataCallback {
    void onSuccess(JSONObject jsonObject);

    void onError(String message);
}
