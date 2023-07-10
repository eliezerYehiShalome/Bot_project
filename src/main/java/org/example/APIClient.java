package org.example;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class APIClient {
    private final String JOKE = bringMeJoke();
    private final String CAT_FACT = bringMeCatFact();
    private final String QUOTE = bringMeQuote();
    private final String NUMBER_FACT = bringMeNumberFact();
    private final String ACTIVITY = bringMeActivity();

    public String getJOKE() {
        return JOKE;
    }
    public String getCAT_FACT() {
        return CAT_FACT;
    }
    public String getQUOTE() {
        return QUOTE;
    }
    public String getNUMBER_FACT() {
        return NUMBER_FACT;
    }
    public String getACTIVITY() {
        return ACTIVITY;
    }




    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T fetchDataFromAPI(String url, Class<T> responseType) {
        try {
            HttpResponse<String> response = Unirest.get(url).asString();
            return objectMapper.readValue(response.getBody(), responseType);
        } catch (UnirestException | JsonProcessingException e) {
            throw new RuntimeException("Failed to fetch data from API: " + url, e);
        }
    }

    public static String bringMeJoke() {
        JokeModel jokeModel = fetchDataFromAPI("https://v2.jokeapi.dev/joke/Any?type=single", JokeModel.class);
        return jokeModel.getJoke();
    }

    public static String bringMeCatFact() {
        CatFactModel catFactModel = fetchDataFromAPI("https://catfact.ninja/fact", CatFactModel.class);
        return catFactModel.getFact();
    }

    public static String bringMeQuote() {
        QuoteModel quoteModel = fetchDataFromAPI("https://api.quotable.io/random", QuoteModel.class);
        return quoteModel.getContent();
    }
    public static String bringMeNumberFact() {
        NumbersModel numbersModel = fetchDataFromAPI("http://numbersapi.com/random?json", NumbersModel.class);
        return numbersModel.getText();
    }

    public static String bringMeActivity() {
        ActivityModel activityModel = fetchDataFromAPI("https://www.boredapi.com/api/activity", ActivityModel.class);
        return activityModel.getActivity();
    }

}
