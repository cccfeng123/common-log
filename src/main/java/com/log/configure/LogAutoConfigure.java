package com.log.configure;

import com.log.properties.TraceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties({TraceProperties.class})
public class LogAutoConfigure {
}
