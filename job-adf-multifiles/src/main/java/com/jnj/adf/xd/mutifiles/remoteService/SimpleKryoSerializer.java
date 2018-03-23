package com.jnj.adf.xd.mutifiles.remoteService;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.ByteBufferInput;
import com.esotericsoftware.kryo.io.ByteBufferOutput;
import com.esotericsoftware.kryo.io.Output;
import com.gemstone.gemfire.internal.ClassPathLoader;

/**
 * Created by dyang39 on 6/30/2017.
 */
public class SimpleKryoSerializer {
	public final static int defaultPoolSize = 16;
    public final static int buffSize = 4096;
    public final static int maxBuffSize = 655370000;

    private final static ThreadLocal<Kryo> tl_basic_kryo = new ThreadLocal<Kryo>() {
        @Override
        protected Kryo initialValue() {
            Kryo kryo = new Kryo();
            return kryo;
        }
    };

    public final static Kryo findKryo() {
        Kryo kryo = tl_basic_kryo.get();
        ClassLoader old= kryo.getClassLoader();
        ClassLoader newLoader=ClassPathLoader.getLatestAsClassLoader();
        if(!old.equals(newLoader)) {
            kryo.getClassResolver().reset();
            kryo.setClassLoader(ClassPathLoader.getLatestAsClassLoader());
        }
        return kryo;
    }

    public final static <T> byte[] toBytes(T value){
        return toBytes(findKryo(),value);
    }

    public final static <T> byte[] toBytes(Kryo kryo,T value) {
    	ByteBufferOutput out = new ByteBufferOutput(buffSize, maxBuffSize);
        try {
            return toBytes(kryo,out,value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (out != null){
            	out.release();
            	out.close();
            }
        }
    }

    public final static <T> byte[] toBytes(Kryo kryo,Output out ,T value) {
        try {
            kryo.writeObject(out, value);
            out.flush();
            byte[] bytes = out.toBytes();
            return bytes;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public final static <T> T toObject(byte[] bytes,Class<T> tClass) {
        Kryo kryo=findKryo();
        T t=toObject(kryo,bytes,tClass);
        return t;
    }
    
    public final static <T> T toObject(Kryo kryo,byte[] bytes,Class<T> tClass) {
    	ByteBufferInput input = null;
        try {
            input = new ByteBufferInput(bytes);
            T t = (T) kryo.readObject(input,tClass);
            return t;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally{
        	if(input!=null){
        		input.release();
        	}
        }
    }
}
