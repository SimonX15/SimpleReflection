package com.app.simon.simplereflection

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        testReflection()

        CompanyUtil.testReflection()
    }


    private fun testReflection() {
        //        val company = Company()
        //        val clazz = Company::class.java

    }
}
