package com.shivamkoli.triodictionary;

import java.io.*;
import java.util.*;


public class TrioDictionaryExample {
    public static void main(String[] args) {
        TrioDictionary trioDict = new TrioDictionary();

        // Adding entries
        trioDict.addEntry("101", "Java Basics", "Introduction to Java");
        trioDict.addEntry("102", "OOP Principles", "Encapsulation, Abstraction");
        trioDict.addEntry("103", "Data Structures", "HashMap, LinkedList");

        // Display all entries
        System.out.println("All Entries:");
        trioDict.displayAllEntries();

        // Search by description
        System.out.println("\nSearch by Description 'Java':");
        List<String> searchResults = trioDict.searchByDescription("Java");
        for (String id : searchResults) {
            System.out.println("Found ID: " + id + " - " + trioDict.getFullEntry(id));
        }

        // Update an entry
        System.out.println("\nUpdating Entry '102':");
        if (trioDict.updateEntry("102", "OOP Concepts", "Encapsulation, Abstraction, Inheritance, Polymorphism")) {
            System.out.println("Updated Entry: " + trioDict.getFullEntry("102"));
        } else {
            System.out.println("Entry '102' not found.");
        }

        // Remove an entry
        System.out.println("\nRemoving Entry '103':");
        trioDict.removeEntry("103");
        System.out.println("All Entries after removal:");
        trioDict.displayAllEntries();

        // Convert to JSON
        System.out.println("\nDictionary as JSON:");
        String jsonData = trioDict.toJson();
        System.out.println(jsonData);

        // Load from JSON
        System.out.println("\nLoading from JSON:");
        trioDict.fromJson(jsonData);
        System.out.println("All Entries after loading from JSON:");
        trioDict.displayAllEntries();
    }
}