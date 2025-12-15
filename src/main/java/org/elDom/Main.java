package org.elDom;

import org.elDom.configuration.SessionFactoryUtil;
import org.hibernate.Session;

public class Main {
    public static void main(String[] args) {
        Session session = SessionFactoryUtil.getSessionFactory().openSession();
        session.close();
    }
    //test

}