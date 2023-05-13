import builders.StudentsBuilder;
import entities.Student;

import java.sql.SQLOutput;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Comparator;

public class Main {

    public static List listaAlunos(){
        var allStudents = StudentsBuilder.getAllStudents();
        return allStudents;
    }

    public static List alunosAprovados(){
        var alunosAprov = new ArrayList<>();

        for(Object student: listaAlunos()){
            Student alunoAtual = (Student) student;

            if((alunoAtual.getTestOne()
                + alunoAtual.getTestTwo()
                + alunoAtual.getTestThree()) / 3 >= 7.0){
                    alunosAprov.add(alunoAtual);

            }
        }
        return(alunosAprov);
    }

    public static List alunosReprovados(){
        var alunosReprov = new ArrayList<>();

        for(Object student : listaAlunos()){
            Student alunoAtual = (Student) student;
            if((alunoAtual.getTestOne()
                    + alunoAtual.getTestTwo()
                    + alunoAtual.getTestThree()) / 3 < 7.0){
                alunosReprov.add(alunoAtual);

            }
        }

        return(alunosReprov);
    }



    public static void main(String[] args) {

        while(true) {
            System.out.println("\nSelecione a opção desejada: \n" +
                    "1 - Alunos que passaram de ano (nota minima 7.0)\n" +
                    "2 - Alunos que não passaram de ano.\n" +
                    "3 - Alunos que tiraram a nota máxima (nota 10)\n" +
                    "4 - Aluno que tirou a menor nota\n" +
                    "5 - Top 3 notas dos alunos\n" +
                    "6 - Lista com as 3 menores notas de alunos\n" +
                    "7 - Média de todos os alunos ordenandas da maior para a menor nota\n" +
                    "0 - Sair\n");

            var sc = new Scanner(System.in);
            var num = sc.nextInt();
            DecimalFormat df = new DecimalFormat("#.##");

            switch(num){
                case 1:
                    for(Object student: alunosAprovados()) {
                        Student alunoAtual = (Student) student;
                        System.out.println(alunoAtual.getCode() + " - " + alunoAtual.getName() +" : Média = "
                                +  (df.format((alunoAtual.getTestOne() + alunoAtual.getTestTwo() + alunoAtual.getTestThree()) / 3)));
                    }
                break;

                case 2:
                    for(Object student: alunosReprovados()) {
                        Student alunoAtual = (Student) student;
                        var media = (alunoAtual.getTestOne() + alunoAtual.getTestTwo() + alunoAtual.getTestThree()) / 3;
                        System.out.println(alunoAtual.getCode() + " - " + alunoAtual.getName() +" : Média = "
                                +  (df.format(media)) + " (Faltou = " + df.format(7.0 - media) + ")");
                    }
                break;

                case 3:
                    for(Object student: alunosAprovados()) {
                        Student alunoAtual = (Student) student;
                        if(alunoAtual.getTestOne() == 10) {
                            System.out.println("\n" + alunoAtual.getCode() + " - " + alunoAtual.getName());
                        }
                    }
                break;

                case 4:
                    var menorNota = Double.POSITIVE_INFINITY;
                    var menoresNotas = new ArrayList<Student>();
                    var alunoMenorNota = new Student();
                    for(Object student: alunosReprovados()) {
                        Student alunoAtual = (Student) student;
                        var media = (alunoAtual.getTestOne() + alunoAtual.getTestTwo() + alunoAtual.getTestThree()) / 3;
                        if (media < menorNota) {
                            menorNota = media;
                            menoresNotas.clear();
                            menoresNotas.add(alunoAtual);
                            alunoMenorNota = alunoAtual;
                        }
                    }

                    for (Object student2: alunosReprovados()){
                        Student alunoAtual2 = (Student) student2;
//                        ----Trecho para teste de duas notas menores que são iguais----
//                        ----Setters da classe Student estão como comentário, retirá-los para usar este trecho----
//                        if(alunoAtual2.getCode() == 3) {
//                            alunoAtual2.setTestOne((float) menorNota);
//                            alunoAtual2.setTestTwo((float) menorNota);
//                            alunoAtual2.setTestThree((float) menorNota);
//                        }
                        var media2 = (alunoAtual2.getTestOne() + alunoAtual2.getTestTwo() + alunoAtual2.getTestThree()) / 3;
                        if(media2 == (alunoMenorNota.getTestOne() + alunoMenorNota.getTestTwo() + alunoMenorNota.getTestThree()) / 3){
                            System.out.println(alunoAtual2.getCode() + " - " + alunoAtual2.getName() + " : Nota = " + df.format(media2));
                        }
                    }

                break;

                case 5:
                    //Neste caso não consegui criar a lógica para dois alunos da mesma nota fiquem na mesma posição, apenas o TOP 3
                    var maiorNota = Double.NEGATIVE_INFINITY;
                    var alunosAprov = alunosAprovados();
                    var alunoMaiorNota = new Student();
                    for(int i = 0; i < 3; i++) {
                        for (Object student3 : alunosAprov) {
                            Student alunoAtual3 = (Student) student3;
                            var media3 = (alunoAtual3.getTestOne() + alunoAtual3.getTestTwo() + alunoAtual3.getTestThree()) / 3;
                            if (media3 > maiorNota) {
                                maiorNota = media3;
                                alunoMaiorNota = alunoAtual3;
                            }
                        }

                        alunosAprov.remove(alunoMaiorNota);
                        System.out.println((i + 1) + "º " + alunoMaiorNota.getName() + ": Nota = "
                                + df.format((alunoMaiorNota.getTestOne() + alunoMaiorNota.getTestTwo() + alunoMaiorNota.getTestThree()) / 3));

                        maiorNota = Double.NEGATIVE_INFINITY;
                    }
                break;

                case 6:
                    var alunosReprov = alunosReprovados();
                    for(Object studentRA: alunosAprovados()){
                        Student studentADD = (Student) studentRA;
                        alunosReprov.add(studentADD);
                    }

                    var menorNota2 = Double.POSITIVE_INFINITY;
                    var alunoMenorNota2 = new Student();
                    for(int i = 0;i < 3;i++) {
                        for (Object student4 : alunosReprov) {
                            Student alunoAtual4 = (Student) student4;
                            var media4 = (alunoAtual4.getTestOne() + alunoAtual4.getTestTwo() + alunoAtual4.getTestThree()) / 3;
                            if (media4 < menorNota2) {
                                menorNota2 = media4;
                                alunoMenorNota2 = alunoAtual4;
                            }
                        }
                        alunosReprov.remove(alunoMenorNota2);
                        System.out.println((i + 1) + "º - " + alunoMenorNota2.getName() + " : Nota = "
                                + df.format((alunoMenorNota2.getTestOne() + alunoMenorNota2.getTestTwo() + alunoMenorNota2.getTestThree()) / 3));
                        menorNota2 = Double.POSITIVE_INFINITY;
                    }
                break;

                case 7:
                    var alunosList = alunosReprovados();
                    for(Object studentRA: alunosAprovados()){
                        Student studentADD = (Student) studentRA;
                        alunosList.add(studentADD);
                    }
                    var maiorNota2 = Double.NEGATIVE_INFINITY;
                    var alunoMaiorNota2 = new Student();
                    for(int i = 0; i < listaAlunos().size(); i++) {
                        for (Object student5 : alunosList) {
                            Student alunoAtual5 = (Student) student5;
                            var media5 = (alunoAtual5.getTestOne() + alunoAtual5.getTestTwo() + alunoAtual5.getTestThree()) / 3;
                            if (media5 > maiorNota2) {
                                maiorNota2 = media5;
                                alunoMaiorNota2 = alunoAtual5;
                            }
                        }
                        alunosList.remove(alunoMaiorNota2);
                        System.out.println((i + 1) + "º - " + alunoMaiorNota2.getCode() + " - " + alunoMaiorNota2.getName() + " : Média = "
                                + df.format((alunoMaiorNota2.getTestOne() + alunoMaiorNota2.getTestTwo() + alunoMaiorNota2.getTestThree()) / 3));
                        maiorNota2 = Double.NEGATIVE_INFINITY;
                    }

                case 0:
                    System.exit(0);
            }
        }

    }
}
