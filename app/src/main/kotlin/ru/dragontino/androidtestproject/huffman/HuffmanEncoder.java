package ru.dragontino.androidtestproject.huffman;


import org.jetbrains.annotations.NotNull;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import ru.dragontino.androidtestproject.huffman.node.HuffmanLeaf;
import ru.dragontino.androidtestproject.huffman.node.HuffmanNode;

class HuffmanEncoder {

    public static byte @NotNull [] encode(@NotNull String str, @NotNull HuffmanNode root) {
        if (root instanceof HuffmanLeaf) {
            return new byte[]{0};
        }

        var codesList = new LinkedList<Byte>();
        var codesMap = new HashMap<Character, byte[]>();
        fillCodesMap(codesList, root, codesMap);
        return applyCodesToText(codesMap, str.toCharArray());
    }

    private static void fillCodesMap(LinkedList<Byte> codes,
                                     HuffmanNode node,
                                     Map<Character, byte[]> codesMap) {
        if (node == null) {
            return;
        }

        if (node instanceof HuffmanLeaf leaf) {
            var resultCode = copyFromList(codes);
            codesMap.put(leaf.getValue(), resultCode);
        } else {
            codes.add((byte) 0);
            fillCodesMap(codes, node.getLeft(), codesMap);
            codes.removeLast();
            codes.add((byte) 1);
            fillCodesMap(codes, node.getRight(), codesMap);
            codes.removeLast();
        }
    }


    private static byte @NotNull [] applyCodesToText(Map<Character, byte[]> codesMap, char[] text) {
        var encodedText = new byte[text.length][];
        for (var i = 0; i < text.length; i++) {
            var code = codesMap.get(text[i]);
            encodedText[i] = code;
        }
        return flatten(encodedText);
    }


    private static byte[] flatten(byte[][] arrays) {
        int totalLength = 0;
        for (var arr : arrays) {
            totalLength += arr.length;
        }

        var resultArray = new byte[totalLength];
        int currentIndex = 0;
        for (var arr : arrays) {
            System.arraycopy(arr, 0, resultArray, currentIndex, arr.length);
            currentIndex += arr.length;
        }
        return resultArray;
    }


    private static byte[] copyFromList(List<Byte> source) {
        var result = new byte[source.size()];
        for (var i = 0; i < result.length; i++) {
            result[i] = source.get(i);
        }
        return result;
    }
}
