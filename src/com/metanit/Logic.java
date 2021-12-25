package com.metanit;

/*

23. При поступлении на некоторую специальность подали заявление множество
абитуриентов (абитуриент описывается следующим образом: ФИО, оценка по русскому
языку, оценка по математике, оценка по физике). На соответствующей специальности N
бюджетных мест, отберите тех студентов, которые поступят. Приоритет имеют студенты
с максимальной суммой баллов, затем с большим баллом по математике, затем по
физике.
 */

import java.util.ArrayList;
import java.util.List;

public class Logic {

        public static List<Student> students(List<Student> persons) {
            List<Student> students = new ArrayList<>();

            students.addAll(studentsJoin(persons));

            return students;
        }

        private static List<Student> studentsJoin(List<Student> persons) {

            studentsMark(persons);
            studentsSort(persons);


            return persons;
        }

        private static void studentsSort(List<Student> persons) {

            List<Student> temp = new ArrayList<>();

            for (int i = 0; i < persons.size(); i++) {
                for (int j = i + 1; j < persons.size(); j++) {

                    if (persons.get(i).mark < persons.get(j).mark) {
                        temp.add(persons.get(i));
                        persons.set(i, persons.get(j));
                        persons.set(j, temp.get(0));
                        temp.clear();

                    }  else if (persons.get(i).mark == persons.get(j).mark) {
                        if (persons.get(i).markMath < persons.get(j).markMath) {
                            temp.add(persons.get(i));
                            persons.set(i, persons.get(j));
                            persons.set(j, temp.get(0));
                            temp.clear();

                        } else if (persons.get(i).markMath == persons.get(j).markMath) {
                            if (persons.get(i).markPhysics < persons.get(j).markPhysics) {
                                temp.add(persons.get(i));
                                persons.set(i, persons.get(j));
                                persons.set(j, temp.get(0));
                                temp.clear();
                            }
                        }
                    }

                }
            }

        }

        private static void studentsMark(List<Student> persons) {
            for (Student person : persons) {
                person.mark = person.markMath + person.markPhysics + person.markRusLang;
            }

        }
}
