package com.github.xuchen93.cloud.exception;

/**
 * 限流异常
 */
public class SentinelException extends RuntimeException {
	int code = 1;

	public SentinelException(String message) {
		super(message);
	}

	public SentinelException(int code, String message) {
		super(message);
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}
