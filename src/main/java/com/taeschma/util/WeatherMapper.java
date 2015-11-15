package com.taeschma.util;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.taeschma.domain.CurrentStationWeather;
import com.taeschma.domain.StationRawData;

public class WeatherMapper {

	private final static Logger log = LoggerFactory.getLogger(WeatherMapper.class);
	
	

	public static List<CurrentStationWeather> getCurrentStationWeather(List<StationRawData> dataRows) {
		List<CurrentStationWeather> ret = new ArrayList<>();

		log.info("count: " + dataRows.size());

		String dataString;

		CurrentStationWeather currentWeather = new CurrentStationWeather();

		for (StationRawData dataRow : dataRows) {

			/**
			 * 3..10 = temperature 11..18 = humidity
			 * 
			 * combi station 
			 * 19 = temperature 
			 * 20 = humidity 
			 * 21 = wind speed 
			 * 22 = number of tics (1 tic = 295ml/m² ; 0..4096 tics)
			 * 
			 */
			dataString = (String) dataRow.getRawData();

			if (dataString != null && !dataString.equals("")) {
				String[] dataValues = dataString.split(";");

				currentWeather.setStationId("todo");
				//try {
					currentWeather.setTimestamp(dataRow.getTimeOfRecording());
					currentWeather.setTemperature(Float.parseFloat(dataValues[19].replace(",", ".")));
					currentWeather.setHumidity(Integer.parseInt(dataValues[20]));

					//MIN TEMP
					if (currentWeather.getMinTemperature() == null
							|| (currentWeather.getTemperature().compareTo(currentWeather.getMinTemperature()) < 0)) {
						currentWeather.setMinTemperature(currentWeather.getTemperature());
					}

					//MAX TEMP
					if (currentWeather.getMaxTemperature() == null
							|| (currentWeather.getTemperature().compareTo(currentWeather.getMaxTemperature()) > 0)) {
						currentWeather.setMaxTemperature(currentWeather.getTemperature());
					}
					
					//RAIN
					Integer currentTics = Integer.parseInt(dataValues[22]);
					Integer ticsSinceLast = 0;
					//first entry of the day or no rain since last entry
					/**
					 * TODO Calculation of the first value of the day need the last value from day before...
					 */
					if (currentWeather.getPrecipCount() == null || currentWeather.getPrecipCount().equals(currentTics)) {
						currentWeather.setPrecipMM(new Float("0.0"));
					}
					//rain
					else {
						ticsSinceLast = getCurrentPrecipCount(currentWeather.getPrecipCount(), currentTics);
						currentWeather.setPrecipMM(ticsSinceLast * 295 / new Float("1000.0"));						
					}
					currentWeather.setPrecipMMSum(currentWeather.getPrecipMMSum()+currentWeather.getPrecipMM());
					
					currentWeather.setPrecipCount(currentTics);
					currentWeather.setWindspeedKmph(Float.parseFloat(dataValues[21].replace(",", ".")));
				/**
				} catch (Exception e) {
					log.error("Error parsing temperature: " + e.getMessage());
				}
				**/

			}
		}
		ret.add(currentWeather);
		return ret;
	}
	
	/**
	 * calculate the number of tics
	 *  
	 * @param oldValue
	 * @param newValue
	 * @return
	 */
	private static Integer getCurrentPrecipCount(Integer oldValue, Integer newValue) {
		Integer tempTics = newValue-oldValue;
		if (oldValue.compareTo(newValue)>0) {
			tempTics = (4096 - oldValue) + newValue;
		}
		return tempTics;
	}
	

	/**
	 * Liefert key-value Paare für eine stationId key=Zeitstempel
	 * value=Temparatur
	 *
	 * Anstelle der Daten-Strings (z. B. $1;1;;6,3;;;;;;;;98;;;;;;;;;;;;;0 )
	 * wird die Temperatur (z. B. 6.3 ) der gewünschten Station geliefert
	 *
	 * @param dataRows
	 * @param stationId
	 *            Id der wetterstation (1..8)
	 * @return
	 */
	/**
	 * public static String getTemperatureListForStation(List<DataRow> dataRows,
	 * Long stationId) { String ret = "";
	 * 
	 * if (stationId == null || stationId < 1 || stationId > 8) { stationId =
	 * 3L; } stationId += 2;
	 * 
	 * int i = 0; for (DataRow next : dataRows) { String[] dataValues =
	 * next.getData().split(";"); if (i>0) { ret += ", "; } ret += "{ x: " +
	 * next.getLogDate().getTime() + ", y: " +
	 * Float.parseFloat(dataValues[stationId.intValue()].replace(",", ".")) +
	 * " }"; i++; }
	 * 
	 * return ret; }
	 **/
}
