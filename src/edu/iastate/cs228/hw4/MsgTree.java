package edu.iastate.cs228.hw4;

import java.util.ArrayList;
import java.util.List;

/*
    @author Rafat Momin
 */
public class MsgTree {
    public char payloadChar;
    public MsgTree left, right;

    private static int index = 0;


    public MsgTree(String encoding) {
        if (index < encoding.length()) {
            char ch = encoding.charAt(index++);
            if (ch == '^') {
                this.payloadChar = '\0';
                this.left = new MsgTree(encoding);
                this.right = new MsgTree(encoding);
            } else {
                this.payloadChar = ch;
                this.left = null;
                this.right = null;
            }
        }
    }


    public static void printCodes(MsgTree node, String code) {
        if (node != null) {
            if (node.left == null && node.right == null) {
                System.out.println(node.payloadChar + "  " + code);
            }
            printCodes(node.left, code + "0");
            printCodes(node.right, code + "1");
        }
    }

    // Decode method as before
    public void decode(MsgTree codes, String msg) {
        MsgTree current = codes;
        StringBuilder decodedMessage = new StringBuilder();
        for (char bit : msg.toCharArray()) {
            current = bit == '0' ? current.left : current.right;
            assert current != null;
            if (current.left == null && current.right == null) {
                decodedMessage.append(current.payloadChar);
                current = codes; // Reset to start of tree for next character
            }
        }
        System.out.println("MESSAGE:\n" + decodedMessage.toString());
    }

    public void showTree() {
        showTree("", true);
    }

    public void showTree(String prefix, boolean isTail) {
        System.out.println(prefix + (isTail ? "└─ " : "├─ ") + (payloadChar == '\0' ? "^" : payloadChar));
        List<MsgTree> children = new ArrayList<>();
        if (this.left != null) children.add(this.left);
        if (this.right != null) children.add(this.right);
        for (int i = 0; i < children.size() - 1; i++) {
            children.get(i).showTree(prefix + (isTail ? "    " : "│   "), false);
        }
        if (!children.isEmpty()) {
            children.get(children.size() - 1).showTree(prefix + (isTail ? "    " : "│   "), true);
        }
    }
}
