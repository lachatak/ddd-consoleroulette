package org.kaloz.roulette.config;

import org.kaloz.roulette.config.adapters.DrivingInfrastructureLayerConfig;
import org.kaloz.roulette.config.adapters.DrivenInfrastructureLayerConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ DrivingInfrastructureLayerConfig.class, DrivenInfrastructureLayerConfig.class })
public class InfrastructureLayerConfig {

}
