package com.autocomplete;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputReader {

    private static final Logger LOG = Logger.getLogger(InputReader.class.getName());
    private static final String PATTERN = "[a-z]+";
    private static final String PATH = "C:\\words.txt";
    private static final String DIVIDER = " ";

    public static String[] getInputData() {
        StringBuilder stringBuilder = new StringBuilder();
        Pattern pattern = Pattern.compile(PATTERN);
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(PATH));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                while (matcher.find()) {
                    stringBuilder.append(matcher.group()).append(DIVIDER);
                }
            }
        } catch (IOException e) {
            LOG.error(e);
        }
        String result = stringBuilder.toString();
        return result.split(DIVIDER);
    }
}
