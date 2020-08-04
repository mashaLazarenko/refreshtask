package com.example.main;

import com.example.refresh.FieldInjector;
import com.example.refresh.PropertyParser;
import com.example.user.UserRefresher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Main {

    private final static Logger logger = LoggerFactory.getLogger(FieldInjector.class);
    public static void main(String[] args) {

        if(args.length == 0){
            logger.error("No property file");
            return;
        }

        String filePath = args[0];

        try {
            UserRefresher user = UserRefresher.getInstance(new PropertyParser(filePath), new FieldInjector());
            logger.info("After init: \n" + user.toString());
            user.doRefresh();
            logger.info("After refresh: \n" + user.toString());
        } catch (IOException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }
}
