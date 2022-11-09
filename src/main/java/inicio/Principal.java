package inicio;


import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import entidad.Alumno;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import util.JpaUtil;

public class Principal {

	private static final Logger logger = LogManager.getLogger(Principal.class);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		   logger.debug("------> Empezando <-------------");
		   EntityManager em = JpaUtil.getEntityManager();
		   System.out.println("\n\t=========Listado de alumnos==========");
		   List<Alumno> alumnos = em.createQuery("SELECT a FROM Alumno a", Alumno.class).getResultList();
		   
		   
		   alumnos.forEach(alumno->logger.debug(alumno));
		   
		   
	       
	       
	       System.out.println("\t");
	       System.out.println("\t====== Busca alumno por ID ======");
	       Scanner s = new Scanner(System.in);

	       System.out.println("Ingrese el id del alumno: ");
	       int id = s.nextInt();
	      
	       Alumno alumno = em.find(Alumno.class, id);
	       logger.debug(alumno);
	       
	       System.out.println("\t");
	       System.out.println("\t====== Busca alumno por su DNI ======");
	       
	       Query query = em.createQuery("from Alumno a where a.dni=?1", Alumno.class);
	        System.out.println("Ingrese el dni de un alumno : ");
	        String dni = s.next();
	        query.setParameter(1, dni);
	        query.setMaxResults(1);
	        Alumno alumnoDNI = (Alumno) query.getSingleResult();
	        logger.debug(alumnoDNI );
	        em.close();
	        logger.debug("------> Final <-------------");
	}

}
