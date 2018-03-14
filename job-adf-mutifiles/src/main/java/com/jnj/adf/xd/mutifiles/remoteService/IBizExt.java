package com.jnj.adf.xd.mutifiles.remoteService;

import com.jnj.adf.client.api.IRemoteService;
import com.jnj.adf.config.CglibRemoteServiceProxy;
import com.jnj.adf.config.annotations.RemoteServiceApi;

/**
 * Created by dyang39 on 7/10/2017.
 */
public class IBizExt {
    @SuppressWarnings("unchecked")
	public static <T> T getRemoteService(Class<T> class1) {
		RemoteServiceApi anno= class1.getAnnotation(RemoteServiceApi.class);
        CglibRemoteServiceProxy<T> proxy = new CglibRemoteServiceProxy<>();
        IRemoteService<T> srv= proxy.bindClientApi((T)class1,
                anno.value());
        return (T)srv;
	}
    
    @SuppressWarnings("unchecked")
	public static <T> IRemoteService<T> getIRemoteService(Class<T> class1) {
		RemoteServiceApi anno= class1.getAnnotation(RemoteServiceApi.class);
        CglibRemoteServiceProxy<T> proxy = new CglibRemoteServiceProxy<>();
        IRemoteService<T> srv= proxy.bindClientApi((T)class1,
                anno.value());
        return srv;
	}
}
