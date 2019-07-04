package com.profiler.utils;

import org.openqa.selenium.WebDriver;

public interface SeleniumFactory {
	public WebDriver getWebDriver();
	public WebDriver getRemoteWebDriver();
}
