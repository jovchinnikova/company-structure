package com.solvd.companystructure;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class WordsCount {

    private static final Logger LOGGER = LogManager.getLogger(WordsCount.class);

    private File file;

    public WordsCount(File file) {
        this.file = file;
    }

    public void countWords() throws IOException {
        String text = FileUtils.readFileToString(file, String.valueOf(StandardCharsets.UTF_8));
        text = text.toLowerCase(Locale.ROOT);
        String delimiter = "[^a-z-’]+";
        String[] splitText = text.split(delimiter);
        List<String> words = Arrays.stream(splitText)
                .filter(word -> !word.isEmpty())
                .distinct()
                .collect(Collectors.toList());

        String finalText = text;
        Map<String, Integer> countedWords = words.stream()
                .collect(Collectors.toMap(word -> word, word -> StringUtils.countMatches(finalText, word)));

        Map<String, Integer> sortedWords = sortByComparator(countedWords);
        String fileMessage = sortedWords.entrySet().stream()
                .map(word -> word.getKey() + " " + word.getValue())
                .collect(Collectors.joining(System.lineSeparator()));
        LOGGER.info(fileMessage);
        File output = new File("src/main/resources/output.txt");
        FileUtils.writeStringToFile(output, fileMessage);
    }

    private static Map<String, Integer> sortByComparator(Map<String, Integer> countedWords) {
        List<Map.Entry<String, Integer>> list = new LinkedList<>(countedWords.entrySet());
        Comparator<Map.Entry<String, Integer>> wordsCompare = new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        };

        Map<String, Integer> sortedWords = new LinkedHashMap<>();
        list.stream()
                .sorted(wordsCompare)
                .forEach(word -> sortedWords.put(word.getKey(), word.getValue()));
        return sortedWords;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
