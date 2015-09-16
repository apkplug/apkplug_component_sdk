package com.apkplug.component;


public interface ServerCallback<T> {
		/**
		 * 获取到相应的服务
		 * @param service
		 */
		public abstract void onSuccess(T service);
		/**
		 * 支付服务获取失败
		 * @param errorNo
		 * @param strMsg
		 */
		public abstract void onFailure(int errorNo ,String strMsg);
}
