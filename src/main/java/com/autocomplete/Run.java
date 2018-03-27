package com.autocomplete;

public class Run {

    public static void main(String... args) {
        PrefixMatches prefixMatches = new PrefixMatches();
        String[] strings = InputReader.getInputData();
        prefixMatches.add(strings);
        System.out.println(prefixMatches.size());
        prefixMatches.add("pooooooooooossssssssssssssssss");
        System.out.println(prefixMatches.size());
        String[] strings1 = {"looajhsdawfahgwvad", "ajhwvdawagwdanwbahw", null, "ahwagwawgdw hgwhawiwoaiuwfda asjhdhawkjghahwdjhg"};
        prefixMatches.add(strings1);
        System.out.println(prefixMatches.size());
        prefixMatches.add("sasda");
        System.out.println(prefixMatches.contains(null));
    }
}
