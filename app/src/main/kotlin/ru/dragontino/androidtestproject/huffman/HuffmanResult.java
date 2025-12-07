package ru.dragontino.androidtestproject.huffman;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class HuffmanResult {
    @NotNull
    private final UUID id;
    private byte @NotNull [] bytes;

    public HuffmanResult(@NotNull UUID id, byte @NotNull [] bytes) {
        this.id = id;
        setBytes(bytes);
    }

    public @NotNull UUID getId() {
        return id;
    }

    public byte @NotNull [] getBytes() {
        return bytes;
    }

    public void setBytes(byte @NotNull [] bytes) {
        this.bytes = bytes;
    }
}
