package com.example.hibernate.mains;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.example.hibernate.entities.Empleado;
import com.example.hibernate.utils.HibernateUtil;


public class Main {
    public static void main(String[] args) {
        // Crear un nuevo empleado
        Empleado empleado = new Empleado();
        empleado.setId(1);
        empleado.setNombre("Juan Pérez");
        empleado.setDepartamento("Ventas");
        empleado.setSalario(45000);

        // Abrir sesión
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        
        // Guardar el empleado
//        session.save(empleado); // save esta deprecated asi que uso persist()
        session.persist(empleado);
        transaction.commit();

        // Leer el empleado recién creado
        Empleado empleadoLeido = session.get(Empleado.class, 1);
        System.out.println("Empleado leído: " + empleadoLeido.getNombre());

        // Actualizar el salario del empleado
        transaction = session.beginTransaction();
        empleadoLeido.setSalario(50000);
//        session.update(empleadoLeido); // esto esta deprecated asi que como alternativa se usa merge() pero el objeto no debe estar vinculado con la sesion
        session.merge(empleadoLeido);
        transaction.commit();

        // Eliminar el empleado
        transaction = session.beginTransaction();
//        session.delete(empleadoLeido); // delete es deprecated asi que uso remove
        session.remove(empleadoLeido);
        transaction.commit();

        // Cerrar la sesión
        session.close();

        // Apagar Hibernate
        HibernateUtil.shutdown();
    }
}
