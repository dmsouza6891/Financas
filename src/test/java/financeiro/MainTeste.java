package financeiro;

import javax.persistence.EntityManager;

import br.com.dmsouza.financas.model.CentroDeCusto;
import br.com.dmsouza.financas.model.dao.CentroDeCustoDao;
import br.com.dmsouza.financas.util.JPAUtil;

public class MainTeste {
	
	public static void main(String[] args) {
		
		EntityManager em = JPAUtil.getEntityManager();
		
		CentroDeCusto banco = new CentroDeCusto("itau", 100L);
		CentroDeCustoDao bancoDao = new CentroDeCustoDao(em);
		
		em.getTransaction().begin();
		bancoDao.cadastrar(banco);
		System.out.println(banco);
		em.getTransaction().commit();
		em.close();
		
	}

}
