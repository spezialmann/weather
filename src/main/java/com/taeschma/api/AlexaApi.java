package com.taeschma.api;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.IntentRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author marco
 */
@Slf4j
@RestController
public class AlexaApi {
    
    @RequestMapping("/alexa/api")
    public String hello(Model model, @RequestParam(value = "name", required = false, defaultValue = "World") String name) {
        log.info("AlexaApi@hello");
        
        
        
        
        
        
        return "{\"version\":\"1.0\",\"response\":{\"outputSpeech\":{\"type\":\"PlainText\",\"text\":\"Heute hat es bisher 2,1 Liter geregnet.\",\"ssml\":null},\"shouldEndSession\":true}}";
    }
    
    @RequestMapping("/alexa/amazon/api")
    public String amazonApi(IntentRequest intentRequest) {
        log.info("AlexaApi@amazonApi");
        
        
        Intent intent = intentRequest.getIntent();
        log.info(intent.toString());
        
        
        
        return "{\"version\":\"1.0\",\"response\":{\"outputSpeech\":{\"type\":\"PlainText\",\"text\":\"Heute hat es bisher 2,1 Liter geregnet.\",\"ssml\":null},\"shouldEndSession\":true}}";
    }
    
    
    
}
