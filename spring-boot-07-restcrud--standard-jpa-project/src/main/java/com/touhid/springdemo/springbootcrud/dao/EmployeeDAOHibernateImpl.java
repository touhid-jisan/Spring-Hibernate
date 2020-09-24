package com.touhid.springdemo.springbootcrud.dao;

import com.touhid.springdemo.springbootcrud.entity.Employee;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO{

    // define field for entityManager
    private EntityManager entityManager;

    // set up constructor injection
    @Autowired
    public EmployeeDAOHibernateImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Override
    public List<Employee> findEmployees() {

        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);
        // create a query
        Query<Employee> theQuery = currentSession.createQuery("from Employee");
        // execute query and get retsult list
        List<Employee> employees = theQuery.getResultList();
        // return the result
        return employees;
    }


    @Override
    public Employee findEmployee(int theId) {
        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);
        // get the employee
        Employee theEmployee = currentSession.get(Employee.class, theId);
        // return the result
        return  theEmployee;
    }

    @Override
    public void saveEmployee(Employee theEmployee) {

        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);
        // saveEmployee
        currentSession.saveOrUpdate(theEmployee);
    }

    @Override
    public void deleteEmployee(int theId) {

        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);
        // delete object with primary key
        Query theQuery = currentSession.createQuery("delete from Employee  where id=:employeeId");
        theQuery.setParameter("employeeId", theId);
        theQuery.executeUpdate();
    }
}
