package com.example.chatapplication.fragment

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapplication.*
import com.example.chatapplication.R
import com.example.chatapplication.databinding.FragmentHomeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class HomeFragment : Fragment() {

    lateinit var binding : FragmentHomeBinding

    private lateinit var userRecyclerView: RecyclerView
    private lateinit var userList: ArrayList<User>
    private lateinit var adapter: UserAdapter
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false)
       return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAuth = FirebaseAuth.getInstance()
        mAuth.currentUser

        userList = ArrayList()

        mDbRef = FirebaseDatabase.getInstance().reference
        userRecyclerView = binding.userRecyclerView
        userRecyclerView.layoutManager = LinearLayoutManager(requireActivity())
        adapter = UserAdapter(requireActivity(), userList)
        userRecyclerView.adapter = adapter

        mDbRef.child("user").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                userList.clear()

                for (postSnapshot in snapshot.children) {
                    val currentUser = postSnapshot.getValue(User::class.java)
                    if (mAuth.currentUser?.uid != currentUser?.uid) {
                        userList.add(currentUser!!)
                    }
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }

//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        inflater.inflate(R.menu.menu, menu)
//        return super.onCreateOptionsMenu(menu, inflater)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        if (item.itemId == R.id.logut) {
//            mAuth.signOut()
//            val intent2 = Intent(requireActivity(), LoginActivity::class.java)
//            requireActivity().finish()
//            startActivity(intent2)
//
//            return true
//        }
//        if(item.itemId == R.id.settings){
//            val intent = Intent(requireActivity(), ChangePassCodeActiviy::class.java)
//            startActivity(intent)
//            requireActivity().finish()
//            return true
//        }
//        return true
//
//    }
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        if (item.itemId == R.id.logut) {
//            mAuth.signOut()
//            val intent2 = Intent(this@MainActivity, LoginActivity::class.java)
//            finish()
//            startActivity(intent2)
//
//            return true
//        }
//        if(item.itemId == R.id.settings){
//            val intent = Intent(this@MainActivity, ChangePassCodeActiviy::class.java)
//            startActivity(intent)
//            finish()
//            return true
//        }
//        return true
//
//    }
    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}