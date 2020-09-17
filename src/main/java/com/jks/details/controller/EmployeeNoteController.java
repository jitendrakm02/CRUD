package com.jks.details.controller;

import java.util.List;

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

import com.jks.details.exception.ResourceNotFoundException;
import com.jks.details.model.EmployeeNote;
import com.jks.details.repository.EmployeeNoteRepository;

@RestController
@RequestMapping("/employee")//api==employee
public class EmployeeNoteController {
	
	@Autowired
	EmployeeNoteRepository empNoteRepository;
	
	// Get All Employee Notes
	
	@GetMapping("/employeenotes")
	public List<EmployeeNote> getAllNotes() {
	    return empNoteRepository.findAll();
	}

    // Create a new Employee Note
	
		@PostMapping("/employeenotes")
		public EmployeeNote createNote(@Valid @RequestBody EmployeeNote emp) {
		    return empNoteRepository.save(emp);
		}

    // Get a Single Employee Note
		
		@GetMapping("/employeenotes/{id}")
		public EmployeeNote getNoteById(@PathVariable(value = "id") Long empnoteId) {
		    return empNoteRepository.findById(empnoteId)
		            .orElseThrow(() -> new ResourceNotFoundException("EmployeeNote", "id", empnoteId));
		}

    // Update a Employee Note
		
		@PutMapping("/employeenotes/{id}")
		public EmployeeNote updateNote(@PathVariable(value = "id") Long empnoteId,
		                                        @Valid @RequestBody EmployeeNote empnoteDetails) {

		    EmployeeNote empnote = empNoteRepository.findById(empnoteId)
		            .orElseThrow(() -> new ResourceNotFoundException("EmployeeNote", "id", empnoteId));
            
		    empnote.setName(empnoteDetails.getName());
		    empnote.setEmail(empnoteDetails.getEmail());
		    empnote.setPassword(empnoteDetails.getPassword());
		    empnote.setTitle(empnoteDetails.getTitle());
		    empnote.setContent(empnoteDetails.getContent());

		    EmployeeNote updatedNote = empNoteRepository.save(empnote);
		    return updatedNote;
		}

    // Delete a Employee Note
		
		@DeleteMapping("/employeenotes/{id}")
		public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long empnoteId) {
		    EmployeeNote note = empNoteRepository.findById(empnoteId)
		            .orElseThrow(() -> new ResourceNotFoundException("EmployeeNote", "id", empnoteId));

		    empNoteRepository.delete(note);

		    return ResponseEntity.ok().build();
		}

}
