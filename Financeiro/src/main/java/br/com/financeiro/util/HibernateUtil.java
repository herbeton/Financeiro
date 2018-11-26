package br.com.financeiro.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
	
	private static SessionFactory fabricaDeSessoes = criarFabricaDeSessoes();
	
	public static SessionFactory getFabricaDeSessoes() {
        return fabricaDeSessoes;
    }
	
    private static SessionFactory criarFabricaDeSessoes() {

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