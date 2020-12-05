package com.example.smartphones

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.lang.Exception


class SecondFragment : Fragment() {

    private val myViewModel: SmartphoneViewModel by activityViewModels()
    lateinit var nameSmartphone: String
    private var idSmartphone = 0

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        myViewModel.smartphoneSelection.observe(viewLifecycleOwner, Observer {

            idSmartphone = it
            Log.d("id",it.toString())
            myViewModel.getOneSmartphoneDetails(idSmartphone).observe(viewLifecycleOwner, Observer {

                it?.let {

                    val imageSelected = view.findViewById<ImageView>(R.id.imageDetail)
                    Glide.with(this).load(it.image).fitCenter().into(imageSelected)

                    val price = view.findViewById<TextView>(R.id.tvPrice)
                    val name = view.findViewById<TextView>(R.id.tvName)
                    val description = view.findViewById<TextView>(R.id.tvDescription)

                    price.text = it.price.toString()
                    name.text = it.name
                    description.text = it.description

                    nameSmartphone = it.name

                }
            })
        })

        view.findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            val addresses = "info@plaplix.cl"
            val subject = getString(R.string.email_subject) + " " + nameSmartphone + " id " + idSmartphone
            val text = getString(R.string.email_text)
            composeEmail(addresses, subject, text)
        }

        view.findViewById<Button>(R.id.button_second).setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

    }

    fun composeEmail(addresses: String, subject: String, text: String) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.data = Uri.parse("mailto:")
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(addresses))
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
        intent.putExtra(Intent.EXTRA_TEXT, text)

        try {
            startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "e.message", Toast.LENGTH_LONG).show()
        }
    }
}