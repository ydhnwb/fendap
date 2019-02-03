package com.starla.fendapbengkulu.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.starla.fendapbengkulu.R
import com.starla.fendapbengkulu.adapters.AboutAdapter
import com.starla.fendapbengkulu.models.Person
import kotlinx.android.synthetic.main.fragment_about.view.*

class FragmentAbout : Fragment() {

    private var listOfPerson : MutableList<Person> = mutableListOf()
    private lateinit var mAdapter: AboutAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) : View? {
      return inflater.inflate(R.layout.fragment_about, null, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listOfPerson.add(Person("Henny Tri Utami","hennytu@gmail.com",R.drawable.ic_about_1))
        listOfPerson.add(Person("Rifa Fariza Adhyma Saputra","rifariza@gmail.com",R.drawable.ic_about_2))
        listOfPerson.add(Person("Yogi Kurniawan","yogikurniawan@gmail.com",R.drawable.ic_about_3))
        System.out.println(listOfPerson)
        view.rv_about.layoutManager = GridLayoutManager(activity, 2)
        mAdapter = AboutAdapter(listOfPerson, activity!!)
        view.rv_about.adapter = mAdapter
    }
}