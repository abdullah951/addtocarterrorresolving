package com.example.hahaha.addtocarterrorresolving;

import java.util.List;

public interface OrderStatusCallBack {
    void OnSuccess(List<object> list);

    void OnError(String message);
}
