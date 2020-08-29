package com.app.mateusz.dsl

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_dsl_playground.*

class DslPlaygroundActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dsl_playground)

        test.setOnClickListener {
            val sausageDog = sausagesDog {
                dogAppearance {
                    name = "Borys"
                    age = 6
                    color = "Brown"
                    length = 70.00
                }
                owner {
                    name = "Mateusz"
                    country = "Poland"
                }
            }

            dsl_message.text = sausageDog.toString()
        }
    }
}
