package com.example.l06

import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Fragment3 : Fragment() {
    private lateinit var mPersonViewModel: PersonViewModel
    private lateinit var adapter: ListAdapter
    companion object {
        var currentChosen: Int = -1
    }

    fun addPersonDatabase(position: Int?, name: String?, occupation: String?, gender: Boolean?, rating: Float?, color: Int?) {

        if(position == -1) {
            val person = Person(0, name!!, occupation!!, gender!!, rating!!, color!!)
            mPersonViewModel.addPerson(person)
        }
        else {
            val person = Person(position!!, name!!, occupation!!, gender!!, rating!!, color!!)
            mPersonViewModel.updatePerson(person)
        }
//        Toast.makeText(requireContext(), position.toString(), Toast.LENGTH_LONG).show()
    }

    fun update_displayed() {
        if (adapter.personList.isNotEmpty()) {
            val currentPerson: Person = adapter.personList.first { it.id == currentChosen }
            val name: String = currentPerson.name
            val gender: Boolean = currentPerson.gender
            val occupation: String = currentPerson.occupation
            val color: Int = currentPerson.color
            val rating: Float = currentPerson.rating

            val bundle = Bundle()

            bundle.putInt("position", currentChosen)
            bundle.putString("name", name)
            bundle.putBoolean("gender", gender)
            bundle.putString("occupation", occupation)
            bundle.putInt("color", color)
            bundle.putFloat("rating", rating)

            childFragmentManager.setFragmentResult("f3", bundle)
        }
    }

    override fun onResume() {
        super.onResume()
        if(currentChosen == -1 && adapter.personList.isNotEmpty()) {
            currentChosen = adapter.personList[0].id
        }
        update_displayed()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().findViewById<BottomNavigationView>(R.id.bottom_nav).visibility = View.VISIBLE

        parentFragmentManager.setFragmentResultListener("f3_altered", viewLifecycleOwner) {
                _, bundle ->
            val position = bundle.getInt("position")
            val name = bundle.getString("name")
            val gender = bundle.getBoolean("gender")
            val occupation = bundle.getString("occupation")
            val temp_color = bundle.getInt("color")
            val rating = bundle.getFloat("rating")

            addPersonDatabase(position, name, occupation, gender, rating, temp_color)
            update_displayed()
        }

        var fab: FloatingActionButton = view.findViewById(R.id.floatingActionButton)
        fab.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("position", -1)
            parentFragmentManager.setFragmentResult("f3_edit", bundle)
            requireActivity().findViewById<BottomNavigationView>(R.id.bottom_nav).visibility = View.GONE
            findNavController().navigate(R.id.action_global_fragmentEdit)
        }

        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            if(currentChosen == -1 && adapter.personList.isNotEmpty()) {
                currentChosen = adapter.personList[0].id
            }
            update_displayed()
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_3, container, false)

        adapter = ListAdapter()
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mPersonViewModel = ViewModelProvider(this).get(PersonViewModel::class.java)
        mPersonViewModel.readAllData.observe(viewLifecycleOwner, Observer { user ->
            adapter.setData(user)
        })
        return view
    }
}