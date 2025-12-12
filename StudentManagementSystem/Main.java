import java.util.*;

class Student {
    private String id, name;
    private int marks;

    public Student(String id, String name, int marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    public String getId() {
        return id;
    }

    public int getMarks() {
        return marks;
    }

    public int getEffectiveMarks() {
        return marks;
    }

    public String getrole() {
        return "Undergraduate";
    }

    @Override
    public String toString() {
        return id + " - " + name + " (" + marks + ")" + " - " + getrole();
    }
}

class HonourStudent extends Student {
    private int bonusMarks;

    public HonourStudent(String id, String name, int marks, int bonusMarks) {
        super(id, name, marks);
        this.bonusMarks = bonusMarks;
    }

    @Override
    public String getrole() {
        return "Honour (bonus " + bonusMarks + ")";
    }

    @Override
    public int getEffectiveMarks() {
        return Math.min(100, super.getMarks() + bonusMarks);
    }
}

class GraduateStudent extends Student {
    private String area;

    public GraduateStudent(String id, String name, int marks, String area) {
        super(id, name, marks);
        this.area = area;
    }

    public String getrole() {
        return "Graduate in (" + area + ")";
    }
    
}

class Repository<T> {
    private Map<String, T> data = new HashMap<>();

    public void save(String id, T obj) {
        data.put(id, obj);
    }

    public T find(String id) {
        return data.get(id);
    }

    public void delete(String id) {
        data.remove(id);
    }

}

public class Main {
    public static void main(String[] args) {
        List<Student> list = new ArrayList<>();
        list.add(new Student("S1", "Akku", 85));
        list.add(new Student("S2", "Balwinder", 78));
        list.add(new Student("S3", "Chant", 90));

        list.add(new GraduateStudent("G1", "Danger", 88, "Computer Science"));

        list.add(new HonourStudent("H1", "Eleven", 92, 10));

        Repository<Student> repo = new Repository<>();
        for(Student s : list) {
            repo.save(s.getId(), s);
        }

        System.out.println("ALL : ");
        list.forEach(System.out::println);

        System.out.println("\nLOOKUP S2 : ");
        Student s = repo.find("S2");
        System.out.println(s != null ? s : "Not Found");

        Iterator<Student> it = list.iterator();

        while(it.hasNext()) {
            Student st = it.next();
            if(st.getMarks() < 80) {
                it.remove();
                repo.delete(st.getId());
            }
        }

        System.out.println("\nAFTER REMOVAL : ");
        list.forEach(System.out::println);

        System.out.println("\nTOPPER : ");
        Student topper = getTopper(list);
        System.out.println(topper != null ? topper : "No students");
    }

    public static Student getTopper(List<Student> students) {
        if (students == null || students.isEmpty()) return null;
        Student best = null;
        int bestMarks = -1;
        for (Student s : students) {
            int eff = s.getEffectiveMarks();
            if (eff > bestMarks) {
                bestMarks = eff;
                best = s;
            }
        }
        return best;
    }
}
