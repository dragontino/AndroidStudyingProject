package ru.dragontino.androidtestproject.ui

import androidx.fragment.app.Fragment

internal interface FragmentActionsHolder {
    fun openAnotherFragment(fragmentClass: Class<out Fragment>)

}

internal inline fun <reified F : Fragment> FragmentActionsHolder.openFragment() {
    openAnotherFragment(F::class.java)
}