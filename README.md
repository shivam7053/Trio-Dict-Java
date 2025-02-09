# TrioDictionary

**TrioDictionary** is a custom data structure introduced by Shivam Koli using Java. It is designed to manage a collection of entries, each consisting of a unique ID, a description, and a briefing. The data structure supports various operations, including insertion, removal, searching, updating, and more, with thread-safe capabilities.

## Features

### 1. Entry Management
- **Insert Entries**: Add new entries with a unique ID, description, and briefing.
- **Remove Entries**: Remove entries by their unique ID.
- **Update Entries**: Update the description and briefing of existing entries.

### 2. Thread-Safe Operations
- **Thread-Safe Insert**: Safely insert entries in a multi-threaded environment.
- **Thread-Safe Remove**: Safely remove entries in a multi-threaded environment.

### 3. Data Retrieval
- **Get All IDs**: Retrieve a list of all entry IDs.
- **Get Description**: Retrieve the description of an entry by its ID.
- **Get Briefing**: Retrieve the briefing of an entry by its ID.
- **Contains**: Check if an entry with a specific ID exists.

### 4. Searching and Sorting
- **Search by Keyword**: Search for entries containing a specific keyword in their description or briefing.
- **Find Closest ID**: Find the entry ID closest to a given target ID.
- **Get Sorted IDs**: Retrieve all entry IDs sorted in ascending or descending order.
- **Find IDs by Prefix**: Find entry IDs where the description or briefing starts with a given prefix.

### 5. Batch Operations
- **Batch Insert**: Insert multiple entries efficiently.
- **Optimize**: Optimize memory usage by removing duplicate or redundant entries.

### 6. JSON Support
- **Export to JSON**: Export the dictionary to a JSON string.
- **Import from JSON**: Import entries from a JSON-like string.

### 7. Miscellaneous
- **Print Entries**: Print all entries in a structured format.
- **Get Random Entry**: Retrieve a random entry from the dictionary.
- **Clear**: Clear all entries from the dictionary.
- **Size**: Get the size of the dictionary.
- **Is Empty**: Check if the dictionary is empty.

## Usage

### Compilation
Navigate to the `src` directory and compile the Java files:
```sh
cd src
javac com/threesisters/dictionary/*.java
Running the Test File
Run the TestThreeSistersDictionary class to test the functionality:

java -cp . com.threesisters.dictionary.TestThreeSistersDictionary
Creating a JAR File
Create a JAR file for easy integration with other programs:

jar cfm ThreeSistersDictionary.jar manifest.txt com/threesisters/dictionary/*.class
Using the JAR File
Include the JAR file in the classpath when compiling and running your Java programs:

javac -cp ThreeSistersDictionary.jar YourProgram.java
java -cp .:ThreeSistersDictionary.jar YourProgram
Author
Shivam Koli

License
This project is licensed under the MIT License.