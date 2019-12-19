package br.com.reminder.resource;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.reminder.model.Lembrete;
import br.com.reminder.repository.ILembrete;

@RestController
@RequestMapping("/lembretes")
public class LembreteResource {

	@Autowired
	private ILembrete lembretes;

	@GetMapping
	public ResponseEntity<List<Lembrete>> listar() {

		List<Lembrete> listaLembretes = lembretes.findAll();

		return ResponseEntity.ok(listaLembretes);
	}

	@GetMapping(value = "/{codigo}")
	public ResponseEntity<Lembrete> buscarPorCodigo(@PathVariable("codigo") Long codigo) {

		Optional<Lembrete> lembrete = lembretes.findById(codigo);

		if (lembrete.isPresent())
			return ResponseEntity.ok(lembrete.get());

		return ResponseEntity.notFound().build();

	}

	@PostMapping
	public ResponseEntity<Void> salvar(@RequestBody @Valid Lembrete lembrete) {

		boolean existsLembreteById = lembretes.existsById(lembrete.getCodigo());

		if (existsLembreteById)
			return ResponseEntity.badRequest().build();

		else {
			lembrete.setData(LocalDateTime.now());
			lembrete = lembretes.save(lembrete);

			URI uri = ServletUriComponentsBuilder
					.fromCurrentRequest()
					.path("/{codigo}")
					.buildAndExpand(lembrete.getCodigo())
					.toUri();
			
			return ResponseEntity.created(uri).build();
		}
	}
	
	@PutMapping(value = "/{codigo}")
	public ResponseEntity<Lembrete> editar(@PathVariable("codigo") Long codigo, @RequestBody Lembrete lembrete) {
		
		if (lembretes.existsById(codigo)) {
			lembrete.setCodigo(codigo);
			lembrete.setData(LocalDateTime.now());
			lembrete = lembretes.save(lembrete);
			
			return ResponseEntity.accepted().body(lembrete);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping(value = "/{codigo}")
	public ResponseEntity<Void> excluir(@PathVariable("codigo") Long codigo) {
		
		boolean lembreteExistsById = lembretes.existsById(codigo);
		
		if (lembreteExistsById) {
			lembretes.deleteById(codigo);
			
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.notFound().build();
	}
}