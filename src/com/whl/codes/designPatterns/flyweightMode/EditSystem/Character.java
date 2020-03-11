package com.whl.designPatterns.flyweightMode.EditSystem;

/**
 * @author whl
 * @version V1.0
 * @Title: 字符
 * @Description:
 */
public class Character {
    private char c;
    private CharacterStyle style;

    public Character(char c, CharacterStyle style) {
        this.c = c;
        this.style = style;
    }
}
