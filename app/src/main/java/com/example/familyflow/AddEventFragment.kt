package com.example.familyflow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.familyflow.databinding.AddEventBinding

class AddEventFragment : Fragment() {
    private var _binding: AddEventBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AddEventBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val selectedDate = arguments?.getString("selectedDate") ?: "No date selected"
        binding.editTextDate.text = selectedDate
        binding.backButton.setOnClickListener {
            // Navigate back to the previous fragment or activity
            requireActivity().supportFragmentManager.popBackStack()
        }
        binding.button.setOnClickListener {
            val eventName = binding.editTextView.text.toString().trim()
            if (eventName.isEmpty()) {
                binding.editTextView.error = "Event name cannot be empty"
                return@setOnClickListener
            }

            // TODO: Handle event creation and saving logic here
            // For example, you might save the event to a database or display a confirmation message
            Toast.makeText(
                requireContext(),
                "Event added: $eventName on $selectedDate",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}