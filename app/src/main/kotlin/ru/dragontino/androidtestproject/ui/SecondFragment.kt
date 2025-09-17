package ru.dragontino.androidtestproject.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import ru.dragontino.androidtestproject.databinding.FragmentSecondBinding
import ru.dragontino.androidtestproject.utils.applyWindowInsets

class SecondFragment : Fragment() {
    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.root.applyWindowInsets(WindowInsetsCompat.Type.systemBars())

        binding.openPreviousScreen.setOnClickListener {
            (requireActivity() as FragmentActionsHolder).openFragment<FirstFragment>()
        }

        binding.openNextScreen.setOnClickListener {
            (requireActivity() as FragmentActionsHolder).openFragment<ThirdFragment>()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}