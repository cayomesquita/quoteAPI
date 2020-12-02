package com.quotemedia.interview.quoteservice;

import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CachingConfig {

    private static final int TIME_TO_LIVE_SECONDS = 30;

    @Bean
    public Config config(){
        Config config = new Config();
        MapConfig mapConfig = new MapConfig();
        mapConfig.setTimeToLiveSeconds(TIME_TO_LIVE_SECONDS);
        config.getMapConfigs().put("symbols", mapConfig);
        return config;
    }

}

