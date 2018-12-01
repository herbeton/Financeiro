package br.com.financeiro.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


import java.sql.Connection;
import java.sql.SQLException;
import org.hibernate.Session;
import org.hibernate.jdbc.ReturningWork;

public class HibernateUtil {

	private static SessionFactory fabricaDeSessoes = criarFabricaDeSessoes();

	public static SessionFactory getFabricaDeSessoes() {
		return fabricaDeSessoes;
	}

	public static Connection getConexao() {
		Session sessao = fabricaDeSessoes.openSession();

		Connection conexao = sessao.doReturningWork(new ReturningWork<Connection>() {
			@Override
			public Connection execute(Connection conn) throws SQLException { // TODO Auto-generated method stub
				return conn;
			}
		});

		return conexao;
	}

	private static SessionFactory criarFabricaDeSessoes() {

		try {
			Configuration configuration = new Configuration().configure();

			ServiceRegistry registro = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties())
					.build();

			SessionFactory fabrica = configuration.buildSessionFactory(registro);

			return fabrica;
		} catch (Throwable ex) {
			System.err.println("A fabrica de sessoes nao pode ser criada! " + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

}