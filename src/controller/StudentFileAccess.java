package controller;
import java.util.ArrayList;
import java.util.List;

import model.Student;

public class StudentFileAccess {

    public List<Student> students;

    /**
     * Constructor to initialize the list of students.
     */
    public StudentFileAccess() {
        students = new ArrayList<Student>();
    }

    /**
     * Gets the list of students.
     * 
     * @return A list of students.
     */
    public List<Student> getStudents() {
        return students;
    }

    public void readFromTextFile(String fileName) {
        //TODO
    }

    public void writeToTextFile(String fileName) {
        //TODO
    }

    public void writeToBinaryFile(String fileName) {
        //TODO
    }

    public void readFromBinaryFile(String fileName) {
       //TODO
    }

    public void writeObject(String fileName) {
        //TODO

    }

    public void readObject(String fileName) {
        //TODO
    }
}
