package cacheServer;

import java.sql.Timestamp;

public class CachedObject {
	private String requestName;
	private String value;
	private long addedTime;

	public String getRequestName() {
		return requestName;
	}

	public void setRequestName(String requestName) {
		this.requestName = requestName;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public long getAddedTime() {
		return addedTime;
	}

	public void setAddedTime(long timeOut) {
		this.addedTime = timeOut;
	}

	public CachedObject(String requestName, String value) {
		super();
		this.requestName = requestName;
		this.value = value;
	}

	public boolean timedOut(long timeout) {
		return (this.addedTime + timeout < System.currentTimeMillis());
	}
}
