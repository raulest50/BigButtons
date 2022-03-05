package com.tentactil.bigbuttons

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.Button
import com.tentactil.bigbuttons.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    /**
     * data binding. agregar databinding=True y viewbinding=True en el build gradle de la app.
     * ActivityMainBinding es creado automaticamente por el freamework
     */
    lateinit var binding: ActivityMainBinding

    private var tts: TextToSpeech? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater) // para acceder los views, reemplaza findViewByID()
        val view = binding.root
        setContentView(view)

        tts = TextToSpeech(this, this)

        binding.btnPato.setOnClickListener{
            tts!!.speak("pato", TextToSpeech.QUEUE_FLUSH, null,"")
        }

        binding.btnControl.setOnClickListener{
            tts!!.speak("control", TextToSpeech.QUEUE_FLUSH, null,"")
        }

        binding.btnAspirar.setOnClickListener{
            tts!!.speak("aspirar", TextToSpeech.QUEUE_FLUSH, null,"")
        }

        binding.btnSi.setOnClickListener{
            tts!!.speak("sizas parce", TextToSpeech.QUEUE_FLUSH, null,"")
        }

        binding.btnNo.setOnClickListener{
            tts!!.speak("no", TextToSpeech.QUEUE_FLUSH, null,"")
        }

    }


    override fun onInit(status: Int) {

        if (status == TextToSpeech.SUCCESS) {

            /**
             * se establece lenguaje espanol para text to speech.
             * mirar https://stackoverflow.com/questions/3577058/android-tts-languages
             * pero esta en java hay que pasarlo a kotlin
             */
            val result = tts!!.setLanguage(Locale("spa", "MEX"))

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS","The Language specified is not supported!")
            } else {
                // que hacer en caso de que si este soportado el lenguaje seleccionado
                // por ahora no hacer nada
            }

        } else {
            Log.e("TTS", "Initilization Failed!")
        }

    }
}