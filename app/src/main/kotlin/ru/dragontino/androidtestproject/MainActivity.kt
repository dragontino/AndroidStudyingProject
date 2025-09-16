package ru.dragontino.androidtestproject

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import ru.dragontino.androidtestproject.ui.FragmentActionsHolder

class MainActivity : AppCompatActivity(), FragmentActionsHolder {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
    }

    override fun openAnotherFragment(fragmentClass: Class<out Fragment>) {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace(R.id.fragment_container, fragmentClass, null)
        }
    }
}