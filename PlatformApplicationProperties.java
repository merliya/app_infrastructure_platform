package com.jbhunt.infrastructure.platform.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@RefreshScope
@Component
public class PlatformApplicationProperties {

	@Value("${ldapUserEndpoints.domain}")
	private String ldapEndpointURL;

	@Value("${websocket.messaging.relayHost}")
	private String host;

	@Value("${websocket.messaging.relayPort}")
	private Integer port;

	@Value("${websocket.messaging.topic}")
	private String topicName;

}
