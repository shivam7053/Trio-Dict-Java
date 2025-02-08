package com.shivamkoli.triodictionary;


public class TrioEntry {
    String description;
    String briefing;

    public TrioEntry(String description, String briefing) {
        this.description = description;
        this.briefing = briefing;
    }

    @Override
    public String toString() {
        return "{" + description + ": " + briefing + "}";
    }
}