package com.wan.api.amqp;

import org.springframework.stereotype.Component;


@Component
public class ResultHandler
{

    public void handleMessage(ResultMessage scrapingResultMessage)
    {
        System.out.println("Received summary: " + scrapingResultMessage.getSummary());
        final String url = scrapingResultMessage.getUrl();

    }
}
