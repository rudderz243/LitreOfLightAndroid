package com.student.litreoflight

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Switch
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.JsonRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

private lateinit var queue: RequestQueue

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        queue = Volley.newRequestQueue(this)
        PerformInitialCheck()

        val rdgSensitivity = findViewById<RadioGroup>(R.id.rdgSensitivity)

        rdgSensitivity.setOnCheckedChangeListener { group, checkedId ->
            val sensitivity = when (checkedId) {
                R.id.rbLow -> 0
                R.id.rbMedium -> 1
                R.id.rbHigh -> 2
                else -> 0
            }
            SetSensitivity(sensitivity)
        }

        val swLight = findViewById<Switch>(R.id.swLight)

        swLight.setOnClickListener {
            val status = swLight.isChecked
            ToggleLight(status)
        }

        val btnAuto = findViewById<Button>(R.id.btnAuto)
        btnAuto.setOnClickListener {
            val url = "http://192.168.4.1/AutomaticMode"
            val stringRequest = StringRequest(Request.Method.GET, url, { response ->
                Log.d("AUTO_MODE","Response: $response")
            }, { error ->
                Log.d("AUTO_MODE", "Error: $error")
            })
            queue.add(stringRequest)
        }

    }

    fun PerformInitialCheck() {
        val url = "http://192.168.4.1/Status"

        val stringRequest = JsonObjectRequest(url, { response ->
            val lblConnectionStatus = findViewById<TextView>(R.id.lblConnectionStatus)
            lblConnectionStatus.text = "Connection Status: ONLINE"
            val rdgSensitivity = findViewById<RadioGroup>(R.id.rdgSensitivity)
            val sensitivity = response.getInt("Sensitivity")
            when (sensitivity) {
                0 -> {
                    val buttonResponse = findViewById<RadioButton>(R.id.rbLow)
                    buttonResponse.isChecked = true
                }
                1 -> {
                    val buttonResponse = findViewById<RadioButton>(R.id.rbMedium)
                    buttonResponse.isChecked = true
                }
                2 -> {
                    val buttonResponse = findViewById<RadioButton>(R.id.rbHigh)
                    buttonResponse.isChecked = true
                }
            }
        }, { error ->
            val lblConnectionStatus = findViewById<TextView>(R.id.lblConnectionStatus)
            lblConnectionStatus.text = "Connection Status: OFFLINE"
        })
        queue.add(stringRequest)
    }

    fun SetSensitivity(sensitivity: Int) {
        when (sensitivity) {
            0 -> {
                val url = "http://192.168.4.1/Low"
                val stringRequest = StringRequest(Request.Method.GET, url, { response ->
                    Log.d("SET_SENS","Response: $response")
                }, { error ->
                    Log.d("SET_SENS", "Error: $error")
                })
                queue.add(stringRequest)
            }
            1 -> {
                val url = "http://192.168.4.1/Medium"
                val stringRequest = StringRequest(Request.Method.GET, url, { response ->
                    Log.d("SET_SENS","Response: $response")
                }, { error ->
                    Log.d("SET_SENS", "Error: $error")
                })
                queue.add(stringRequest)
            }
            2 -> {
                val url = "http://192.168.4.1/High"
                val stringRequest = StringRequest(Request.Method.GET, url, { response ->
                    Log.d("SET_SENS","Response: $response")
                }, { error ->
                    Log.d("SET_SENS", "Error: $error")
                })
                queue.add(stringRequest)
            }
        }
    }

    fun ToggleLight(status : Boolean) {
        when (status) {
            true -> {
                val url = "http://192.168.4.1/LightOn"
                val stringRequest = StringRequest(Request.Method.GET, url, { response ->
                    Log.d("TOGGLE_LIGHT","Response: $response")
                }, { error ->
                    Log.d("TOGGLE_LIGHT", "Error: $error")
                })
                queue.add(stringRequest)
            }
            false -> {
                val url = "http://192.168.4.1/LightOff"
                val stringRequest = StringRequest(Request.Method.GET, url, { response ->
                    Log.d("TOGGLE_LIGHT","Response: $response")
                }, { error ->
                    Log.d("TOGGLE_LIGHT", "Error: $error")
                })
                queue.add(stringRequest)
            }
        }
    }
}