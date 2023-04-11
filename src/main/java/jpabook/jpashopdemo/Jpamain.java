package jpabook.jpashopdemo;

import jpabook.jpashopdemo.domain.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class Jpamain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Book book = new Book();
            book.setName("JPA");
            book.setAuthor("김영한");
            em.persist(book);

            Movie movie = new Movie();
            movie.setName("asdf");
            movie.setDirector("asssss");
            em.persist(movie);

            List<Item> result = em.createQuery("select i from Item i where treat(i as Book).author = 'kim'", Item.class)
                    .getResultList();

            for (Item item : result) {
                System.out.println("item = " + item);
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
