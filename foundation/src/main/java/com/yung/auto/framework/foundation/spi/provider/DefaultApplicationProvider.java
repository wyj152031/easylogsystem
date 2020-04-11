package com.yung.auto.framework.foundation.spi.provider;

import com.yung.auto.framework.foundation.constants.FrameWorkConstants;
import com.yung.auto.framework.foundation.spi.env.EnvType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

import static com.yung.auto.framework.foundation.constants.AppPropertiesConstants.FRAMEWORK_APP_ID_DEFAULT_VALUE;
import static com.yung.auto.framework.foundation.constants.AppPropertiesConstants.FRAMEWORK_APP_ID_KEY;

/**
 * @author yungwang
 * @date 2020/4/11.
 */
public class DefaultApplicationProvider extends AbstractProvider {

    private final Logger logger = LoggerFactory.getLogger(DefaultApplicationProvider.class);
    private static final String APP_PROPERTIES_CLASSPATH = "/META-INF/app.properties";
    private static final String APP_PROPERTIES = "app.properties";
    private String appId;

    private EnvType envType;

    private Properties appProperties = new Properties();

    @Override
    public void initialize() {
        try {
            initializeConfig();
            initAppId();
        } catch (Exception var2) {
            logger.error(var2.toString());
        }
    }

    private void initialize(InputStream in) {
        try {
            if (in != null) {
                try {
                    this.appProperties.load(new InputStreamReader(in, StandardCharsets.UTF_8));
                } finally {
                    in.close();
                }
            }
        } catch (Exception var6) {
            logger.error(var6.toString());
        }
    }

    private void initializeConfig() {
        File file = new File(FrameWorkConstants.SYSTEM_DIR_CONFIG + APP_PROPERTIES);
        if (file.exists()) {
            load(FrameWorkConstants.SYSTEM_DIR_CONFIG + APP_PROPERTIES);
        }
        if (this.appProperties != null) {
            return;
        }
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(APP_PROPERTIES_CLASSPATH);
        if (in == null) {
            in = DefaultApplicationProvider.class.getResourceAsStream(APP_PROPERTIES_CLASSPATH);
        }

        if (in == null) {
            logger.error("/META-INF/app.properties not found from classpath!", new String[0]);
        }
        this.initialize(in);
    }

    private void load(String filePath) {
        try {
            if (null != appProperties) {
                if (filePath.contains(FrameWorkConstants.RELATIVE_DIR_CONFIG)) {
                    appProperties.load(new InputStreamReader(new BufferedInputStream(new FileInputStream(filePath)), "UTF-8"));
                } else {
                    appProperties.load(DefaultApplicationProvider.class.getClassLoader().getResourceAsStream(filePath));
                }
            }
        } catch (Exception var3) {
            logger.info("FrameWork load Config File Failed: {}", filePath, var3);
        }
    }

    private void initAppId() {
        this.appId = this.appProperties.getProperty(FRAMEWORK_APP_ID_KEY);
        if (!StringUtils.isEmpty(this.appId)) {
            this.appId = this.appId.trim();
            logger.info("AppID is set to " + this.appId + " by app.id property from /META-INF/app.properties", new String[0]);
        } else {
            logger.error("app.id is not available from /META-INF/app.properties. It is set to null", new String[0]);
            this.appId = FRAMEWORK_APP_ID_DEFAULT_VALUE;
        }
    }


    @Override
    public Class<? extends Provider> getType() {
        return ApplicationProvider.class;
    }

    @Override
    public String getProperty(String name, String defaultValue) {
        String val = this.appProperties.getProperty(name, defaultValue);
        return val == null ? defaultValue : val;
    }

    public String getAppId() {
        return this.appId;
    }

    public EnvType getEnvType() {
        return this.envType;
    }

    public void setEnvType(EnvType envType) {
        this.envType = envType;
    }

    public String toString() {
        return "properties: " + this.appProperties + " (DefaultApplicationProvider)";
    }
}
