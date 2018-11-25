package br.com.financeiro.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
	
	private static SessionFactory fabricaDeSessoes = criaFabricaDeSessoes();
	
	public static SessionFactory setFabricaDeSessoes() {
        return fabricaDeSessoes;
    }
	
    private static SessionFactory criaFabricaDeSessoes() {

	    try {
	        Configuration configuration = new Configuration().configure();
	        
	        ServiceRegistry registro = new StandardServiceRegistryBuilder()
	                .applySettings(configuration.getProperties()).build();
	        SessionFactory fabrica = configuration.buildSessionFactory(registro);
	        
	        return fabrica;
	    }
	    catch(Throwable ex) {
	    	System.err.println("A fabrica de sessoes nao pode ser criada! " + ex);
	    	throw new ExceptionInInitializerError(ex);
	    }
    }

}