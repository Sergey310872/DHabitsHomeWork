package ru.digitalhabbits.homework2.impl;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import ru.digitalhabbits.homework2.FileLetterCounter;
import ru.digitalhabbits.homework2.LetterCountMerger;
import ru.digitalhabbits.homework2.LetterCounter;

//todo Make your impl
public class AsyncFileLetterCounter implements FileLetterCounter {
    private final LetterCountMerger letterCountMerger = new LetterCountMerger() {
        @Override
        public Map<Character, Long> merge(Map<Character, Long> first, Map<Character, Long> second) {
            second.forEach((k,v) -> first.merge(k, v, Long::sum));
            return first;
        }
    };
    private final LetterCounter letterCounter = new LetterCounter() {
        @Override
        public Map<Character, Long> count(String input) {
            Map<Character, Long> characterLongMap = new HashMap<>();
            for (Character ch : input.toCharArray()) {
                if (characterLongMap.containsKey(ch)) {
                    characterLongMap.put(ch, (characterLongMap.get(ch) + 1L));
                } else {
                    characterLongMap.put(ch, 1L);
                }
            }

            return characterLongMap;
        }
    };
    @Override
    public Map<Character, Long> count(File input) {
        Stream<String> streamStr = new Reader().readLines(input);
        List<Map<Character, Long>> listMap = streamStr.parallel().map(s -> letterCounter.count(s)).collect(Collectors.toList());
        Map<Character, Long> result = listMap.stream().parallel().reduce((a1,b1) -> letterCountMerger.merge(a1,b1)).get();
        System.out.println(result);
        //todo
        return result;//Collections.emptyMap();
    }
}
