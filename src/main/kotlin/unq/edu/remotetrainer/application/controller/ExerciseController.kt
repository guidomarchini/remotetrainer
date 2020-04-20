package unq.edu.remotetrainer.application.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import unq.edu.remotetrainer.application.controller.exceptions.ExerciseNotFoundException
import unq.edu.remotetrainer.application.sevice.ExerciseService
import unq.edu.remotetrainer.model.Exercise

@RestController
class ExerciseController constructor(
    @Autowired val exerciseService: ExerciseService
){

    @GetMapping("/exercises")
    fun exercises(): Collection<Exercise> {
        return exerciseService.getAllExercises()
    }

    @GetMapping("/exercises/{id}")
    fun getExerciseById(@PathVariable id: Int): Exercise {
        return exerciseService.getExerciseById(id) ?: throw ExerciseNotFoundException(id)
    }

    @PostMapping("/exercises")
    @ResponseStatus(HttpStatus.CREATED)
    fun createExercise(@RequestBody exercise: Exercise): Exercise {
        return exerciseService.createExercise(exercise)
    }

    @DeleteMapping("/exercises/{id}")
    fun deleteExercise(@PathVariable id: Int): ResponseEntity<Unit> {
        exerciseService.deleteExercise(id)
        return ResponseEntity.noContent().build()
    }
}