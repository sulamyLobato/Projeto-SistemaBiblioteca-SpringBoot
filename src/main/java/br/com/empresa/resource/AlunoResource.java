package br.com.empresa.resource;

import java.net.URI;
import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.empresa.entity.Aluno;
import br.com.empresa.service.AlunoService;

@RestController
@RequestMapping("/aluno")
public class AlunoResource {
	
	@Autowired
	private AlunoService alunoService;
	
		@RequestMapping(method=RequestMethod.GET)
		public ResponseEntity<List<Aluno>> listarAluno(){
			List<Aluno> alunos = alunoService.listaTodosAlunos();
			
			return ResponseEntity.ok().body(alunos);
		}
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Aluno> buscaPorID(@PathVariable Integer id) throws ObjectNotFoundException{
		Aluno aluno = alunoService.buscarPorID(id);
		return ResponseEntity.ok().body(aluno);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> inserir(@RequestBody Aluno objAluno){
		Aluno aluno = alunoService.salvar(objAluno);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(aluno.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> excluirs(@PathVariable Integer id){
		alunoService.excluir(id);
		return ResponseEntity.noContent().build();

	}
	@RequestMapping(value="/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> alterar(@RequestBody Aluno objAluno, @PathVariable Integer id){
		objAluno.setId(id);
		alunoService.alterar(objAluno);
		return ResponseEntity.noContent().build();
	}
	}
		

