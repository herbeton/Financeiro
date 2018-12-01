package br.com.financeiro.bean;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.financeiro.dao.HistoricoDAO;
import br.com.financeiro.dao.ProdutoDAO;
import br.com.financeiro.domain.Historico;
import br.com.financeiro.domain.Produto;

@ManagedBean
@ViewScoped
public class ProdutoPesquisaBean {

	private Produto produto;
	private boolean exibir;
	private Historico historico;

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	public boolean getExibir() {
		return exibir;
	}

	public void setExibir(boolean exibir) {
		this.exibir = exibir;
	}
	
	public Historico getHistorico() {
		return historico;
	}
	
	public void setHistorico(Historico historico) {
		this.historico = historico;
	}
	
	@PostConstruct
	public void novo() {
		historico = new Historico();
		produto = new Produto();
		exibir = false;
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
	
	public void salvar() {
		try {
			historico.setHorario(new Date());
			historico.setProduto(produto);
			
			HistoricoDAO historicoDAO = new HistoricoDAO();
			historicoDAO.salvar(historico);
			
			Messages.addGlobalInfo("Histórico salvo com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o histórico");
			erro.printStackTrace();
		}
	}

}
