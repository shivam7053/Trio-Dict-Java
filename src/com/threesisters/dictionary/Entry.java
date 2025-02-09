package com.threesisters.dictionary;

/**
 * Represents an entry in the Three Sisters Dictionary.
 * Each entry consists of a unique ID, a description, and a briefing.
 */
public class Entry {
    private final int id;      // Unique identifier
    private String description;  // Detailed description (modifiable)
    private String briefing;     // Short summary (modifiable)

    /**
     * Constructor to initialize an Entry object.
     *
     * @param id          Unique identifier
     * @param description Detailed description of the entry
     * @param briefing    Short summary
     */
    public Entry(int id, String description, String briefing) {
        if (description == null || briefing == null) {
            throw new IllegalArgumentException("Description and briefing cannot be null");
        }
        this.id = id;
        this.description = description;
        this.briefing = briefing;
    }

    // ✅ Getter for ID (Remains final)
    public int getId() {
        return id;
    }

    // ✅ Getter for Description
    public String getDescription() {
        return description;
    }

    // ✅ Setter for Description (Now works properly)
    public void setDescription(String newDescription) {
        if (newDescription == null || newDescription.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be empty.");
        }
        this.description = newDescription;
    }

    // ✅ Getter for Briefing
    public String getBriefing() {
        return briefing;
    }

    // ✅ Setter for Briefing (Now works properly)
    public void setBriefing(String newBriefing) {
        if (newBriefing == null || newBriefing.trim().isEmpty()) {
            throw new IllegalArgumentException("Briefing cannot be empty.");
        }
        this.briefing = newBriefing;
    }

    // ✅ Override equals for object comparison
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Entry entry = (Entry) obj;
        return id == entry.id &&
               description.equals(entry.description) &&
               briefing.equals(entry.briefing);
    }

    // ✅ Override hashCode for use in hash-based collections
    @Override
    public int hashCode() {
        int result = Integer.hashCode(id);
        result = 31 * result + description.hashCode();
        result = 31 * result + briefing.hashCode();
        return result;
    }

    // ✅ Override toString for easy debugging
    @Override
    public String toString() {
        return "Entry{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", briefing='" + briefing + '\'' +
                '}';
    }
}