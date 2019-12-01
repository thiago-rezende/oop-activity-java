package app;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import exceptions.NotFoundException;
import exceptions.IncompleteCourseException;
import java.io.*;
import java.nio.charset.Charset;

/**
 * Main application class
 * 
 * @author Thiago Rezende
 */
public class App {

    public static School school = new School("Horus Software University (HSU)");

    /**
     * @param args launch options
     */
    public static void main(String[] args) {

        tryToLoad();

        String menu = " -- " + App.school.getName() + " -- \n";
        menu += "1. Cadastrar curso\n";
        menu += "2. Matricular aluno\n";
        menu += "3. Matricular aluno em disciplina\n";
        menu += "4. Estatisticas por aluno\n";
        menu += "5. Certificado de conclusao de um aluno\n";
        menu += "6. Data limite de jubilo\n";
        menu += "7. Relatorio de formandos por curso\n";

        Integer op;

        while (true) {
            try {
                op = Integer.parseInt(
                        JOptionPane.showInputDialog(null, menu, App.school.getName(), JOptionPane.QUESTION_MESSAGE));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Saindo", "EXIT", JOptionPane.INFORMATION_MESSAGE);
                break;
            }

            switch (op) {
            case 1:
                registerCourse();
                break;

            case 2:
                registerStudent();
                break;

            case 3:
                enrollStudent();
                break;

            case 4:
                studentStats();
                break;

            case 5:
                conclusionCertificate();
                break;

            case 6:
                limitDate();
                break;

            case 7:
                courseReport();
                break;

            default:
                JOptionPane.showMessageDialog(null, "Invalid Input!", "ERROR", JOptionPane.ERROR_MESSAGE);
                break;
            }
        }

        if (!App.school.getCourses().isEmpty())
            tryToSave();

    }

