package com.kaizhuo.monitor.core.util;

import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Base64;

/**
 * @program: tiangong
 * @package: com.kaizhuo.monitor.core.util
 * @description:
 * @author: miaochen
 * @create: 2020-02-28 21:42
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
@Slf4j
public class SerializeUtil {
    public static String serialize(Object obj){
        String msg=null;
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bos);
            out.writeObject(obj);
            msg = Base64.getEncoder().encodeToString(bos.toByteArray());
        } catch (Exception e) {
            log.error("object serialize error:", e);
        }
        return msg;
    }

    public static Object deserialize(String msg) {
        Object obj = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(Base64.getDecoder().decode(msg));
            ObjectInputStream in = new ObjectInputStream(bis);
            obj = in.readObject();
        } catch (Exception e) {
            log.error("msg deserialize error:", e);
        }
        return obj;
    }
}
