package com.epam.model;

import com.epam.passwordOperations.PreferredPassword;
import com.epam.repository.MySQL_DB;
import com.epam.repository.RepositoryDB;
import com.epam.repository.RepositoryManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestClass
{
    public static void main(String[] args)
    {
        EntityManager em = RepositoryManager.getEntityManager().createEntityManager();
//        User user;
//        List<UserAccount> ua = new ArrayList<>();
//        List<String> grp = new ArrayList<>();
//        PreferredPassword pp = new PreferredPassword();
//        user = new User("Manash", "qwerty", ua, grp, pp);

        List<UserAccount> uA = new ArrayList<>();



        RepositoryDB database = new MySQL_DB();
        User user = new User();
        uA.add(new UserAccount("a", "hgbaiy", "yigbciyv", "G1", user));
        user.setUserName("Hari");
        user.setPassword("gvyfk");
        user.setAccounts(uA);
        List<String> grps = Arrays.asList("Java,C#,Python,ReactJs");
        user.setGroups(grps);
        user.setPrefPass(new PreferredPassword());


        database.setMasterUser(user);


//        em.getTransaction().begin();
//        em.persist(user);
//        em.getTransaction().commit();
    }
}
