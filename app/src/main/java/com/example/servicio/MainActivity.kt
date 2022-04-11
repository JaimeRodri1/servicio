package com.example.servicio

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val serviceClass = view::class.java
        val i = Intent(applicationContext, serviceClass)

        activar.setOnClickListener {
            if (!activationservice(serviceClass)) {
                startService(i)
                Toast.makeText(this,"Se ha iniciado el servicio",Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this,"El servicio esta activo",Toast.LENGTH_SHORT).show()
            }
        }

        desactivar.setOnClickListener {
            if (activationservice(serviceClass)) {
                stopService(i)
                Toast.makeText(this,"Se ha apagado el servicio",Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this,"El servicio esta apagado",Toast.LENGTH_SHORT).show()
            }
        }


    }
    private fun activationservice(serviceClass: Class<*>): Boolean {
        val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        for (service in activityManager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.name == service.service.className) {
                return true
            }
        }
        return false
    }
}