package com.taeschma.wwo;

import com.taeschma.wwo.response.Weather;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author marco
 */
@Service
public class ApiService {

    @Value("${rest.proxy.enabled:false}")
    private boolean restWithProxy;
    @Value("${rest.proxy.host:}")
    private String proxyHost;
    @Value("${rest.proxy.port:}")
    private Optional<Integer> proxyPort;
    @Value("${rest.scheme:https}")
    private String scheme;
    @Value("${rest.weather.api.key}")
    private String weatherApiKey;
    
    private final Logger log = LoggerFactory.getLogger(ApiService.class);

    public Weather doWeatherRequest(String location) {
        final String uri = scheme
                + "://api.worldweatheronline.com/free/v2/weather.ashx?q={location}&format=json&num_of_days=4&includelocation=yes&show_comments=no&tp=24&key={key}";

        Map<String, String> params = new HashMap<String, String>();
        params.put("location", location);
        params.put("key", weatherApiKey);

        RestTemplate restTemplate = getRestTemplate();
        Weather response = restTemplate.getForObject(uri, Weather.class, params);
        
        log.info(response.toString());
        
        return response;
    }

    private RestTemplate getRestTemplate() {
        if (restWithProxy) {
            SimpleClientHttpRequestFactory clientHttpRequestFactory = new SimpleClientHttpRequestFactory();
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort.get().intValue()));
            clientHttpRequestFactory.setProxy(proxy);
            return new RestTemplate(clientHttpRequestFactory);
        }
        return new RestTemplate();
    }
}
