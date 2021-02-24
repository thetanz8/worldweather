package com.test.worldwether.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.test.worldwether.databinding.ActivityMainBinding
import com.test.worldwether.gone
import com.test.worldwether.visible

class HomeScreenActivity : AppCompatActivity() {

    private val viewModel by viewModels<HomeScreenViewModel>()

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val textWatcherList: MutableList<TextWatcher>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initDefaultSearchBox()
        initObserve()
    }

    private fun initObserve() {
        viewModel.serviceState.observe(this, Observer { state ->
            if (state) {
                showRecyclerView()
            } else {
                hideRecyclerView()
            }
        })
    }

    private fun initDefaultSearchBox() {
        binding.apply {
            layoutSearchBox.apply {
                btnClose.setOnClickListener {
                    removeAllTextChangedListeners()
                    layoutSearchBox.editTextViewSearch.setText("")
                }
            }
        }
    }

    private fun getTextWatcher() = object : TextWatcher {
        override fun afterTextChanged(editText: Editable?) = Unit

        override fun beforeTextChanged(
            charSequence: CharSequence?,
            start: Int,
            count: Int,
            after: Int
        ) = Unit

        override fun onTextChanged(
            charSequence: CharSequence?,
            start: Int,
            brfore: Int,
            count: Int
        ) {
            if (charSequence.isNullOrEmpty()) {
                binding.layoutSearchBox.btnClose.gone()
                hideRecyclerView()
            } else {
                binding.layoutSearchBox.btnClose.visible()
                if (charSequence.isNotEmpty()) {

                } else {
                    hideRecyclerView()
                }
            }
        }
    }

    private fun addTextChangedListener(watcher: TextWatcher?) {
        watcher?.also {
            textWatcherList?.add(it)
            binding.layoutSearchBox.editTextViewSearch.addTextChangedListener(it)
        }
    }

    private fun removeAllTextChangedListeners() {
        textWatcherList?.also {
            for (watcher in it) {
                binding.layoutSearchBox.editTextViewSearch.removeTextChangedListener(watcher)
            }
            it.clear()
        }
    }

    override fun onBackPressed() {
        finish()
    }

    private fun showRecyclerView() {
        binding.apply {
            recyclerViewBody.visible()
            linearLayoutEmpty.gone()
        }
    }

    private fun hideRecyclerView() {
        binding.apply {
            recyclerViewBody.gone()
            linearLayoutEmpty.visible()
        }
    }
}