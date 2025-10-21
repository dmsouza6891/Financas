package financeiro;

public class MainTeste {
	
	public static void main(String[] args) {

		/*
		EntityManager em = JPAUtil.getEntityManager();
		
		CentroDeCusto banco = new CentroDeCusto("itau", 100);
		Categoria mercado = new Categoria("Mercado");
		Debito debitoPrimeiro = new Debito(LocalDate.now(), "compras semana", 50.20, banco, mercado);
		Credito creditoPrimeiro = new Credito(LocalDate.now(), "salario", 1000.00, banco, mercado);
		CentroDeCustoDao bancoDao = new CentroDeCustoDao(em);
		DebitoDao debitoDao = new DebitoDao(em);
		CreditoDao creditoDao = new CreditoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);
		
		em.getTransaction().begin();
		bancoDao.cadastrar(banco);
		categoriaDao.cadastrar(mercado);
		debitoDao.cadastrar(debitoPrimeiro);
		creditoDao.cadastrar(creditoPrimeiro);
		em.getTransaction().commit();
		em.close();
		*/
	}

}
