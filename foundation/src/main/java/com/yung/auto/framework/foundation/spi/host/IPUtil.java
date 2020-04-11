package com.yung.auto.framework.foundation.spi.host;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * @author yungwang
 * @date 2020/4/11.
 */
public class IPUtil {

    private static final Logger logger = LoggerFactory.getLogger(IPUtil.class);
    public static String IP = getLocalIP();

    public IPUtil() {
    }

    public static String getIP() {
        return IP;
    }

    public static boolean isWindowsOS() {
        boolean isWindowsOS = false;
        String osName = System.getProperty("os.name");
        if (osName.toLowerCase().indexOf("windows") > -1) {
            isWindowsOS = true;
        }

        return isWindowsOS;
    }

    private static String getLocalIP() {
        try {
            Enumeration interfaces = NetworkInterface.getNetworkInterfaces();

            while(true) {
                NetworkInterface current;
                do {
                    do {
                        do {
                            if (!interfaces.hasMoreElements()) {
                                return "";
                            }

                            current = (NetworkInterface)interfaces.nextElement();
                        } while(!current.isUp());
                    } while(current.isLoopback());
                } while(current.isVirtual());

                Enumeration addresses = current.getInetAddresses();

                while(addresses.hasMoreElements()) {
                    InetAddress addr = (InetAddress)addresses.nextElement();
                    if (!addr.isLoopbackAddress() && addr.getHostAddress().indexOf(":") == -1 && !addr.getHostAddress().contains("localhost")) {
                        return addr.getHostAddress();
                    }
                }
            }
        } catch (Exception var4) {
            logger.error(var4.toString());
            return "";
        }
    }
}
