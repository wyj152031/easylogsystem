package com.yung.auto.framework.foundation.constants;

import java.io.File;

/**
 * @author yungwang
 * @date 2020/4/11.
 */
public interface FrameWorkConstants {

    String CONFIG_PREFIX = "config";
    String SYSTEM_DIR_CONFIG = System.getProperty("user.dir") + File.separator + CONFIG_PREFIX + File.separator;
    String RELATIVE_DIR_CONFIG = CONFIG_PREFIX + File.separator;
}
