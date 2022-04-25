package br.com.empresa.service;

import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.empresa.entity.Disciplina;
import br.com.empresa.repository.DisciplinaRepository;

@Service
public class DisciplinaService {
	
	@Autowired
	private DisciplinaRepository disciplinaRepo;
	
	public List<Disciplina> listaTodasDisciplinas(){
		return disciplinaRepo.findAll();	
	}
	public Disciplina buscaPorID(Integer id) throws ObjectNotFoundException{
		java.util.Optional<Disciplina> disciplina = disciplinaRepo.findById(id);
		return disciplina.orElseThrow(()-> new ObjectNotFoundException(null, "Disciplina não encontrada"));
		
	}
	
	public Disciplina salvar(Disciplina disciplina) {
		return disciplinaRepo.save(disciplina);
	}
	
	public void excluir (Integer id) {
		disciplinaRepo.deleteById(id);
		
	}
	public Disciplina alterar(Disciplina objDisciplina) {
		Disciplina disc = buscaPorID(objDisciplina.getId());
		disc.setNome(objDisciplina.getNome());
		return salvar(disc);
		
	}
}
