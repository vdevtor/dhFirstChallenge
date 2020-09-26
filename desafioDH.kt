import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


fun main() {
    var mainTeacher1 = MainTeacher("Bruno", "Xavier", LocalDateTime.now(), 10, "DATA BASE")
    var mainTeacher2 = MainTeacher("Vanessa", "Claudia", LocalDateTime.now(), 20, "ANALISE DE SISTEMAS")
    var assistantTeacher1 = AssistantTeacher("Diego", "Lopes Matos", LocalDateTime.now(), 11, 30)
    var assistantTeacher2 = AssistantTeacher("Beatriz", "Zatra", LocalDateTime.now(), 21, 45)
    var student1 = Student("Vitor", "Soares Silva", 1000)
    var student2 = Student("Fabio", "Munhoz", 2000)
    var studentlistInit: MutableList<Student>? = mutableListOf(student1, student2)
    var course1 = Course("DATABASE", 100, mainTeacher1, assistantTeacher1, 35, studentlistInit)
    var course2 = Course("Kotlin", 200, mainTeacher2, assistantTeacher2, 35, studentlistInit)
    var courseListInit: MutableList<Course>? = mutableListOf(course1, course2)
    var teacherListInit: MutableList<Teacher>? =
        mutableListOf(mainTeacher1, mainTeacher2, assistantTeacher1, assistantTeacher2)
    var enrollmentListInit: MutableList<Enrollment>? = null
    var digitalHouseManager = DigitalHouseManager(studentlistInit, teacherListInit, courseListInit, enrollmentListInit)
//    println(courseListInit)
//    println("....................................................................")
//    digitalHouseManager.registerCourse("Ciencias da Computação",300,35)
//    println(courseListInit)
//    println("....................................................................")
//    digitalHouseManager.removeCourse(300)
//    println(courseListInit)
    println("....................................................................")
//    digitalHouseManager.registerAssistantTeacher("Marcos","oliver",31,22)
//    println(teacherListInit)

//   digitalHouseManager.registerMainTeacher("Daniela","Pavarotti",30,"Java Script", LocalDateTime.now())
//         println(teacherListInit)
//        digitalHouseManager.removeTeacher(10)
//    println("....................................................................")
//    println(teacherListInit)
//        println(studentlistInit)
//        digitalHouseManager.registerStudent("Enola","Holmes",3000)
//        println(studentlistInit)


//    digitalHouseManager.returnStudent(2000)
//    digitalHouseManager.returnCourse(100)

//    println(enrollmentListInit)
//   enrollmentListInit= digitalHouseManager.enrollStudent(2000,100)
//
//    println(enrollmentListInit)

//    println(courseListInit)
//        digitalHouseManager.requestTeacher(100,20,21)
//    println(courseListInit)



    println(teacherListInit)

}

//Criando a class DH manager e seus metodos

