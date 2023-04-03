package com.example.chatapplication.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.example.chatapplication.LoginActivity
import com.example.chatapplication.R
import com.example.chatapplication.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.more_item_layout.*

class ProfileFragment : Fragment() {
lateinit var binding: FragmentProfileBinding
lateinit var progressDialog: Dialog
    private lateinit var mAuth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAuth = FirebaseAuth.getInstance()
        mAuth.currentUser
        binding.moreBtn.setOnClickListener {
            showDialog()
        }
    }

//    mAuth.signOut()
//    val intent2 = Intent(requireActivity(), LoginActivity::class.java)
//    requireActivity().finish()
//    startActivity(intent2)
//    progressDialog.dismiss()
    fun showDialog(){
        AlertDialog.Builder(requireActivity())
            .setMessage("Log out")
            .setPositiveButton(
                "Ok"
            ){ _, _ ->
                mAuth.signOut()
                startActivity(Intent(requireActivity(), LoginActivity::class.java))
                activity?.finish()
                Toast.makeText(requireActivity(), "ok", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton(
                "Cancel"
            ){ dialog,
            _ ->
                dialog.dismiss()
            }.show()
    }

    companion object {

        @JvmStatic
        fun newInstance() = ProfileFragment()
    }


}