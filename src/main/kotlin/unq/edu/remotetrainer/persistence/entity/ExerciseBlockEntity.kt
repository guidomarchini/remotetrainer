package unq.edu.remotetrainer.persistence.entity

import javax.persistence.*

@Entity
class ExerciseBlockEntity (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @OneToMany(targetEntity = ExerciseRepetitionEntity::class)
    var exercises: Collection<ExerciseRepetitionEntity>,

    var notes: String
)