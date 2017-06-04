package com.example.guojia.util;

public interface HttpCallbackListener {
	void onFinish(String response);
	void onError(Exception e);
}
