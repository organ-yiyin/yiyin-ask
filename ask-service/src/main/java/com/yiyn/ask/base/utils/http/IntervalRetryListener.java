package com.yiyn.ask.base.utils.http;

import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.RetryListener;

public class IntervalRetryListener implements RetryListener{
	
	private int sleepMilliSeconds;
	
	public IntervalRetryListener(int sleepMilliSeconds){
		this.sleepMilliSeconds = sleepMilliSeconds;
	}

	@Override
	public <T> void close(RetryContext arg0, RetryCallback<T> arg1,
			Throwable arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T> void onError(RetryContext arg0, RetryCallback<T> arg1,
			Throwable arg2) {
		try {
			Thread.sleep(sleepMilliSeconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public <T> boolean open(RetryContext arg0, RetryCallback<T> arg1) {
		// TODO Auto-generated method stub
		return true;
	}

	
}
