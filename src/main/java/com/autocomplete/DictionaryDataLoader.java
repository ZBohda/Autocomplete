package com.autocomplete;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

public class DictionaryDataLoader {

    private static final Logger LOG = Logger.getLogger(DictionaryDataLoader.class.getName());
    private static final String WORD_REGEX = "[^a-z]+";

    public static String[] getData(String path) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            LOG.error(e);
        }

        return Pattern.compile(WORD_REGEX).split(stringBuilder);
    }
}
