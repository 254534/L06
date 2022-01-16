//package com.example.l06
//
//import android.app.Application
//import android.content.Context
//import androidx.annotation.WorkerThread
//import androidx.lifecycle.AndroidViewModel
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.viewModelScope
//import androidx.room.*
//import androidx.room.RoomDatabase
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
//
//@Entity(tableName = "people_table")
//public class Person(
//    @PrimaryKey(autoGenerate = true) val id: Int,
//    val name: String,
//    val occupation: String,
//    val gender: Boolean,
//    val rating: Float,
//    val color: Int
//
//)
//
//@Dao
//interface PersonDao {
//    @Query("SELECT * FROM people_table ORDER BY id ASC")
//    fun readAllData(): LiveData<List<Person>>
//
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    suspend fun addPerson(person: Person)
//
////    @Query("DELETE FROM people_table")
////    suspend fun deleteAll()
//}
//
//@Database(entities = [Person::class], version = 1, exportSchema = false)
//public abstract class PersonRoomDatabase : RoomDatabase() {
//
//    abstract fun personDao(): PersonDao
//
//    companion object {
//        @Volatile
//        private var INSTANCE: PersonRoomDatabase? = null
//
//        fun getDatabase(context: Context): PersonRoomDatabase {
//            val tempInstance = INSTANCE
//            if(tempInstance != null) {
//                return tempInstance
//            }
//
//            synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    PersonRoomDatabase::class.java,
//                    "person_database"
//                ).build()
//                INSTANCE = instance
//                return instance
//            }
//        }
//    }
//}
//
//
//class PersonRepository(private val personDao: PersonDao) {
//
//    val readAllData: LiveData<List<Person>> = personDao.readAllData()
//
////    @Suppress("RedundantSuspendModifier")
////    @WorkerThread
//    suspend fun addPerson(person: Person) {
//        personDao.addPerson(person)
//    }
//}
//
//class UserViewModel(application: Application): AndroidViewModel(application) {
//    private val readAllData: LiveData<List<Person>>
//    private val repository: PersonRepository
//
//    init {
//        val personDao = PersonRoomDatabase.getDatabase(application).personDao()
//        repository = PersonRepository(personDao)
//        readAllData = repository.readAllData
//    }
//
//    fun addPerson(person: Person) {
//        viewModelScope.launch(Dispatchers.IO) {
//            repository.addPerson(person)
//        }
//    }
//}
