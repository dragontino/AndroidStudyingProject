package ru.dragontino.androidtestproject.huffman.node;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class HuffmanLeaf extends HuffmanNode {
    private char value;
    public HuffmanLeaf(int frequency, char value) {
        super(frequency, null, null);
        setValue(value);
    }

    public char getValue() {
        return value;
    }
    public void setValue(char value) {
        this.value = value;
    }

    @Override
    public @Nullable HuffmanNode getLeft() {
        return null;
    }

    @Override
    public void setLeft(@Nullable HuffmanNode left) {
        super.setLeft(null);
    }

    @Override
    public @Nullable HuffmanNode getRight() {
        return null;
    }

    @Override
    public void setRight(@Nullable HuffmanNode right) {
        super.setRight(null);
    }

    @Override
    public int compareTo(@NotNull HuffmanNode o) {
        var parentComparison = super.compareTo(o);
        if (o instanceof HuffmanLeaf l) {
            return parentComparison == 0 ? l.getValue() - this.getValue() : parentComparison;
        } else {
            return parentComparison;
        }
    }
}
