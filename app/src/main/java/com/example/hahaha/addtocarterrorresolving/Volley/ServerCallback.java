package com.example.hahaha.addtocarterrorresolving.Volley;

import com.example.hahaha.addtocarterrorresolving.object;

import java.util.List;

public interface ServerCallback {
    void onSuccess(List<object> list);


    void onError(String message);
}
