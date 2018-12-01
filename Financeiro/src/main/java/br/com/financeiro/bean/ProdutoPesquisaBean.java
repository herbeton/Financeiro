package br.com.financeiro.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.financeiro.dao.ProdutoDAO;
import br.com.financeiro.domain.Produto;

@ManagedBean
@ViewScoped
public class ProdutoPesquisaBean {

	private Produto produto;
	private boolean exibir;

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	public boolean isExibir() {
		return exibir;
	}

	public void setExibir(boolean exibir) {
		this.exibir = exibir;
	}
	
	@PostConstruct
	public void novo() {
		produto = new Produto();
	}
	
	public void buscar() {
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			Produto resultado = produtoDAO.buscar(produto.getCodigo());
			if(produto == null) {
				Messages.addGlobalWarn("Nao existe produto!");
				exibir = false;
			}else {
				produto = resultado;
				exibir = true;
			}
		}
		catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar buscar o produto!");
			erro.printStackTrace();
		}
	}

}