open class DigitalHouseManager(
    var studentList: MutableList<Student>?, var teacherList: MutableList<Teacher>?,
    var courseList: MutableList<Course>?, var enrollmentList: MutableList<Enrollment>?
) {
    //Iniciando variaveis
    var contains = false

    //Criando metodo que permite cadastras um novo curso a lista de cursos
    fun registerCourse(name: String, cCode: Int, amountOfStudent: Int) {
        courseList?.add(Course(name, cCode, null, null, amountOfStudent, null))
        println("O curso ${name} foi adicionado com sucesso a lista de cursos!!")
        println("....................................................................")
    }

    //criando metodo que permite remover um curso da lista de cursos pelo codigo
    fun removeCourse(cCode: Int) {
        for (course in courseList!!) {
            if (course.courseCode == cCode) {
                contains = true
                courseList?.remove(course)
                println("Curso Removido com Sucesso!!")
                break
            }

        }
        if (!contains)
            println("Curso não encontrado no sistema!")

    }

    //metodo que permite cadastrar um professor adjunto

    fun registerAssistantTeacher(name: String, surname: String, aTCode: Int, monitorTime: Int) {

        teacherList?.add(AssistantTeacher(name, surname, null, aTCode, monitorTime))
        println("Professor(a) Adicionado com Sucesso a Lista!!")


    }

    //metodo que permite criar professor titular

    fun registerMainTeacher(
        name: String, surname: String, mTCode: Int, expertise: String,
        serviceTime: LocalDateTime?
    ) {

        teacherList?.add(MainTeacher(name, surname, null, mTCode, expertise))
        println("Professor(a) Adicionado com Sucesso a Lista !!")
    }

    //Criando metodo que permite excluir professor da lista

    fun removeTeacher(tCode: Int) {
        var contains = false
        for (teacher in teacherList!!) {
            if (teacher.tCode == tCode) {
                contains = true
                teacherList?.remove(teacher)
                println("O prefessor foi removido com sucesso!!!")
                break
            }
        }
        if (!contains)
            println("Não foi possivel localizar o professor no sistema !")
    }


    // Criando metodo que permite registrar aluno...

    fun registerStudent(name: String, surname: String, sCode: Int) {
        studentList?.add(Student(name, surname, sCode))
        println("Aluno Incluido com Sucesso!!")

    }

// criando função que retorna auluno pelo codigo de aluno

    open fun returnStudent(sCode: Int): Student? {
        var containsStudent = false
        var studentName: String
        var aux1: Student? = null
        for (student in studentList!!) {

            if (student.sCode == sCode) {
                containsStudent = true
                println("Localizando Aluno")
                println("..")
                println("...")
                println("....")
                println(".......")
                studentName = student.name
                println("O Aluno: ${studentName} ${student.surname}  foi localizado!!")
                aux1 = student
                break

            }
        }
        if (!containsStudent) {
            println("Aluno não Encontrado no sistema")

        }

        return aux1

    }

    // criando função que retorna um curso pelo codigo

    open fun returnCourse(cCode: Int): Course? {
        var contains = false
        var courseName: String
        var auxCourse: Course? = null

        for (course in courseList!!) {
            if (course.courseCode == cCode) {
                contains = true
                println("Localizando Curso")
                println("..")
                println("...")
                println("....")
                println(".......")
                courseName = course.courseName
                auxCourse = course
                println("O curso : ${courseName} foi localizado !!")
                break
            }
        }
        if (!contains) {
            println("Nao foi possivel localizar o curso")

        }

        return auxCourse
    }

    //Criando metodo que permite Matricular aluno em curso

    fun enrollStudent(sCode: Int, cCode: Int): MutableList<Enrollment>? {

        var auxCourse = returnCourse(cCode)
        var auxStudent = returnStudent(sCode)



        if (auxStudent != null && auxCourse != null && auxCourse.maxAmountOfStudents <= 35) {

            var enrolling = Enrollment(auxStudent, auxCourse, LocalDateTime.now())
            enrollmentList?.add(0, enrolling)
            println(
                "Matricula Realizada com sucesso, O aluno ${auxStudent.name} " +
                        "foi matriculado no curso ${auxCourse.courseName} !!"
            )


        } else {
            println("Impossivel realizar a matricula")

        }
        return enrollmentList

    }

    //criando metodo que permite alocar professor a um curso

    fun requestTeacher(cCode: Int, mTCode: Int, aTCode: Int) {
        var auxMainTeacher: Teacher? = null
        var auxAssistantTeacher: Teacher? = null
        var auxCourse: Course? = null
        var containsTeacher = false
        var containsATeacher = false

        for (mainTeacher in teacherList!!) {
            if (mainTeacher.tCode == mTCode) {
                containsTeacher = true
                println("professor titular encontrado")
                auxMainTeacher = mainTeacher
                break

            }
        }
        if (!containsTeacher) {
            println("Professor titular não encontrado")
        }

        for (assistantTeacher in teacherList!!) {
            if (assistantTeacher.tCode == aTCode) {
                containsATeacher = true
                println("Professor Assistente encontrado")
                auxAssistantTeacher = assistantTeacher
                break

            }
        }
        if (!containsATeacher) {
            println("Professor Assistente não encontrado!!")

        }
        for (courseAux in courseList!!) {
            if (courseAux.courseCode == cCode) {
                println("O curso ${courseAux.courseName} foi localizado no sistema  !")
                auxCourse = courseAux
                break

            } else {
                println("Curso não encontrado no sistema!!")
            }
        }

        if (auxCourse != null && auxAssistantTeacher != null && auxMainTeacher != null) {

            var mTeacher = auxMainTeacher as? MainTeacher
            var aTeacher = auxAssistantTeacher as? AssistantTeacher
            auxCourse.mainTeacher = mTeacher
            auxCourse.assistantTeacher = aTeacher


        }

    }

}

