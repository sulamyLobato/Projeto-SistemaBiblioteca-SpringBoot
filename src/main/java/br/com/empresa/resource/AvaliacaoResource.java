package br.com.empresa.resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.empresa.entity.Aluno;
import br.com.empresa.entity.AlunoDisciplina;
import br.com.empresa.entity.Avaliacao;
import br.com.empresa.entity.Disciplina;
import br.com.empresa.service.AvaliacaoService;

@RestController
@RequestMapping("/avaliacao")
public class AvaliacaoResource {
	
	@Autowired
	private AvaliacaoService service;

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Avaliacao>> listarAvaliacao(){
		List<Avaliacao> listaAvaliacao = service.findAll();
		return ResponseEntity.ok().body(listaAvaliacao);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> inserir (@RequestBody Avaliacao objAvaliacao){
		
		service.save(objAvaliacao);
		objAvaliacao = service.save(objAvaliacao);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(objAvaliacao.getAlunoDisciplina()).toUri();
		return ResponseEntity.created(uri).build();
	}
	@RequestMapping(value="/{idAluno}/{idDisciplina}", method=RequestMethod.GET)
	public ResponseEntity <Avaliacao> buscarAvaliacaoPorDisciplina(@PathVariable Integer idAluno, @PathVariable Integer idDisciplina){
		Aluno aluno = new Aluno ();
		aluno.setId(idAluno);
		
		Disciplina disciplina = new Disciplina();
		disciplina.setId(idDisciplina);
		
		
		AlunoDisciplina alunoDisciplina = new AlunoDisciplina();
		alunoDisciplina.setAluno(aluno);
		alunoDisciplina.setDisciplina(disciplina);
		
		service.buscarNotaAlunoDisciplina(alunoDisciplina);
		Avaliacao avaliacao = service.buscarNotaAlunoDisciplina(alunoDisciplina);
		return ResponseEntity.ok().body(avaliacao);
	}
}
