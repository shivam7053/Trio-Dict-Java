package com.shivamkoli.triodictionary;

import java.io.*;
import java.util.*;

public class TrioDictionary {
    private final Map<String, TrioEntry> dictionary;

    public TrioDictionary() {
        this.dictionary = new HashMap<>();
    }

    // 1. Add Entry
    public void addEntry(String id, String description, String briefing) {
        dictionary.put(id, new TrioEntry(description, briefing));
    }

    // 2. Remove Entry
    public void removeEntry(String id) {
        dictionary.remove(id);
    }

    // 3. Update Entry
    public boolean updateEntry(String id, String newDescription, String newBriefing) {
        if (dictionary.containsKey(id)) {
            dictionary.put(id, new TrioEntry(newDescription, newBriefing));
            return true;
        }
        return false;
    }

    // 4. Get Methods
    public String getDescription(String id) {
        return dictionary.containsKey(id) ? dictionary.get(id).description : null;
    }

    public String getBriefing(String id) {
        return dictionary.containsKey(id) ? dictionary.get(id).briefing : null;
    }

    public String getFullEntry(String id) {
        return dictionary.containsKey(id) ? id + dictionary.get(id) : null;
    }

    // 5. Utility Methods
    public boolean containsId(String id) {
        return dictionary.containsKey(id);
    }

    public int size() {
        return dictionary.size();
    }

    public boolean isEmpty() {
        return dictionary.isEmpty();
    }

    // 6. Display Methods
    public List<String> getAllIds() {
        return new ArrayList<>(dictionary.keySet());
    }

    public void displayAllEntries() {
        for (Map.Entry<String, TrioEntry> entry : dictionary.entrySet()) {
            System.out.println(entry.getKey() + entry.getValue());
        }
    }

    // 7. Sorting Methods
    public List<Map.Entry<String, TrioEntry>> sortById() {
        List<Map.Entry<String, TrioEntry>> entries = new ArrayList<>(dictionary.entrySet());
        entries.sort(Comparator.comparing(Map.Entry::getKey));
        return entries;
    }

    public List<Map.Entry<String, TrioEntry>> sortByDescription() {
        List<Map.Entry<String, TrioEntry>> entries = new ArrayList<>(dictionary.entrySet());
        entries.sort(Comparator.comparing(e -> e.getValue().description));
        return entries;
    }

    public List<Map.Entry<String, TrioEntry>> sortByBriefing() {
        List<Map.Entry<String, TrioEntry>> entries = new ArrayList<>(dictionary.entrySet());
        entries.sort(Comparator.comparing(e -> e.getValue().briefing));
        return entries;
    }

    // 8. Search Methods
    public List<String> searchByDescription(String keyword) {
        List<String> result = new ArrayList<>();
        for (Map.Entry<String, TrioEntry> entry : dictionary.entrySet()) {
            if (entry.getValue().description.contains(keyword)) {
                result.add(entry.getKey());
            }
        }
        return result;
    }

    public List<String> searchByBriefing(String keyword) {
        List<String> result = new ArrayList<>();
        for (Map.Entry<String, TrioEntry> entry : dictionary.entrySet()) {
            if (entry.getValue().briefing.contains(keyword)) {
                result.add(entry.getKey());
            }
        }
        return result;
    }

    public List<String> advancedSearch(String keyword) {
        List<String> result = new ArrayList<>();
        for (Map.Entry<String, TrioEntry> entry : dictionary.entrySet()) {
            if (entry.getValue().description.contains(keyword) || entry.getValue().briefing.contains(keyword)) {
                result.add(entry.getKey());
            }
        }
        return result;
    }

    // 9. JSON Handling (Manual)
    public String toJson() {
        StringBuilder json = new StringBuilder("{");
        for (Map.Entry<String, TrioEntry> entry : dictionary.entrySet()) {
            json.append("\"").append(entry.getKey()).append("\":{\"description\":\"")
                .append(entry.getValue().description).append("\",\"briefing\":\"")
                .append(entry.getValue().briefing).append("\"},");
        }
        if (json.length() > 1) {
            json.setLength(json.length() - 1); // Remove last comma
        }
        json.append("}");
        return json.toString();
    }

    public void fromJson(String json) {
        dictionary.clear();
        json = json.substring(1, json.length() - 1); // Remove curly braces
        String[] entries = json.split("},");
        for (String entry : entries) {
            entry = entry.replace("{", "").replace("}", "");
            String[] parts = entry.split(":\\{\"description\":\"");
            String id = parts[0].replace("\"", "");
            String[] descBrief = parts[1].split("\",\"briefing\":\"");
            String description = descBrief[0];
            String briefing = descBrief[1].replace("\"", "");
            dictionary.put(id, new TrioEntry(description, briefing));
        }
    }

    public void saveToJsonFile(String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(toJson());
        }
    }

    public void loadFromJsonFile(String filePath) throws IOException {
        StringBuilder json = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                json.append(line);
            }
        }
        fromJson(json.toString());
    }

    // 10. Extra Utility
    public void clearAll() {
        dictionary.clear();
    }

    public List<Map.Entry<String, TrioEntry>> getEntriesAsList() {
        return new ArrayList<>(dictionary.entrySet());
    }
}