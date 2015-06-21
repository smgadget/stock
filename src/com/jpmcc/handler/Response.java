package com.jpmcc.handler;

public class Response {
	protected boolean success;
	protected Request request;

	protected void setSuccess(boolean success) {
		// TODO Auto-generated method stub
		this.success = success;
	}
	public boolean getSuccess() {
		return this.success;
	}
	protected void setRequest(Request request) {
		this.request = request;
	}
	protected Request getRequest() {
		return request;
	}
	
	public String toString(String title, boolean success, String result) {
		StringBuilder sb = new StringBuilder();
		sb.append(request.toString());
		sb.append("{Calculate " + title + " Result->>> ");
		if (success == true) {
			sb.append("Success;");
		} else {
			sb.append("Failed;");
		}
		sb.append(" result: " + result + ",");
		sb.append("}");
		return sb.toString();
	}
}
