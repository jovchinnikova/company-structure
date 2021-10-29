package com.solvd.companystructure;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

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
        List<String> words = new ArrayList<>();
        for (String word : text.split(delimiter)) {
            if (!words.contains(word) & !word.isEmpty()) {
                words.add(word);
            }
        }

        Map<String, Integer> countedWords = new HashMap<>();
        for (String word : words) {
            Integer quantity = StringUtils.countMatches(text, word);
            countedWords.put(word, quantity);
        }

        Map<String, Integer> sortedWords = WordsCount.sortByComparator(countedWords);
        String fileMessage = "";
        for (Map.Entry<String, Integer> word : sortedWords.entrySet()) {
            String message = word.getKey() + " " + word.getValue();
            LOGGER.info(message);
            fileMessage += message + System.lineSeparator();
        }
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

        Collections.sort(list, wordsCompare);
        Map<String, Integer> sortedWords = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> word : list) {
            sortedWords.put(word.getKey(), word.getValue());
        }
        return sortedWords;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
