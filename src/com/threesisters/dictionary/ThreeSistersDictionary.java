package com.threesisters.dictionary;

import java.util.*;

public class ThreeSistersDictionary {

    static class Node {
        Entry entry;
        Node next;

        Node(Entry entry) {
            this.entry = entry;
            this.next = null;
        }
    }

    private Node head;
    private int size;
    private final Random random;
    private final Object lock; // Synchronization lock for thread safety

    public ThreeSistersDictionary() {
        this.head = null;
        this.size = 0;
        this.random = new Random();
        this.lock = new Object();
    }

    /**
     * Thread-safe insertion of a new entry.
     */
    public synchronized void threadSafeInsert(int id, String description, String briefing) {
        synchronized (lock) {
            if (contains(id)) return; // Avoid duplicate insertion

            Node newNode = new Node(new Entry(id, description, briefing));
            newNode.next = head;
            head = newNode;
            size++;
        }
    }

    /**
     * Thread-safe removal of an entry by ID.
     */
    public synchronized boolean threadSafeRemove(int id) {
        synchronized (lock) {
            if (head == null) return false;

            if (head.entry.getId() == id) { // If head is to be removed
                head = head.next;
                size--;
                return true;
            }

            Node current = head;
            while (current.next != null) {
                if (current.next.entry.getId() == id) {
                    current.next = current.next.next;
                    size--;
                    return true;
                }
                current = current.next;
            }
            return false;
        }
    }

    /**
     * Returns a list of all IDs in the dictionary.
     */
    public List<Integer> getAllIds() {
        List<Integer> ids = new ArrayList<>();
        Node current = head;
        while (current != null) {
            ids.add(current.entry.getId());
            current = current.next;
        }
        return ids;
    }

    /**
     * Searches for entries containing a specific keyword.
     */
    public List<String> searchByKeyword(String keyword) {
        List<String> results = new ArrayList<>();
        Node current = head;
        while (current != null) {
            if (current.entry.getDescription().contains(keyword) || 
                current.entry.getBriefing().contains(keyword)) {
                results.add(current.entry.toString());
            }
            current = current.next;
        }
        return results;
    }

    /**
     * Inserts multiple entries efficiently.
     */
    public void batchInsert(Map<Integer, Entry> entries) {
        for (Map.Entry<Integer, Entry> e : entries.entrySet()) {
            insert(e.getKey(), e.getValue().getDescription(), e.getValue().getBriefing());
        }
    }

    /**
     * Updates the description of an entry.
     */
    public void updateDescription(int id, String newDescription) {
        Node current = head;
        while (current != null) {
            if (current.entry.getId() == id) {
                current.entry.setDescription(newDescription);
                return;
            }
            current = current.next;
        }
    }

    /**
     * Updates the briefing of an entry.
     */
    public void updateBriefing(int id, String newBriefing) {
        Node current = head;
        while (current != null) {
            if (current.entry.getId() == id) {
                current.entry.setBriefing(newBriefing);
                return;
            }
            current = current.next;
        }
    }

    /**
     * Optimizes memory usage by removing duplicate or redundant entries.
     */
    public void optimize() {
        Set<Integer> seenIds = new HashSet<>();
        Node current = head;
        Node prev = null;

        while (current != null) {
            if (seenIds.contains(current.entry.getId())) {
                prev.next = current.next; // Remove duplicate
                size--;
            } else {
                seenIds.add(current.entry.getId());
                prev = current;
            }
            current = current.next;
        }
    }

    /**
     * Returns a map of all IDs to descriptions.
     */
    public Map<Integer, String> getAllDescriptions() {
        Map<Integer, String> descriptions = new HashMap<>();
        Node current = head;
        while (current != null) {
            descriptions.put(current.entry.getId(), current.entry.getDescription());
            current = current.next;
        }
        return descriptions;
    }

    /**
     * Returns a map of all IDs to briefings.
     */
    public Map<Integer, String> getAllBriefings() {
        Map<Integer, String> briefings = new HashMap<>();
        Node current = head;
        while (current != null) {
            briefings.put(current.entry.getId(), current.entry.getBriefing());
            current = current.next;
        }
        return briefings;
    }

    /**
     * Exports dictionary to JSON string.
     */
    public String toJSON() {
        StringBuilder json = new StringBuilder("[");
        Node current = head;
        while (current != null) {
            json.append(String.format("{\"id\":%d,\"description\":\"%s\",\"briefing\":\"%s\"},",
                    current.entry.getId(), current.entry.getDescription(), current.entry.getBriefing()));
            current = current.next;
        }
        if (json.length() > 1) json.deleteCharAt(json.length() - 1); // Remove last comma
        json.append("]");
        return json.toString();
    }

