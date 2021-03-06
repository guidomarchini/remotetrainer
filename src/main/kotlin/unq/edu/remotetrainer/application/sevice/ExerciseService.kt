package unq.edu.remotetrainer.application.sevice

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import unq.edu.remotetrainer.mapper.ExerciseMapper
import unq.edu.remotetrainer.model.Exercise
import unq.edu.remotetrainer.persistence.entity.ExerciseEntity
import unq.edu.remotetrainer.persistence.repository.ExerciseRepository

@Service
class ExerciseService @Autowired constructor(
    override val repository: ExerciseRepository,
    override val mapper: ExerciseMapper
): RemoteTrainerService<Exercise, ExerciseEntity> {

    fun getOrderedByName(): List<Exercise> {
        return repository.getAllByOrderByName()
                .map { mapper.toModel(it) }
    }

    fun getExerciseByName(name: String): Exercise? {
        return repository.getByName(name)
            ?.let { mapper.toModel(it) }
    }

    fun getExerciseWithNameIn(names: List<String>): List<Exercise> {
        return repository.getByNameIn(names)
            .map { mapper.toModel(it) }
    }
}