# TrioDictionaryProject

## Overview
The TrioDictionaryProject is a Java-based library that provides a simple dictionary data structure. This dictionary allows users to store entries with descriptions and briefings, and includes various features such as adding, removing, updating, searching, sorting, and JSON handling.


## Features
- **Add Entry**: Add a new entry to the dictionary.
- **Remove Entry**: Remove an entry from the dictionary.
- **Update Entry**: Update an existing entry in the dictionary.
- **Get Methods**: Retrieve descriptions, briefings, or full entries by ID.
- **Utility Methods**: Check if an ID exists, get the size of the dictionary, and check if the dictionary is empty.
- **Display Methods**: Display all entries or get all IDs.
- **Sorting Methods**: Sort entries by ID, description, or briefing.
- **Search Methods**: Search entries by description or briefing.
- **JSON Handling**: Convert the dictionary to and from JSON, and save/load JSON from files.
- **Extra Utility**: Clear all entries and get entries as a list.

## How to Use

### Adding the Library to Your Project

1. **Compile the Code**

   ```sh
   javac -d build src/main/java/com/shivamkoli/triodictionary/*.java
Create the JAR File

jar cvf TrioDictionary.jar -C build/ .
Add the JAR to Your Project

Include the TrioDictionary.jar in your project's classpath.

Example Usage
Here's an example of how to use the TrioDictionary class in your project:

import com.shivamkoli.triodictionary.TrioDictionary;
import com.shivamkoli.triodictionary.TrioEntry;

import java.util.List;

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
Detailed Feature Descriptions
Add Entry
public void addEntry(String id, String description, String briefing)
Adds a new entry to the dictionary with the given ID, description, and briefing.

Remove Entry
public void removeEntry(String id)
Removes the entry with the given ID from the dictionary.

Update Entry
public boolean updateEntry(String id, String newDescription, String newBriefing)
Updates the entry with the given ID to have the new description and briefing. Returns true if the entry was updated, false if the ID does not exist.

Get Methods
public String getDescription(String id)
public String getBriefing(String id)
public String getFullEntry(String id)
Retrieves the description, briefing, or full entry (ID + description + briefing) for the given ID.

Utility Methods
public boolean containsId(String id)
public int size()
public boolean isEmpty()
Checks if the dictionary contains the given ID, gets the size of the dictionary, or checks if the dictionary is empty.

Display Methods
public List<String> getAllIds()
public void displayAllEntries()
Gets a list of all IDs in the dictionary or displays all entries.

Sorting Methods
public List<Map.Entry<String, TrioEntry>> sortById()
public List<Map.Entry<String, TrioEntry>> sortByDescription()
public List<Map.Entry<String, TrioEntry>> sortByBriefing()
Sorts the entries by ID, description, or briefing.

Search Methods
public List<String> searchByDescription(String keyword)
public List<String> searchByBriefing(String keyword)
public List<String> advancedSearch(String keyword)
Searches for entries by description or briefing, or performs an advanced search that checks both fields.

JSON Handling
public String toJson()
public void fromJson(String json)
public void saveToJsonFile(String filePath) throws IOException
public void loadFromJsonFile(String filePath) throws IOException
Converts the dictionary to and from JSON, and saves/loads JSON from files.

Extra Utility
public void clearAll()
public List<Map.Entry<String, TrioEntry>> getEntriesAsList()
Clears all entries from the dictionary or gets the entries as a list.

License
This project is licensed under the MIT License - see the LICENSE file for details.

Contributing
Contributions are welcome! Please open an issue or submit a pull request.

Contact
For any questions or feedback, please contact [your-email@example.com].


This `README.md` file provides detailed information about the project, its features, and how users can use it. Let me know if you need any further assistance!