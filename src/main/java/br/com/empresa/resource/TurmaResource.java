package br.com.empresa.resource;

import java.net.URI;
import java.util.List;

import org.hibernate.ObjectDeletedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.empresa.entity.Turma;
import br.com.empresa.service.TurmaService;

@RestController
@RequestMapping("/turma")
public class TurmaResource {

	
	@Autowired
	private TurmaService turmaService;
	
	
	//@RequestMapping(method = RequestMethod.GET)- mesma coisa da linha debaixo(getmapping)
	
	@GetMapping 
	public ResponseEntity<List<Turma>> listarTurmas(){
		List<Turma> turmas = turmaService.listaTodasTurmas();
		return ResponseEntity.ok().body(turmas);	
		}
	
	@RequestMapping(value= "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Turma> buscaPorID(@PathVariable Integer id) throws ObjectDeletedException{
		Turma turma =  turmaService.buscarPorID(id);
		return ResponseEntity.ok().body(turma);
	}
	@RequestMapping (method = RequestMethod.POST) 
	//@PostMapping - same thing as above
	public ResponseEntity<Void> inserir(@RequestBody Turma turma){
		Turma objTurma = turmaService.salvar(turma);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
				buildAndExpand(objTurma.getId()).toUri();
		return ResponseEntity.created(uri).build();

	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> excluir (@PathVariable Integer id){
		turmaService.excluir(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> alterar(@RequestBody Turma objTurma, @PathVariable Integer id){
		objTurma.setId(id);
		turmaService.alterar(objTurma);
		return ResponseEntity.noContent().build();
	}
}
