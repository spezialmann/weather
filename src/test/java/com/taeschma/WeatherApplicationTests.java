package com.taeschma;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeatherApplicationTests {

	@Test
	public void contextLoads() {
            String temp = "bar not found";
            assertEquals("bar not found", temp);
	}

}
