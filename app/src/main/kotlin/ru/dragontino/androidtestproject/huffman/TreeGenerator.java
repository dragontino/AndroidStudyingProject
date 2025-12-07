package ru.dragontino.androidtestproject.huffman;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.TreeMap;

import ru.dragontino.androidtestproject.huffman.node.HuffmanLeaf;
import ru.dragontino.androidtestproject.huffman.node.HuffmanNode;

public class TreeGenerator {
    @Nullable
    public static HuffmanNode generateFromText(@NotNull String text) {
        if (text.length() == 1) {
            var leaf = new HuffmanLeaf(1, text.charAt(0));
            return new HuffmanNode(1, leaf, null);
        }

        var frequencies = calculateFrequenciesFromText(text);
        var nodesQueue = new PriorityQueue<HuffmanNode>();

        for (var entry : frequencies.entrySet()) {
            var leaf = new HuffmanLeaf(entry.getValue(), entry.getKey());
            nodesQueue.add(leaf);
        }

        while (nodesQueue.size() > 1) {
            var left = Objects.requireNonNull(nodesQueue.poll());
            var right = Objects.requireNonNull(nodesQueue.poll());
            var frequenciesSum = left.getFrequency() + right.getFrequency();
            nodesQueue.add(new HuffmanNode(frequenciesSum, left, right));
        }

        return nodesQueue.poll();
    }

    private static Map<Character, Integer> calculateFrequenciesFromText(String text) {
        if (text.isEmpty()) return Collections.emptyMap();

        var frequenciesMap = new TreeMap<Character, Integer>(Comparator.naturalOrder());
        for (var i = 0; i < text.length(); i++) {
            var c = text.charAt(i);
            frequenciesMap.compute(c, (ch, freq) -> freq == null ? 1 : freq + 1);
        }
        return frequenciesMap;
    }
}
