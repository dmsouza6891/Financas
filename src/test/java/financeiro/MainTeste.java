package financeiro;

import java.time.LocalDate;

import javax.persistence.EntityManager;

import br.com.dmsouza.financas.model.Categoria;
import br.com.dmsouza.financas.model.CentroDeCusto;
import br.com.dmsouza.financas.model.Debito;
import br.com.dmsouza.financas.model.dao.CategoriaDao;
import br.com.dmsouza.financas.model.dao.CentroDeCustoDao;
import br.com.dmsouza.financas.model.dao.DebitoDao;
import br.com.dmsouza.financas.util.JPAUtil;

public class MainTeste {
	
	public static void main(String[] args) {
		
		EntityManager em = JPAUtil.getEntityManager();
		
		CentroDeCusto banco = new CentroDeCusto("itau", 100);
		Categoria mercado = new Categoria("Mercado");
		Debito debitoPrimeiro = new Debito(LocalDate.now(), "compras semana", 50.20, banco, mercado);
		CentroDeCustoDao bancoDao = new CentroDeCustoDao(em);
		DebitoDao debitoDao = new DebitoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);
		
		em.getTransaction().begin();
		bancoDao.cadastrar(banco);
		categoriaDao.cadastrar(mercado);
		debitoDao.cadastrar(debitoPrimeiro);
		em.getTransaction().commit();
		em.close();
		
	}

}
