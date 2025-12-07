package ru.dragontino.androidtestproject.huffman.node;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class HuffmanNode implements Comparable<HuffmanNode> {
    private int frequency;
    @Nullable
    private HuffmanNode left;
    @Nullable
    private HuffmanNode right;

    public HuffmanNode(int frequency, @Nullable HuffmanNode left, @Nullable HuffmanNode right) {
        setFrequency(frequency);
        setLeft(left);
        setRight(right);
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public @Nullable HuffmanNode getLeft() {
        return left;
    }

    public void setLeft(@Nullable HuffmanNode left) {
        this.left = left;
    }

    public @Nullable HuffmanNode getRight() {
        return right;
    }

    public void setRight(@Nullable HuffmanNode right) {
        this.right = right;
    }

    @Override
    public int compareTo(@NotNull HuffmanNode o) {
        return this.getFrequency() - o.getFrequency();
    }
}
