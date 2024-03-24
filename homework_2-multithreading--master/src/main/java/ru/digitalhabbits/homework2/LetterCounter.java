package ru.digitalhabbits.homework2;

import java.util.Map;

/**
 * Counter characters in given string
 */
public interface LetterCounter {

    Map<Character, Long> count(String input);

}