    /**
     * Imports entries from a JSON-like string.
     */
    public void fromJSON(String json) {
        json = json.replace("[", "").replace("]", "").trim();
        if (json.isEmpty()) return;
        String[] entries = json.split("\\},\\{");
        for (String entry : entries) {
            entry = entry.replace("{", "").replace("}", "").replace("\"", "");
            String[] fields = entry.split(",");
            int id = Integer.parseInt(fields[0].split(":")[1]);
            String description = fields[1].split(":")[1];
            String briefing = fields[2].split(":")[1];
            insert(id, description, briefing);
        }
    }

    /**
     * Finds the closest ID to a given target.
     */
    public int findClosestId(int targetId) {
        int closestId = -1;
        int minDifference = Integer.MAX_VALUE;
        Node current = head;

        while (current != null) {
            int diff = Math.abs(current.entry.getId() - targetId);
            if (diff < minDifference) {
                minDifference = diff;
                closestId = current.entry.getId();
            }
            current = current.next;
        }
        return closestId;
    }

    /**
     * Returns all IDs sorted in ascending/descending order.
     */
    public List<Integer> getSortedIds(boolean ascending) {
        List<Integer> ids = getAllIds();
        if (ascending) {
            ids.sort(Integer::compareTo);
        } else {
            ids.sort(Collections.reverseOrder());
        }
        return ids;
    }

    /**
     * Prints all entries in a structured format.
     */
    public void printEntries() {
        Node current = head;
        while (current != null) {
            System.out.println(current.entry);
            current = current.next;
        }
    }

    /**
     * Returns a random entry from the dictionary.
     */
    public String getRandomEntry() {
        if (size == 0) return "No entries available.";
        int randomIndex = random.nextInt(size);
        Node current = head;
        for (int i = 0; i < randomIndex; i++) {
            current = current.next;
        }
        return current.entry.toString();
    }

    /**
     * Finds IDs where description or briefing starts with a given prefix.
     */
    public List<Integer> findIdsByPrefix(String prefix) {
        List<Integer> ids = new ArrayList<>();
        Node current = head;
        while (current != null) {
            if (current.entry.getDescription().startsWith(prefix) || 
                current.entry.getBriefing().startsWith(prefix)) {
                ids.add(current.entry.getId());
            }
            current = current.next;
        }
        return ids;
    }

    /**
     * Inserts a new entry into the dictionary.
     * @param id The ID of the entry
     * @param description The description of the entry
     * @param briefing The briefing of the entry
     */
    public void insert(int id, String description, String briefing) {
        Entry newEntry = new Entry(id, description, briefing);
        Node newNode = new Node(newEntry);
        newNode.next = head;
        head = newNode;
        size++;
    }

    /**
     * Removes an entry by ID.
     * @param id The ID to remove
     * @return True if removed, false if not found
     */
    public boolean remove(int id) {
        Node current = head, prev = null;

        while (current != null) {
            if (current.entry.getId() == id) {
                if (prev == null) {
                    head = current.next;
                } else {
                    prev.next = current.next;
                }
                size--;
                return true;
            }
            prev = current;
            current = current.next;
        }
        return false;
    }

    /**
     * Retrieves the description of an entry using ID.
     * @param id The ID to look up
     * @return The description, or null if not found
     */
    public String getDescription(int id) {
        Node current = head;
        while (current != null) {
            if (current.entry.getId() == id) {
                return current.entry.getDescription();
            }
             current = current.next;
        }
        return null;
    }

    /**
     * Retrieves the briefing of an entry using ID.
     * @param id The ID to look up
     * @return The briefing, or null if not found
     */
    public String getBriefing(int id) {
        Node current = head;
        while (current != null) {
            if (current.entry.getId() == id) {
                return current.entry.getBriefing();
            }
            current = current.next;
        }
        return null;
    }

    /**
     * Checks if an entry with the given ID exists.
     * @param id The ID to check
     * @return True if exists, otherwise false
     */
    public boolean contains(int id) {
        Node current = head;
        while (current != null) {
            if (current.entry.getId() == id) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * Clears all entries from the dictionary.
     */
    public void clear() {
        head = null;
        size = 0;
    }

    /**
     * Returns the size of the dictionary.
     */
    public int size() {
        return size;
    }

    /**
     * Checks if the dictionary is empty.
     */
    public boolean isEmpty() {
        return size == 0;
    }
}