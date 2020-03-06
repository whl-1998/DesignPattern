package com.whl.designPatterns.flyweightMode.EditSystem;

/**
 * @author whl
 * @version V1.0
 * @Title: 字符格式
 * @Description:
 */
public class CharacterStyle {
    private Font font;
    private int size;
    private int colorRGB;

    public CharacterStyle(Font font, int size, int colorRGB) {
        this.font = font;
        this.size = size;
        this.colorRGB = colorRGB;
    }

    public boolean equals(Font font, int size, int colorRGB) {
        return this.font.equals(font) && this.size == size && this.colorRGB == colorRGB;
    }
}
