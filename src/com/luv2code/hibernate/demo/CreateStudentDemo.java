package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class CreateStudentDemo {

	public static void main(String[] args) {
//		create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

//		create session
		Session session = factory.getCurrentSession();

		try {
//			create a student object
			System.out.println("ceate a new student object...");
			Student tempStudent = new Student("paul", "wall", "paul@luv2code.com");

//			start a transaction
			session.beginTransaction();

//			save the student object
			System.out.println("saving the student...");
			session.save(tempStudent);

//			commit transaction
			System.out.println("now commiting...");
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
