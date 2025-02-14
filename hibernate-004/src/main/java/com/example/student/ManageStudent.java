package com.example.student;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ManageStudent {

    public Integer addStudent(String firstName, String lastName, int rollNumber) {
    	Session session = org.hibernate.SessionFactoryImplementor.from(HibernateUtil.getSessionFactory()).openSession();
        Transaction tx = null;
        Integer studentID = null;

        try {
            tx = session.beginTransaction();
            Student student = new Student(firstName, lastName, rollNumber);
            studentID = (Integer) session.save(student);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return studentID;
    }

    public void listStudents() {
        Session session = HibernateUtil.getSessionFactory().createEntityManager().unwrap(Session.class);
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            List<Student> students = session.createQuery("FROM Student", Student.class).list();
            for (Student student : students) {
                System.out.println("ID: " + student.getId());
                System.out.println("First Name: " + student.getFirstName());
                System.out.println("Last Name: " + student.getLastName());
                System.out.println("Roll Number: " + student.getRollNumber());
                System.out.println("---------------");
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}