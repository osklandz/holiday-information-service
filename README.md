Holiday Information Service for retrieving common holiday for two european countries,
list of supported countries and their country codes can be found in "pl.landzberg.holidayinformationservice.common.CountryCode".

Application is developed in Spring Boot 2, example GET endpoint, use for example Postman:
"http://localhost:8080/common/holiday?date=2019-01-01&countryCode1=pl&countryCode2=fr".

I used api from https://holidayapi.com/ to and before starting application, 
you have to get your api key from mentioned website and replace "api.apiKey" in "application.properties" file with it.
