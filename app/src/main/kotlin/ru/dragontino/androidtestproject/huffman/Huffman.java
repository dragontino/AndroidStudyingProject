package ru.dragontino.androidtestproject.huffman;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.HashMap;
import java.util.UUID;
import ru.dragontino.androidtestproject.huffman.node.HuffmanNode;


public class Huffman {
    private final HashMap<UUID, HuffmanNode> cache;

    public Huffman() {
        this.cache = new HashMap<>();
    }

    public HuffmanResult encode(@NotNull String text) {
        return encode(text, null);
    }

    @Nullable
    public HuffmanResult encode(@NotNull String text, @Nullable UUID id) {
        if (id == null || !cache.containsKey(id)) {
            id = UUID.randomUUID();
            var root = TreeGenerator.generateFromText(text);
            cache.put(id, root);
        }

        var treeRoot = cache.get(id);
        if (treeRoot == null) {
            return null;
        }
        var resultBytes = HuffmanEncoder.encode(text, treeRoot);
        return new HuffmanResult(id, resultBytes);
    }

    @Nullable
    public String decode(@NotNull HuffmanResult encodedData) {
        return decode(encodedData.getId(), encodedData.getBytes());
    }

    @Nullable
    public String decode(@NotNull UUID encodedResultId, byte @NotNull [] encodedBytes) {
        var treeRoot = cache.get(encodedResultId);
        if (treeRoot == null) {
            return null;
        } else {
            return HuffmanDecoder.decode(encodedBytes, treeRoot);
        }
    }

    public void clear() {
        cache.clear();
    }
}
