import java.util.Random;

public class MyTestingClass {
    private int id;
    private String name;

    public MyTestingClass(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Custom hashCode — does NOT use Objects.hash() or default hashing
    // Uses polynomial rolling hash on the name + id mixing
    @Override
    public int hashCode() {
        int hash = 0;
        int prime = 31;
        // Hash the name characters manually
        for (int i = 0; i < name.length(); i++) {
            hash = hash * prime + name.charAt(i);
        }
        // Mix in the id using a bit-spreading technique
        hash = hash ^ (id * 2654435761); // Knuth multiplicative hash
        return Math.abs(hash);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof MyTestingClass)) return false;
        MyTestingClass other = (MyTestingClass) obj;
        return this.id == other.id && this.name.equals(other.name);
    }

    @Override
    public String toString() {
        return "MyTestingClass{id=" + id + ", name='" + name + "'}";
    }

    // ---- Testing (Part 1.2) ----
    public static void main(String[] args) {
        // Use a large M for better distribution visibility
        MyHashTable<MyTestingClass, Student> table = new MyHashTable<>(1000);

        Random rand = new Random();
        String[] names = {"Alice", "Bob", "Charlie", "Diana", "Eve",
                          "Frank", "Grace", "Hank", "Ivy", "Jack"};

        // Add 10000 random elements
        for (int i = 0; i < 10000; i++) {
            int id = rand.nextInt(100000);
            String name = names[rand.nextInt(names.length)] + rand.nextInt(1000);
            MyTestingClass key = new MyTestingClass(id, name);
            Student value = new Student("Student" + i, rand.nextInt(100));
            table.put(key, value);
        }

        System.out.println("Total size: " + table.size());
        System.out.println("\nBucket distribution:");
        table.printBucketSizes();
    }
}

// Simple Student class used as value
class Student {
    String name;
    int grade;

    public Student(String name, int grade) {
        this.name = name;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return name + "(" + grade + ")";
    }
}
