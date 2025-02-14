package com.example.student;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Date;
import java.util.Iterator;

public class ManageStudent {
   private static SessionFactory factory; 
   public static void main(String[] args) {
      
      try {
         factory = new Configuration().configure().buildSessionFactory();
      } catch (Throwable ex) { 
         System.err.println("Failed to create sessionFactory object." + ex);
         throw new ExceptionInInitializerError(ex); 
      }
      
      ManageStudent MS = new ManageStudent();

      /* Add few students records in database */
      Integer stuID1 = MS.addStudent(1, "Aswathi", "T");
      Integer stuID2 = MS.addStudent(15, "Bhava", "L");
      Integer stuID3 = MS.addStudent(20, "Tharini", "L");

      /* List down all the students */
      MS.listStudents();

      /* Update student's records */
      MS.updateStudent(stuID1, 5);

      /* Delete an student from the database */
      MS.deleteStudent(stuID2);

      /* List down new list of the students */
      MS.listStudents();
   }
   
   /* Method to CREATE an student in the database */
   public Integer addStudent(int rollNumber, String fname, String lname){
      Session session = factory.openSession();
      Transaction tx = null;
      Integer studentID = null;
      
      try {
         tx = session.beginTransaction();
         Student student = new Student(rollNumber, fname, lname);
         studentID = (Integer) session.save(student); 
         tx.commit();
      } catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      } finally {
         session.close(); 
      }
      return studentID;
   }
   
   /* Method to  READ all the students */
   public void listStudents() {
      Session session = factory.openSession();
      Transaction tx = null;
      
      try {
         tx = session.beginTransaction();
         List students = session.createQuery("FROM Student").list(); 
         for (Iterator iterator = students.iterator(); iterator.hasNext();){
            Student student = (Student) iterator.next(); 
            System.out.print("Roll Number: " + student.getRollNumber()); 
            System.out.print("  First Name: " + student.getFirstName()); 
            System.out.println("  Last Name: " + student.getLastName()); 
         }
         tx.commit();
      } catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      } finally {
         session.close(); 
      }
   }

   
   /* Method to UPDATE salary for an student */
   public void updateStudent(Integer StudentID, int rollNumber ){
      Session session = factory.openSession();
      Transaction tx = null;
      
      try {
         tx = session.beginTransaction();
         Student student = (Student)session.get(Student.class, StudentID); 
         student.setRollNumber( rollNumber );
		 session.update(student); 
         tx.commit();
      } catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      } finally {
         session.close(); 
      }
   }
   
   /* Method to DELETE an student from the records */
   public void deleteStudent(Integer StudentID){
      Session session = factory.openSession();
      Transaction tx = null;
      
      try {
         tx = session.beginTransaction();
         Student student = (Student)session.get(Student.class, StudentID); 
         session.delete(student); 
         tx.commit();
      } catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      } finally {
         session.close(); 
      }
   }
}