package ru.otus;

public enum Banknote {
    BANKNOTE_50(50),
    BANKNOTE_100(100),
    BANKNOTE_500(500),
    BANKNOTE_1000(1000),
    BANKNOTE_5000(5000);

    private final int value;

    Banknote(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}