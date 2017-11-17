package com.taeschma.api;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.SimpleCard;
import com.taeschma.domain.CurrentStationWeather;
import com.taeschma.domain.StationRawData;
import com.taeschma.service.RawDataService;
import com.taeschma.util.WeatherMapper;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author marco
 */
@Slf4j
@RestController
public class AlexaApi {

    private RawDataService rawDataService;

    public AlexaApi(RawDataService rawDataService) {
        this.rawDataService = rawDataService;
    }
    
    @RequestMapping("/alexa/api")
    public String hello(Model model, @RequestParam(value = "name", required = false, defaultValue = "World") String name) {
        log.info("AlexaApi@hello");

        return "{\"version\":\"1.0\",\"response\":{\"outputSpeech\":{\"type\":\"PlainText\",\"text\":\"Heute hat es bisher 2,1 Liter geregnet.\",\"ssml\":null},\"shouldEndSession\":true}}";
    }

    @RequestMapping("/alexa/amazon/api")
    public AlexaResponse amazonApi() {
        log.info("AlexaApi@amazonApi");

        String speechText = getStationWeather();

        // Create the Simple card content.
        SimpleCard card = new SimpleCard();
        card.setTitle("HelloWorld");
        card.setContent(speechText);

        // Create the plain text output.
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

        SpeechletResponse newTellResponse = SpeechletResponse.newTellResponse(speech);

        log.info(newTellResponse.toString());

        AlexaResponse alexaResponse = new AlexaResponse("1.0", newTellResponse);

        return alexaResponse;

    }
    
    private String getStationWeather() {
        String alexaAnsage = "Das sind die aktuellen Daten der Wetterstation vom ";
        
        List<StationRawData> findAllForToday = rawDataService.findAllForToday("");
        List<CurrentStationWeather> currentStationWeatherList = WeatherMapper.getCurrentStationWeather(findAllForToday);
        
        

        if (null != currentStationWeatherList && currentStationWeatherList.size() > 0) {
            log.debug("Anzahl: " + currentStationWeatherList.size());
            
            CurrentStationWeather aussen = currentStationWeatherList.get(0);
            Date lastUpdate = aussen.getTimestamp();

            log.info(lastUpdate.toString());

            ZoneId defaultZoneId = ZoneId.of("CET");
            //1. Convert Date -> Instant
            Instant instant = lastUpdate.toInstant();
            //System.out.println("instant : " + instant); //Zone : UTC+0
            //3. Instant + system default time zone + toLocalDateTime() = LocalDateTime
            LocalDateTime localDateTime = instant.atZone(defaultZoneId).toLocalDateTime();
            log.debug("localDateTime : " + localDateTime);
            DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.GERMAN);
            DateTimeFormatter formatterHour = DateTimeFormatter.ofPattern("HH", Locale.GERMAN);
            DateTimeFormatter formatterMinute = DateTimeFormatter.ofPattern("mm", Locale.GERMAN);

            String formatDate = localDateTime.format(formatterDate);
            String formatHour = localDateTime.format(formatterHour);
            String formatMinute = localDateTime.format(formatterMinute);
            
            alexaAnsage += formatDate + " um " + formatHour + " Uhr " + formatMinute;
            
            alexaAnsage += " Aktuelle Außentemperatur " + aussen.getTemperature().toString().replace('.', ',') + " Grad ";
            alexaAnsage += " Tageshöchsttemperatur " + aussen.getMaxTemperature().toString().replace('.', ',') + " Grad ";
            alexaAnsage += " Tagesminimum " + aussen.getMinTemperature().toString().replace('.', ',') + " Grad ";
            alexaAnsage += " Regenmenge heute " + aussen.getPrecipMMSum().toString().replace('.', ',') + " Liter pro Quadratmeter ";
            
            if(currentStationWeatherList.size()>1) {
                CurrentStationWeather innen = currentStationWeatherList.get(1);
                alexaAnsage += " Aktuelle Innentemperatur " + innen.getTemperature().toString().replace('.', ',') + " Grad ";
            }
            
        }
        else {
            alexaAnsage = "Aktuelle keine Wetterdaten verfügbar";
        }
        
        return alexaAnsage;
    }

}

@Data
@NoArgsConstructor
@RequiredArgsConstructor
class AlexaResponse {

    @NonNull
    private String version;
    @NonNull
    private SpeechletResponse response;
}
