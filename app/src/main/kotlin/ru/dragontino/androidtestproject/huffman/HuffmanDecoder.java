package ru.dragontino.androidtestproject.huffman;

import org.jetbrains.annotations.NotNull;
import java.util.Objects;
import ru.dragontino.androidtestproject.huffman.node.HuffmanLeaf;
import ru.dragontino.androidtestproject.huffman.node.HuffmanNode;

class HuffmanDecoder {
    @NotNull
    static String decode(byte @NotNull [] encodedBytes, @NotNull HuffmanNode root) {
        var resultBuilder = new StringBuilder();
        var currentNode = root;
        var index = 0;
        while (index < encodedBytes.length) {
            while (!(currentNode instanceof HuffmanLeaf) && index < encodedBytes.length) {
                var currentByte = encodedBytes[index];
                if (currentByte == 0) {
                    currentNode = Objects.requireNonNull(currentNode.getLeft());
                } else {
                    currentNode = Objects.requireNonNull(currentNode.getRight());
                }
                index++;
            }
            if (currentNode instanceof HuffmanLeaf leaf) {
                resultBuilder.append(leaf.getValue());
                currentNode = root;
            }
        }

        return resultBuilder.toString();
    }
}
