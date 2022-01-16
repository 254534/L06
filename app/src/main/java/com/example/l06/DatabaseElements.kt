package com.example.l06

import android.app.Application
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.WorkerThread
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import androidx.room.*
import androidx.room.RoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Entity(tableName = "people_table")
public class Person(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val occupation: String,
    val gender: Boolean,
    val rating: Float,
    val color: Int

)

@Dao
interface PersonDao {
    @Query("SELECT * FROM people_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Person>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addPerson(person: Person)

//    @Query("DELETE FROM people_table")
//    suspend fun deleteAll()
}

@Database(entities = [Person::class], version = 1, exportSchema = false)
public abstract class PersonRoomDatabase : RoomDatabase() {

    abstract fun personDao(): PersonDao

    companion object {
        @Volatile
        private var INSTANCE: PersonRoomDatabase? = null

        fun getDatabase(context: Context): PersonRoomDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PersonRoomDatabase::class.java,
                    "people_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}


class PersonRepository(private val personDao: PersonDao) {

    val readAllData: LiveData<List<Person>> = personDao.readAllData()

//    @Suppress("RedundantSuspendModifier")
//    @WorkerThread
    fun addPerson(person: Person) {
        personDao.addPerson(person)
    }
}

class PersonViewModel(application: Application): AndroidViewModel(application) {
    private val readAllData: LiveData<List<Person>>
    private val repository: PersonRepository

    init {
        val personDao = PersonRoomDatabase.getDatabase(application).personDao()
        repository = PersonRepository(personDao)
        readAllData = repository.readAllData
    }

    fun addPerson(person: Person) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addPerson(person)
        }
    }
}

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {
    private var personList = emptyList<Person>()
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_row, parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = personList[position]
        holder.itemView.findViewById<TextView>(R.id.row_tv1).text = currentItem.name
        holder.itemView.findViewById<TextView>(R.id.row_tv2).text = currentItem.occupation
        holder.itemView.findViewById<ImageView>(R.id.row_image).setBackgroundColor(currentItem.color)

    }

    override fun getItemCount(): Int {
        return personList.size
    }

    fun setData(personList: List<Person>) {
        this.personList = personList
        notifyDataSetChanged()
    }
}