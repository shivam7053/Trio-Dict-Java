package com.threesisters.dictionary;

public class TestThreeSistersDictionary {
    public static void main(String[] args) {
        // Create an instance of ThreeSistersDictionary
        ThreeSistersDictionary dictionary = new ThreeSistersDictionary();

        System.out.println("🔹 TEST 1: Inserting Entries...");
        dictionary.insert(101, "Lion", "A wild carnivorous animal");
        dictionary.insert(102, "Eagle", "A powerful bird of prey");
        dictionary.insert(103, "Shark", "A large marine predator");

        System.out.println("✅ Total Entries After Insert: " + dictionary.size());

        System.out.println("\n🔹 TEST 2: Retrieving Data...");
        System.out.println("Description of ID 102: " + dictionary.getDescription(102));
        System.out.println("Briefing of ID 103: " + dictionary.getBriefing(103));

        System.out.println("\n🔹 TEST 3: Checking Existence...");
        System.out.println("Contains ID 101? " + dictionary.contains(101));
        System.out.println("Contains ID 200? " + dictionary.contains(200));

        System.out.println("\n🔹 TEST 4: Removing Entry...");
        dictionary.remove(102);
        System.out.println("✅ Total Entries After Removal: " + dictionary.size());

        System.out.println("\n🔹 TEST 5: Running Thread-Safe Inserts and Removes...");
        Thread thread1 = new Thread(() -> {
            dictionary.threadSafeInsert(201, "Elephant", "A large mammal");
            dictionary.threadSafeInsert(202, "Whale", "The largest sea creature");
        });

        Thread thread2 = new Thread(() -> {
            dictionary.threadSafeRemove(101);
            dictionary.threadSafeRemove(202);
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("✅ Final Dictionary Size: " + dictionary.size());

        System.out.println("\n🔹 TEST 6: Printing All Entries...");
        dictionary.printEntries();
    }
}
