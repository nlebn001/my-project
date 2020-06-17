package kz.gamma.my.project.service;

import kz.gamma.my.project.model.CaOrganization;
import kz.gamma.my.project.model.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

// TODO: 15.06.2020  tester 
public class ThreadCreator extends Thread{

    public ThreadCreator(String name){
        super(name);
    }

    public static Set<CaOrganization> randomCreate(){

        User user = null;
        ArrayList<User> users = null ;
        Set<CaOrganization> orgs = new HashSet<>();

        for(int i = 0 ; i< (int) (10 * Math.random()+1); i++) {
            users = new ArrayList<User>();
            for(int j = 0; j<(int) (10 * Math.random()+1); j++) {
                user = new User();
                users.add(user);
            }
            orgs.add(new CaOrganization(users));
        }
        return orgs;
    }

    public void run() {
       Set<CaOrganization> orgs = randomCreate();
    }

}
