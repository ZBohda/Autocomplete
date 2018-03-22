package com.autocomplete;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputReader {

    private static final Logger LOG = Logger.getLogger(InputReader.class.getName());

    public static String[] getInputData() {
        StringBuilder stringBuilder = new StringBuilder();
        String[] strings = new String[0];
        Pattern pattern = Pattern.compile("[a-z]+");
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\words.txt"));
            String line = bufferedReader.readLine();
            while (line != null) {
                Matcher matcher = pattern.matcher(line);
                while (matcher.find()) {
                    stringBuilder.append(matcher.group()).append(" ");
                }
                line = bufferedReader.readLine();
            }
            String result = stringBuilder.toString();
            strings = result.split(" ");
        } catch (IOException e) {
            LOG.error(e);
        }
        return strings;
    }
}
