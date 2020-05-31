package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class ReadStudentDemo {

	public static void main(String[] args) {
//		create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

//		create session
		Session session = factory.getCurrentSession();

		try {
//			create a student object
			System.out.println("ceate a new student object...");
			Student tempStudent = new Student("Daffy", "Duck", "daffy@luv2code.com");

//			start a transaction
			session.beginTransaction();

//			save the student object
			System.out.println("saving the student...");
			System.out.println(tempStudent);
			session.save(tempStudent);

//			commit transaction
			System.out.println("now commiting...");
			session.getTransaction().commit();

//			find out the student's id: primary key
			System.out.println("saved student Generated id: " + tempStudent.getId());

//			now get a new session and start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();

//			retrieve student based on the id: primary key
			System.out.println("\n Getting student with id: " + tempStudent.getId());
			Student myStudent = session.get(Student.class, tempStudent.getId());

			System.out.println("\nGet complete: " + myStudent);
//			commit the transaction
			session.getTransaction().commit();

			System.out.println("Done..!");

		} catch (Exception e) {
			System.out.println("Exception is: ");
			e.printStackTrace();
		} finally {
			factory.close();
		}
	}

}
