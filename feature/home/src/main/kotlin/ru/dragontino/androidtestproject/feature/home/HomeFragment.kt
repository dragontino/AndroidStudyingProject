package ru.dragontino.androidtestproject.feature.home

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.dragontino.androidtestproject.feature.home.di.HomeComponent
import javax.inject.Inject

class HomeFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var homeViewModel: HomeViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireContext().applicationContext as HomeComponentProvider)
            .provideHomeComponent()
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel = ViewModelProvider(this, factory = viewModelFactory)[HomeViewModel::class]
    }
}


interface HomeComponentProvider {
    fun provideHomeComponent(): HomeComponent
}