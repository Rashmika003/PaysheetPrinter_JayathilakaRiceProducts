package com.jayathilakaRiceProducts.JayathilakaPosBackedWithPrinter.printPdfWithTxtInsert;

public class TextItem {

    private String text;
    private int x;
    private int y;
    private int fontSize;

    public TextItem(String text, int x, int y, int fontSize) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.fontSize = fontSize;
    }

    public TextItem() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }
}
