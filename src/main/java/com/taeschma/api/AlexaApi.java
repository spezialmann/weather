package com.taeschma.api;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.SimpleCard;
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
    public SpeechletResponse amazonApi() {
        log.info("AlexaApi@amazonApi");

        String speechText = "Hello world";

        // Create the Simple card content.
        SimpleCard card = new SimpleCard();
        card.setTitle("HelloWorld");
        card.setContent(speechText);

        // Create the plain text output.
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

        SpeechletResponse newTellResponse = SpeechletResponse.newTellResponse(speech, card);
        
        log.info(newTellResponse.toString());
        
        return newTellResponse;

    }

}
