package app;

import java.util.List;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import exceptions.NotFoundException;
import exceptions.IncompleteCourseException;

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
    public static void main(String[] args) throws Exception {
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

    }

    public static void registerCourse() {
        String title = JOptionPane.showInputDialog(null, "Nome do curso", "Nome", JOptionPane.QUESTION_MESSAGE);
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

    }

    public static void studentsStats() {
        String message = "Alunos:\n";

        for (Student student : App.school.getStudents()) {
            message += "\nNome: " + student.getName();
            message += "\nMatricula: " + student.getRegistration();
            message += "\nCurso: " + student.getCourse().getTitle();
            message += "\nDisciplinas:";
            for (Subject subject : student.getCourse().getSubjects()) {
                message += subject.getName() + " ";
            }
            message += "\n";
        }

        JOptionPane.showMessageDialog(null, message, "Alunos", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void studentStats() {
        Integer id;

        try {
            id = Integer
                    .parseInt(JOptionPane.showInputDialog(null, "Digite o id do aluno:", JOptionPane.QUESTION_MESSAGE));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Student student = App.school.getStudents().get(id - 1);

        String message = "Aluno:\n";
        message += "\nNome: " + student.getName();
        message += "\nMatricula: " + student.getRegistration();
        message += "\nCurso: " + student.getCourse().getTitle();
        message += "\nDisciplinas:";

        for (Subject subject : student.getCourse().getSubjects()) {
            message += subject.getName() + " " + subject.getPeriod() + "\n";
        }

        JOptionPane.showMessageDialog(null, message, "Alunos", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void conclusionCertificate() throws IncompleteCourseException {
        Integer id;

        try {
            id = Integer.parseInt(
                    JOptionPane.showInputDialog(null, "Digite o id do aluno: ", JOptionPane.QUESTION_MESSAGE));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (App.school.getStudents().get(id - 1).getAcademicPerformanceCoefficient() > 60.0f)
            throw new IncompleteCourseException("O Aluno foi reprovado!");

        // Serialização do certificado
    }

    public static void limitDate() {

    }

    public static void courseReport() {

    }

    public static void testSchool() {
        Subject subject = new Subject("Introduction to Programming", 1, 5);
        List<Subject> courseSubjects = new ArrayList<>();
        courseSubjects.add(subject);
        Course course = new Course("Computer Science", 2, courseSubjects, 4);
        School school = new School("Horus Software University (HSU)");
        school.addCourse(course);
        Student student = new Student("Thiago Rezende", school.getCourses().get(0));
        school.addStudent(student);

        try {
            student = school.getStudent(20191);
            student.addEnrolledSubject(student.getCourse().getSubjects().get(0));
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }

        student.getEnrolledSubjects().get(0).addGrade(100);

        try {
            System.out.println(student.getCertificateOfCompletion());
        } catch (IncompleteCourseException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(student);
        System.out.println(school);
    }
}
