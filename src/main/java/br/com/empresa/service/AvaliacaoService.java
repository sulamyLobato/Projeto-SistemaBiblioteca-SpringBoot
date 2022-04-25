package br.com.empresa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.empresa.entity.AlunoDisciplina;
import br.com.empresa.entity.Avaliacao;
import br.com.empresa.repository.AvaliacaoRepository;

@Service
public class AvaliacaoService {

	
		@Autowired
		AvaliacaoRepository repo;
		
		public Avaliacao save(Avaliacao avaliacao) {
			return repo.save(avaliacao);
		}
		
		public List<Avaliacao> findAll(){
			return repo.findAll();
		}
		
		public Avaliacao buscarNotaAlunoDisciplina(AlunoDisciplina alunoDisciplina) {
			return repo.findByAlunoDisciplina(alunoDisciplina);
			
		}
}
