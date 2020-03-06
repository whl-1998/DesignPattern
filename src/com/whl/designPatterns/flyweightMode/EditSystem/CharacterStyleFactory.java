package com.whl.designPatterns.flyweightMode.EditSystem;

import javafx.scene.effect.SepiaTone;

import java.util.*;

/**
 * @author whl
 * @version V1.0
 * @Title: 享元工厂
 * @Description:
 */
public class CharacterStyleFactory {
    private static final Map<Integer, CharacterStyle> styles = new HashMap<>();

    public static CharacterStyle getStyle(Font font, int size, int colorRGB) {
        //key = font的哈希值 + size + colorRGB 以保证哈希值唯一性, 同时也避免了重复创建CharacterStyle的开销
        int key = font.hashCode() + size + colorRGB;
        if (styles.containsKey(key)) {
            return styles.get(key);
        }
        CharacterStyle newStyle = new CharacterStyle(font, size, colorRGB);
        styles.put(key, newStyle);
        return newStyle;
    }
}
