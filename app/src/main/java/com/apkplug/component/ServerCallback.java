package com.apkplug.component;


import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class ServerCallback<T> {
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
	public Class<T> clazz;
	void doGetClass() {
		Type genType = this.getClass().getGenericSuperclass();
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		this.clazz = (Class<T>) params[0];
	}


}
