package controller;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Evaluation;
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
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(";");

                // Extract name and surname
                String name = parts[0].trim();
                String surname = parts[1].trim();
                Student etudiant = new Student(surname, name);

                this.students.add(etudiant);

                // Extract notes and coefficients
                for (int i = 2; i < parts.length; i += 2) {
                    int coefficient = Integer.parseInt(parts[i].trim());
                    float note = Float.parseFloat(parts[i + 1].trim());

                    Evaluation examen = new Evaluation(note, coefficient);

                    etudiant.add(examen);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + fileName);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Invalid number format in the file: " + e.getMessage());
        }
    }


    public void writeToTextFile(String fileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            for (Student student : students) {
                bufferedWriter.write(student.getName() + ";" + student.getFirstName() + ";");
                List<Evaluation> evaluations = student.getEvaluations();
                for (Evaluation evaluation : evaluations) {
                    bufferedWriter.write(evaluation.getCoefficient() + ";" + evaluation.getScore() + ";");
                }
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public void writeToBinaryFile(String fileName) {
        try (DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(fileName))) {
            for (Student student : students) {
                dataOutputStream.writeUTF(student.getName());
                dataOutputStream.writeUTF(student.getFirstName());
                List<Evaluation> evaluations = student.getEvaluations();
                for (Evaluation evaluation : evaluations) {
                    dataOutputStream.writeInt(evaluation.getCoefficient());
                    dataOutputStream.writeFloat(evaluation.getScore());
                }
            }
            System.out.println("Student data has been written to the binary file: " + fileName);
        } catch (IOException e) {
            System.err.println("Error writing to binary file: " + e.getMessage());
        }
    }

    public void readFromBinaryFile(String fileName) {
        try (DataInputStream dataInputStream = new DataInputStream(new FileInputStream(fileName))) {
            while (dataInputStream.available() > 0) {
                String name = dataInputStream.readUTF();
                String firstName = dataInputStream.readUTF();
                Student student = new Student(name, firstName);
                while (dataInputStream.available() > 0) {
                    int coefficient = dataInputStream.readInt();
                    float score = dataInputStream.readFloat();
                    student.add(new Evaluation(score, coefficient));
                }
                students.add(student);
            }
        } catch (IOException e) {
            System.err.println("Error reading from binary file: " + e.getMessage());
        }
    }

    public void writeObject(String fileName) {
        //TODO

    }

    public void readObject(String fileName) {
        //TODO
    }
}
