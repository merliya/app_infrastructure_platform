package com.jbhunt.infrastructure.platform.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.session.ExpiringSession;
import org.springframework.session.web.socket.config.annotation.AbstractSessionWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

import com.jbhunt.biz.securepid.PIDCredentials;
import com.jbhunt.infrastructure.platform.properties.PlatformApplicationProperties;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractSessionWebSocketMessageBrokerConfigurer<ExpiringSession> {
	private final PIDCredentials pidCredentials;
	private final PlatformApplicationProperties platformApplicationProperties;

	public WebSocketConfig(PlatformApplicationProperties platformApplicationProperties, PIDCredentials pidCredentials) {
		this.platformApplicationProperties = platformApplicationProperties;
		this.pidCredentials = pidCredentials;
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.enableStompBrokerRelay("/topic", "/queue").setRelayHost(platformApplicationProperties.getHost())
				.setRelayPort(platformApplicationProperties.getPort()).setSystemLogin(pidCredentials.getUsername())
				.setSystemPasscode(pidCredentials.getPassword()).setClientLogin(pidCredentials.getUsername())
				.setClientPasscode(pidCredentials.getPassword());
		config.setApplicationDestinationPrefixes("/app");
	}

	@Override
	public void configureStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/messaging").withSockJS();
	}
}
