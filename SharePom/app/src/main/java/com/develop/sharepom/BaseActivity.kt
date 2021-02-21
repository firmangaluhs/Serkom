package com.develop.sharepom

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.AppBarLayout
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.actiivity_base.*
import www.sanju.zoomrecyclerlayout.ZoomRecyclerLayout

class BaseActivity : AppCompatActivity() {
    private lateinit var rvData: RecyclerView
    private var list: ArrayList<ModelPom> = arrayListOf()
    var db = FirebaseFirestore.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actiivity_base)
        setSupportActionBar(toolbar)
        toolbar_layout.title = title

        rvData = findViewById(R.id.rv_item)
        setCollapsing()
        showData()
    }

    private fun setCollapsing() {
        var isShow = true
        var scrollRange = -1
        app_bar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { barLayout, verticalOffset ->
            if (scrollRange == -1) {
                scrollRange = barLayout?.totalScrollRange!!
            }
            if (scrollRange + verticalOffset == 0) {
                toolbar_layout.title = "Share Pom"
                isShow = true
            } else if (isShow) {
                toolbar_layout.title =
                    " " //careful there should a space between double quote otherwise it wont work
                isShow = false
            }
        })
    }

    //menampilkan data dari database
    fun showData() {
        db.collection("Data").get()
            .addOnSuccessListener {
                val list = mutableListOf<ModelPom>()
                for (data in it) {
                    list.add(data.toObject(ModelPom::class.java))
                }
                populateData(list)

            }.addOnFailureListener {

            }
    }

    //mengatur tata letak layout
    private fun populateData(list: MutableList<ModelPom>) {
        val linearLayoutManager = ZoomRecyclerLayout(this)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        linearLayoutManager.reverseLayout = true
        linearLayoutManager.stackFromEnd = true
        rvData.layoutManager = linearLayoutManager
        val gridDataAdapter = Adapter(list)
        rvData.adapter = gridDataAdapter
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(rvData) // Menambah recycle view
        rvData.isNestedScrollingEnabled = false
    }
}