    public static void registerCourse() {
        String title = JOptionPane.showInputDialog(null, "Nome do curso", "Nome", JOptionPane.QUESTION_MESSAGE);

        if (title == null)
            return;

        Integer level = 0;

        while (level != 1 && level != 2) {
            try {
                level = Integer.parseInt(JOptionPane.showInputDialog(null, "Nivel do curso\n1. Tecnico\n2. Superior",
                        "Nivel", JOptionPane.QUESTION_MESSAGE));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        List<Subject> subjects = new ArrayList<>();
        Integer howManySubjects;
        try {
            howManySubjects = Integer.parseInt(JOptionPane.showInputDialog(null, "Quantidade de disciplinas",
                    "Disciplinas", JOptionPane.QUESTION_MESSAGE));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }

        for (int i = 0; i < howManySubjects; i++) {
            String name = JOptionPane.showInputDialog(null, "Nome da disciplina", "Nome", JOptionPane.QUESTION_MESSAGE);
            ;
            Integer period;
            Integer valueInCredits;
            try {
                period = Integer.parseInt(JOptionPane.showInputDialog(null, "Periodo da disciplina", "Periodo",
                        JOptionPane.QUESTION_MESSAGE));
                valueInCredits = Integer.parseInt(JOptionPane.showInputDialog(null, "Valor da disciplina em creditos",
                        "Creditos", JOptionPane.QUESTION_MESSAGE));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Subject subject = new Subject(name, period, valueInCredits);
            subjects.add(subject);
        }

        Integer integrationPeriods;
        try {
            integrationPeriods = Integer.parseInt(JOptionPane.showInputDialog(null, "Periodos de integracao",
                    "Integracao", JOptionPane.QUESTION_MESSAGE));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Course course = new Course(title, level, subjects, integrationPeriods);
        App.school.addCourse(course);
        String message = "Titulo: " + course.getTitle();
        message += "\nNviel: " + (course.getLevel() == 1 ? "Tecnico" : "Superior");
        message += "\nDisciplinas: " + course.getSubjects().size();
        for (Subject subject : course.getSubjects())
            message += "\n - Nome: " + subject.getName() + " Periodo: " + subject.getPeriod();
        message += "\nPeriodos de integracao: " + course.getIntegrationPeriods();
        JOptionPane.showMessageDialog(null, message, "Curso", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void registerStudent() {
        if (App.school.getCourses().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum curso dadastrado", "ERROR", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String name = JOptionPane.showInputDialog(null, "Nome do aluno", "Nome", JOptionPane.QUESTION_MESSAGE);

        if (name == null)
            return;

        String message = "Cursos disponiveis:\n";

        for (int i = 0; i < App.school.getCourses().size(); i++)
            message += (i + 1) + ". " + App.school.getCourses().get(i).getTitle() + "\n";

        Integer i = -1;

        while (!(i > 0 && i <= App.school.getCourses().size())) {
            try {
                i = Integer.parseInt(JOptionPane.showInputDialog(null, message, "Curso", JOptionPane.QUESTION_MESSAGE));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        Course course = App.school.getCourses().get(i - 1);
        Student student = new Student(name, course);
        App.school.addStudent(student);
        message = "Matricula: " + student.getRegistration();
        message += "\nNome: " + student.getName();
        message += "\nCurso: " + student.getCourse().getTitle();
        JOptionPane.showMessageDialog(null, message, "Aluno", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void enrollStudent() {
        Integer registration;
        try {
            registration = Integer.parseInt(JOptionPane.showInputDialog(null, "Numero de matricula", "Matricula",
                    JOptionPane.QUESTION_MESSAGE));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Student student;

        try {
            student = App.school.getStudent(registration);
        } catch (NotFoundException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (student.getCourse().getSubjects().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nao ha disciplinas no curso", "ERRO", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        List<Subject> subjectsToEnroll = new ArrayList<>();

        for (Subject subject : student.getCourse().getSubjects()) {
            boolean found = false;

            for (EnrolledSubject enrolled : student.getEnrolledSubjects())
                if (subject.equals(enrolled.getSubject()))
                    found = true;

            if (!found)
                subjectsToEnroll.add(subject);
        }

        if (subjectsToEnroll.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Matriculado em todas as disciplinas", "Matriculado",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String message = "Available subjects:";

        for (int i = 0; i < subjectsToEnroll.size(); i++) {
            message += "\n" + (i + 1) + ". " + subjectsToEnroll.get(i).getName();
        }

        Integer subjectID = 0;
        while ((subjectID < 1 || subjectID > subjectsToEnroll.size())) {
            try {
                subjectID = Integer.parseInt(
                        JOptionPane.showInputDialog(null, message, "Disciplinas", JOptionPane.QUESTION_MESSAGE));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        student.addEnrolledSubject(subjectsToEnroll.get(subjectID - 1));
        JOptionPane.showMessageDialog(null, "Enrolled", "Status", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void studentStats() {
        Integer registration;
        try {
            registration = Integer.parseInt(JOptionPane.showInputDialog(null, "Numero de matricula", "Matricula",
                    JOptionPane.QUESTION_MESSAGE));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Student student;

        try {
            student = App.school.getStudent(registration);
        } catch (NotFoundException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String message = "";

        message += "\nNome: " + student.getName();
        message += "\nMatricula: " + student.getRegistration();
        message += "\nCurso: " + student.getCourse().getTitle();
        message += "\nDisciplinas por periodo:\n";

        EnrolledSubject[] enrolledSubjects = new EnrolledSubject[student.getEnrolledSubjects().size()];
        for (int i = 0; i < enrolledSubjects.length; i++) {
            enrolledSubjects[i] = student.getEnrolledSubjects().get(i);
        }

        Arrays.sort(enrolledSubjects);

        for (EnrolledSubject subject : enrolledSubjects) {
            message += " - " + subject.getSubject().getName() + " " + subject.getSubject().getPeriod() + " "
                    + (subject.getApprovalStatus() ? "OK" : " ") + "\n";
        }

        Integer conclusionRatio = 0;
        for (EnrolledSubject subject : enrolledSubjects)
            if (subject.getApprovalStatus())
                conclusionRatio++;

        Float conclusionPercentage = (100.0f * conclusionRatio) / student.getCourse().getSubjects().size();

        message += "Concluiu " + conclusionPercentage + "% do curso\n";
        message += "Numero de disciplinas nao cursadas: "
                + (student.getCourse().getSubjects().size() - student.getEnrolledSubjects().size()) + "\n";
        JOptionPane.showMessageDialog(null, message, "Aluno", JOptionPane.INFORMATION_MESSAGE);

    }

    public static void conclusionCertificate() {
        Integer registration;
        try {
            registration = Integer.parseInt(JOptionPane.showInputDialog(null, "Numero de matricula", "Matricula",
                    JOptionPane.QUESTION_MESSAGE));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Student student;

        try {
            student = App.school.getStudent(registration);
        } catch (NotFoundException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String certificate = "";

        try {
            certificate = student.getCertificateOfCompletion();
        } catch (IncompleteCourseException e) {
            JOptionPane.showMessageDialog(null, "Curso Incompleto", "Incompleto", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        FileOutputStream fileOut;

        try {
            fileOut = new FileOutputStream(student.getRegistration() + ".txt");
            fileOut.write(certificate.getBytes(Charset.forName("UTF-8")));
            fileOut.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }

        JOptionPane.showMessageDialog(null, certificate, "Certificate", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void limitDate() {
        Integer registration;
        try {
            registration = Integer.parseInt(JOptionPane.showInputDialog(null, "Numero de matricula", "Matricula",
                    JOptionPane.QUESTION_MESSAGE));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Student student;

        try {
            student = App.school.getStudent(registration);
        } catch (NotFoundException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }

        LocalDate date = student.getDateOfEntry().plusYears(8);
        JOptionPane.showMessageDialog(null, "Data limite: " + date, "Limite", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void courseReport() {
        String message = " -- RELATORIO --\n";
        for (Student student : App.school.getStudents()) {
            if (student.getApprovalStatus())
                message += " - " + student.getRegistration() + " " + student.getName() + " "
                        + student.getCourse().getTitle();
        }
        JOptionPane.showMessageDialog(null, message, "Relatorio", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void tryToLoad() {
        FileInputStream fileIn;
        ObjectInputStream objIn;

        try {
            fileIn = new FileInputStream("school.ser");
            objIn = new ObjectInputStream(fileIn);
            App.school = (School) objIn.readObject();
            fileIn.close();
            objIn.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException c) {
            JOptionPane.showMessageDialog(null, c.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void tryToSave() {
        FileOutputStream fileOut;
        ObjectOutputStream objOut;

        try {
            fileOut = new FileOutputStream("school.ser");
            objOut = new ObjectOutputStream(fileOut);
            objOut.writeObject(App.school);
            fileOut.close();
            objOut.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }

        JOptionPane.showMessageDialog(null, "Serializado no arquivo 'school.ser'", "INFO",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