//Criando a class Matricula e seus metodos
class Enrollment(var student: Student, var course: Course, var enrollmentDate: LocalDateTime = LocalDateTime.now()) {


}

//criando a class Curso...

class Course(
    var courseName: String, var courseCode: Int, var mainTeacher: MainTeacher?, var assistantTeacher: AssistantTeacher?,
    var maxAmountOfStudents: Int, var enrolledStudent: MutableList<Student>?
) {

    var isPossible = false

    override fun equals(other: Any?): Boolean {
        return other?.let {
            var course = (it as Course)
            course?.let {
                it.courseCode == courseCode
            } ?: false
        } ?: false
    }

    fun addStudent(student: Student): Boolean {
        if (maxAmountOfStudents < 35) {
            isPossible = true
            enrolledStudent?.add(student)
            maxAmountOfStudents++
            println("O Aluno${student.name} foi matriculado com sucesso no curso ${courseName}")
        }
        if (maxAmountOfStudents >= 35) {
            isPossible = false
            println("Não foi possivel adicionar o aluno ${student.name}, pois não há vagas!!")
        } else if (student !is Student) {
            try {
                isPossible = false
                student as Student
            } catch (e: Exception) {
                e.localizedMessage
                e.stackTrace
                println("Impossivel Matricular pois não é um Aluno Elegivel !")
            }
        }
        return isPossible
    }

    fun removeStudent(student: Student): Boolean {
        var isPossible = false
        try {
            enrolledStudent?.remove(student)
            isPossible = true
            println("O Aluno ${student.name} foi removido com sucesso!")
        } catch (e: Exception) {
            println(e.localizedMessage)
            println(e.stackTrace)
        }
        return isPossible
    }

    override fun toString(): String {
        return "O curso:'$courseName', possui o codigo: $courseCode, e seu professor titular é ${mainTeacher?.name} ${mainTeacher?.surname})  "
    }

}

//criando a class Professor  seus atributos

open class Teacher(val name: String, val surname: String, var serviceTime: LocalDateTime? = LocalDateTime.now(), val tCode: Int) {
    val formatter = DateTimeFormatter.ofPattern("dd. MMMM. yyyy")
    val formattedString = serviceTime?.format(formatter)



    override fun equals(other: Any?): Boolean {
        return other?.let {
            var teacher = (it as Teacher)
            teacher?.let {
                it.tCode == tCode
            } ?: false
        } ?: false
    }

    override fun toString(): String {
        return "Professor $name $surname, Data de Admissão $formattedString, Codigo de Registro $tCode "
    }



}


//Criando a classe professor titular e seus paramentros

open class MainTeacher(
    name: String, surname: String, serviceTime: LocalDateTime?, var mTCode: Int,
    var expertise: String
) : Teacher(name, surname, serviceTime, mTCode) {

    override fun equals(other: Any?): Boolean {
        return other?.let {
            var mainTeacher = (it as Teacher)
            mainTeacher?.let {
                it.tCode == tCode
            } ?: false
        } ?: false
    }

}

// Criando a classe Professor Adjunto e seus atributos

open class AssistantTeacher(
    name: String,
    surname: String,
    serviceTime: LocalDateTime?,
    var aTCode: Int,
    var monitorTime: Int
) :
    Teacher(name, surname, serviceTime, aTCode) {



    override fun equals(other: Any?): Boolean {
        return other?.let {
            var aTeacher = (it as Teacher)
            aTeacher?.let {
                it.tCode == tCode
            } ?: false
        } ?: false
    }

}

// criando a class Aluno e seus atributos

class Student(val name: String, val surname: String, var sCode: Int) {
    override fun equals(other: Any?): Boolean {
        return other?.let {
            var aluno = (it as Student)
            aluno?.let {
                it.sCode == sCode
            } ?: false
        } ?: false
    }

    override fun toString(): String {
        return "Aluno: $name $surname', RA: $sCode)"
    }
}

