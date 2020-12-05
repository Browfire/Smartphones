package com.example.smartphones

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.smartphones.model.SmartphoneItem


class FirstFragment : Fragment(), SmartphoneAdapter.PassSmartphoneData {

    private val myViewModel: SmartphoneViewModel by activityViewModels()
    lateinit var mySmartphoneAdapter: SmartphoneAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mySmartphoneAdapter = SmartphoneAdapter(this)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerList)

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = mySmartphoneAdapter

        myViewModel.allSmartphones.observe(viewLifecycleOwner, Observer {
            Log.d("ff44", it.toString())
            mySmartphoneAdapter.updateAdapter(it)
        })

        myViewModel.getOneSmartphoneDetails(1).observe(viewLifecycleOwner, Observer {

        })

    }

    override fun passItemInfo(smartphone: SmartphoneItem) {

        val smartphoneSelectedId = smartphone.id
        myViewModel.smartphoneSelected(smartphoneSelectedId)
        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)

    }
}