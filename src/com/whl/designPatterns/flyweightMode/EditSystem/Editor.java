package com.whl.designPatterns.flyweightMode.EditSystem;

import java.util.ArrayList;
import java.util.List;

/**
 * @author whl
 * @version V1.0
 * @Title: 编辑器
 * @Description:
 */
public class Editor {
    private List<Character> chars = new ArrayList<>();

    public void appendCharacter(char c, Font font, int size, int colorRGB) {
        Character character = new Character(c, CharacterStyleFactory.getStyle(font, size, colorRGB));
        chars.add(character);
    }

    public static void main(String[] args) {
        Editor ed = new Editor();
        Font f1 = new Font();
        ed.appendCharacter('c', f1, 1, 1);
        ed.appendCharacter('b', f1, 1, 1);
    }
}
