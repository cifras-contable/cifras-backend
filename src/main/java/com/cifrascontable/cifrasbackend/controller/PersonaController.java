package com.cifrascontable.cifrasbackend.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class PersonaController {

    /*@GetMapping("/persona")
    public PersonaResponseDTO firstPage( HttpServletRequest request, HttpServletRequest response ) {

        PersonaResponseDTO persona = PersonaResponseDTO.builder()
            .id(1L)
            .nombre_y_apellido("Juan", "Perez")
            .email("asd@gmail.com")
            .tipo_documento("dni")
            .numero_documento("1234")
            .build();

        return persona;
    }

    private final PersonaService personaService;

    @GetMapping("/persona")
    public List<PersonaResponseDTO> getAllComments() {
        return personaService.list();
    }

    @PostMapping("/persona")
    public PersonaResponseDTO createComment(@RequestBody PersonaRequestDTO p) {
        return personaService.save(p);
    }

    @GetMapping("/persona/{id}")
    public PersonaResponseDTO getPersonaById(@PathVariable(value = "id") Long personaId) {
        return personaService.get(personaId)
            .orElseThrow(() -> new ResourceNotFoundException("Persona", "id", personaId));
    }*/

//    @PutMapping("/persona/{id}")
//    public PersonaResponseDTO updatePersona(@PathVariable(value = "id") Long personaId, @RequestBody Persona personaDetails) {
//
//        Persona persona = personaService.get(personaId)
//            .orElseThrow(() -> new ResourceNotFoundException("Comment", "id", personaId));
//
//        comment.setPostName(personaDetails.getPostName());
//        comment.setComment(personaDetails.getComment());
//
//        Comment updatedComment = commentRepository.save(comment);
//        return updatedComment;
//    }

//    @DeleteMapping("/persona/{id}")
//    public ResponseEntity<?> deletePersona(@PathVariable(value = "id") Long personaId) {
//        Persona persona = personaService.get(personaId)
//            .orElseThrow(() -> new ResourceNotFoundException("Persona", "id", personaId));
//
//        commentRepository.delete(comment);
//
//        return ResponseEntity.ok().build();
//    }

}
